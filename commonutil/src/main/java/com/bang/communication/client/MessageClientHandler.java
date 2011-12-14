package com.bang.communication.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Mina Client Handler
 * @author liming
 */
public class MessageClientHandler extends IoHandlerAdapter {
	
	@Override 
	public void messageReceived(IoSession session, Object message) throws Exception { 
		System.out.println(message); 
	}
}
