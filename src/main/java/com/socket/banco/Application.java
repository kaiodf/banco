package com.socket.banco;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.socket.banco.config.RestConfiguration;
import com.socket.banco.tcp.server.SocketServer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = "com.socket.banco")
@Import({ RestConfiguration.class })
@EnableSwagger2
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

	private static Class<?> applicationClass = Application.class;

	public static void main(String[] args){
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		SocketServer socketServer = context.getBean(SocketServer.class);
		try {
			socketServer.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setServletContext(container);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}