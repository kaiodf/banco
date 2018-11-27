package com.socket.banco.tcp.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		        SocketEntradaDto socketEntradaDto = null;
		        while((socketEntradaDto = (SocketEntradaDto) ois.readObject()) != null) {
		            System.out.println("objeto recebido: " + socketEntradaDto);
		            SocketAutorizacaoDto autorizacaoDto = saldoController.withdraw(socketEntradaDto);
		            oos.writeObject(autorizacaoDto);
		            break;
		        }

	        oos.close();
	        serverSocket.close();
	        }
	    }
	 
}
