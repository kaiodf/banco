package com.socket.tcp.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.Socket;

import com.socket.tcp.dto.SocketAutorizacaoDto;
import com.socket.tcp.dto.SocketEntradaDto;

public class SocketClient {

	public static void main(String[] args) throws Exception {
        int portNumber = 33442;
        Socket socket = new Socket(InetAddress.getLocalHost(), portNumber);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        SocketEntradaDto dto = new SocketEntradaDto();
        dto.setAction("withdraw");
        dto.setCardnumber("123456");
        dto.setAmount(BigDecimal.TEN);

        oos.writeObject(dto);

        SocketAutorizacaoDto socketAutorizacaoDto = null;
        while((socketAutorizacaoDto = (SocketAutorizacaoDto) ois.readObject()) != null) {
            System.out.println(socketAutorizacaoDto);
            break;
        }

        ois.close();
        oos.close();
        socket.close();
    }
}
