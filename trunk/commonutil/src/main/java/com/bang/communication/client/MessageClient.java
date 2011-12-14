package com.bang.communication.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MessageClient {
	private NioSocketConnector connector;
	private ConnectFuture cf;
	protected String receiveMessage;

	public MessageClient(String remoteIp, int remotePort, int timeout) {
		this.init(remoteIp, remotePort, timeout);
	}
	
	@SuppressWarnings("deprecation")
	public void init(String remoteIp, int remotePort, int timeout) {
		connector = new NioSocketConnector(); 
		connector.getFilterChain().addLast( "logger", new LoggingFilter() );
		connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
		connector.setConnectTimeout(timeout);
		connector.setHandler(new IoHandlerAdapter(){
			@Override 
			public void messageReceived(IoSession session, Object message) throws Exception {
				receiveMessage = message.toString();
			}
		});
		cf = connector.connect(new InetSocketAddress(remoteIp, remotePort));
		cf.awaitUninterruptibly();
	}
	
	public void sentMesasge(String message) {
		cf.getSession().write(message);
	}
	
	public void close() {
		cf.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
