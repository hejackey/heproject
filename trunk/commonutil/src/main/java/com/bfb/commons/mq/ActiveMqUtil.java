package com.bfb.commons.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.lang.StringUtils;

/**
 * activemq创建queue工具类 
 * @author Administrator
 * @version 1.0
 * @date 2011-10-18
 */
public class ActiveMqUtil {
	private static Connection con;
	public static Session session;
	private static Destination destination;
	private static final String URL="";
	private static final String USERNAME="";
	private static final String PASSWD="";
	private static final String QUEUENAME="ActiveMq.default";
	private static final String MSG_TYPE_QUEUE="queue";
	
	/**
	 * 工具初始化
	 * @param url	消息服务器的地址
	 * @param userName	消息服务器授权的用户名
	 * @param passwd	消息服务器授权的密码
	 * @param queueName	消息队列名

	 */
	public static void init(String type,String url,String userName,
			String passwd,String queueName){
		try {
			ConnectionFactory cf = null;
			
			checkParam(url,userName,passwd,queueName);
			if(!StringUtils.isEmpty(url))
				cf = new ActiveMQConnectionFactory(url);
			else 
				cf = new ActiveMQConnectionFactory();
			
			con = cf.createConnection(userName,passwd);
			con.start();
			
			session = con.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
			if(MSG_TYPE_QUEUE.equals(type.toLowerCase()))
				destination = session.createQueue(queueName);
			else
				destination = session.createTopic(queueName);
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取消息生产对象
	 * @param url	消息服务器的地址
	 * @param userName	消息服务器授权的用户名
	 * @param passwd	消息服务器授权的密码
	 * @param queueName	消息队列名
	 * @return 消息生产对象
	 */
	public static MessageProducer getAcmqMsgProducer(){
		try {
			return session.createProducer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * 获取消息的消费者
	 * @return	消息消费者
	 */
	public static MessageConsumer getAcmqMsgConsuer(){
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
	public static void destoryConnection(){
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
	public static void destorySession(){
		if(session != null)
			try {
				session.commit();
				session.close();
			} catch (final JMSException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 传入空值参数赋默认值
	 * @param url	消息服务器的地址
	 * @param userName	消息服务器授权的用户名
	 * @param passwd	消息服务器授权的密码
	 * @param queueName	消息队列名
	 */
	private static void checkParam(String url,String userName,
			String passwd,String queueName){
		if(StringUtils.isEmpty(url))
			url = URL;
		
		if(StringUtils.isEmpty(userName))
			userName = USERNAME;
		
		if(StringUtils.isEmpty(passwd))
			passwd = PASSWD;
		
		if(StringUtils.isEmpty(queueName))
			queueName = QUEUENAME;
	}
}
