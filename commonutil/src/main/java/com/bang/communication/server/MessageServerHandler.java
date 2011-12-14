package com.bang.communication.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Mina ServerHandler
 * @author liming
 */
public abstract class MessageServerHandler extends IoHandlerAdapter {
	
	@Override 
	public void sessionCreated(IoSession session) { 
		System.out.println(session.getRemoteAddress().toString()); 
	}
	
	@Override 
	public void messageReceived( IoSession session, Object message ) throws Exception { 
		String str = message.toString(); 
		
		this.messageReceived(str);
		session.close(true);
//		String returnString = this.messageReturn(str);
//		session.write(returnString);
	}
	
	@Override
	public void sessionClosed(IoSession session) {
		session.close(true);
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable e) {
		session.close(true);
	}
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
	    session.getConfig().setBothIdleTime(180);
	}
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
	    session.close(true);
	}
	
	public abstract void messageReceived(String message);
	public abstract String messageReturn(String message);
}
