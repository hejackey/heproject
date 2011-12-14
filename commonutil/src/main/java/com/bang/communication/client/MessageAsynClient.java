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

/**
 * Mina Client
 * @author liming
 */
public class MessageAsynClient {
	private String remoteIp;
	private int remotePort;
	private int timeout;
	protected String receiveMessage;
	
	public MessageAsynClient(String remoteIp, int remotePort, int timeout) {
		this.remoteIp = remoteIp;
		this.remotePort = remotePort;
		this.timeout = timeout;
	}
	
	@SuppressWarnings("deprecation")
	public void sentMessage(String message) {
		NioSocketConnector connector = new NioSocketConnector(); 
		connector.getFilterChain().addLast( "logger", new LoggingFilter() ); 
		connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" )))); //设置编码过滤器 
		connector.setConnectTimeout(timeout); 
		connector.setHandler(new IoHandlerAdapter(){
			@Override 
			public void messageReceived(IoSession session, Object message) throws Exception {
				receiveMessage = message.toString();
			}
		});
		ConnectFuture cf = connector.connect( 
		new InetSocketAddress(remoteIp, remotePort));
		cf.awaitUninterruptibly();
		cf.getSession().write(message);
		cf.getSession().close(true);
//		cf.getSession().getCloseFuture().awaitUninterruptibly();
		connector.dispose();
	}
}
