package com.bang.communication.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * Mina Server
 * @author liming
 */
public class MessageServer {
	private int port;

	private MessageServerHandler messageServerHandler;
	
	public void setMessageServerHandler(MessageServerHandler messageServerHandler) {
		this.messageServerHandler = messageServerHandler;
	}

	public void run() throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
		acceptor.setHandler(messageServerHandler);
		acceptor.setDefaultLocalAddress(new InetSocketAddress(port));
		acceptor.bind();
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
