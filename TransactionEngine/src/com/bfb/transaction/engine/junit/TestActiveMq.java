package com.bfb.transaction.engine.junit;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestActiveMq {

	/**
	 * @param args
	 * @throws JMSException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws JMSException, InterruptedException {
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(); 
		//����Ⱥ��ʽ�������ȡһ�����õ�broker
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(nio://localhost:61616,nio://localhost:61617)");
		//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.10.223.218:61616");
	    Connection connection = connectionFactory.createConnection();  
	    connection.start();  
	  
	    Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
	    Destination destination = session.createQueue("test_queue_5");  
	  
	    MessageProducer producer = session.createProducer(destination);  
	    for(int i=900; i<1000; i++) {  
	        MapMessage message = session.createMapMessage();  
	        message.setLong("count", new Date().getTime());  
	        message.setString("url", "http://sendsms/"+i);
	       // Thread.sleep(500);  
	        //ͨ����Ϣ�����߷�����Ϣ  
	        producer.send(message);  
	    }  
	    session.commit();  
	    session.close();  
	    connection.close();  
	}

}
