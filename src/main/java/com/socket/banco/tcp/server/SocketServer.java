package com.socket.banco.tcp.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.socket.banco.controller.SaldoController;
import com.socket.tcp.dto.SocketAutorizacaoDto;
import com.socket.tcp.dto.SocketEntradaDto;

/**
 * The Class SocketServer.
 */
@Service
public class SocketServer {
	
	/** The transacao service. */
	@Autowired
	private SaldoController saldoController;
	
	 /**
 	 * Start server.
 	 *
 	 * @throws Exception the exception
 	 */
 	public void startServer() throws Exception {
	        int portNumber = 33442;
	        System.out.println("Server Up");
	        
	        while(true) {
	        	ServerSocket serverSocket = new ServerSocket(portNumber);
		        Socket clientSocket = serverSocket.accept();
		
		        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
		
		        String socketEntrada = "";
		        while((socketEntrada = (String) ois.readObject()) != null) {
		        	SocketEntradaDto socketEntradaDto = converterJsonToDto(socketEntrada);
		            System.out.println("objeto recebido: " + socketEntradaDto);
		            SocketAutorizacaoDto autorizacaoDto = saldoController.withdraw(socketEntradaDto);
		            oos.writeObject(converterDtoToJson(autorizacaoDto));
		            break;
		        }

	        oos.close();
	        serverSocket.close();
	        }
	    }

	private String converterDtoToJson(SocketAutorizacaoDto autorizacaoDto) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(autorizacaoDto);
	}

	private SocketEntradaDto converterJsonToDto(String socketEntrada) {
		Gson gson = new GsonBuilder().create();
		SocketEntradaDto dto = gson.fromJson(socketEntrada, SocketEntradaDto.class);
		return dto;
	}
	 
}
