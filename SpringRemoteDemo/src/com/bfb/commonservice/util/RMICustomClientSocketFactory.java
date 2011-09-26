package com.bfb.commonservice.util;

import java.io.IOException;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

public class RMICustomClientSocketFactory implements RMIClientSocketFactory {
	private int timeout;
	
	
	@Override
	public Socket createSocket(String host, int port) throws IOException {
		 Socket socket = new Socket(host, port);  
		 socket.setSoTimeout(timeout);  
		 return socket;  
	}


	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
