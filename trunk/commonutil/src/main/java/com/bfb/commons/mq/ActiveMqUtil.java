package com.bfb.commons.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqUtil {
	private Connection con;
	public Session session;
	private Destination destination;
	
	public ActiveMqUtil(String queueName){
		ConnectionFactory cf = new ActiveMQConnectionFactory();
		try {
			con = cf.createConnection();
			session = con.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public MessageProducer getAcmqMsgProducer(){
		try {
			return session.createProducer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	public void destoryConnection(){
		if(con != null)
			try {
				con.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
	}
	
	public void destorySession(){
		if(session != null)
			try {
				session.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
	}
}	
