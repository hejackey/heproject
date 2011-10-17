package com.bfb.commons.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqUtil {
	private Connection con;
	public Session session;
	private Destination destination;
	
	/**
	 * 初始化消息队列连接、会话、目标队列等信息
	 * @param queueName 创建的队列名
	 */
	public ActiveMqUtil(String queueName){
		ConnectionFactory cf = new ActiveMQConnectionFactory();
		try {
			con = cf.createConnection();
			con.start();
			session = con.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取消息生产者
	 * @return	消息生产者
	 */
	protected MessageProducer getAcmqMsgProducer(){
		try {
			return session.createProducer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	protected MessageConsumer getAcmqMsgConsuer(){
		try {
			return session.createConsumer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 关闭消息队列连接
	 */
	protected void destoryConnection(){
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
	protected void destorySession(){
		if(session != null)
			try {
				session.close();
			} catch (final JMSException e) {
				e.printStackTrace();
			}
	}
}	