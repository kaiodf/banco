package com.socket.banco;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ApplicationTests {

	@Tested
	Application application;

	@Injectable
	SpringApplicationBuilder springApplication;
	
	@Test(expected=Exception.class)
	public void construtor() {
		Application.main(null);
	}
	
	@Test(expected=Exception.class)
	public void configureException() {
		application.configure(null);
	}
	
	@Test
	public void configure() {
		assertNotNull(application.configure(new SpringApplicationBuilder()));
	}
	
	@Test(expected=Exception.class)
	public void onStartupException() throws Exception {
		application.onStartup(null);
	}

}
