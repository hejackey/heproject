package com.bfb.commons.mq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;


public class ActiveMqPoolUtil {
	private static final int MAX_POOL_CONNECTION=10;
	private  PooledConnectionFactory poolConFact = new PooledConnectionFactory();
	private  ActiveMQConnection con ;
	public  Session session;
	private  Destination destination ;
	
	public ActiveMqPoolUtil(String queueName){
		poolConFact.setMaxConnections(MAX_POOL_CONNECTION);
		
		try {
			PooledConnection pcon = (PooledConnection)poolConFact.createConnection();
			con = pcon.getConnection();
			con.start();
			session = con.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	public MessageProducer getMsgProducer(){
		try {
			return session.createProducer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关闭消息队列连接
	 */
	public void destoryConnection(){
		if(con != null)
			try {
				con.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 关闭消息队列会话
	 */
	public void destorySession(){
		if(session != null)
			try {
				session.close();
			} catch (final JMSException e) {
				e.printStackTrace();
			}
	}
}
