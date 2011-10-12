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
		//做集群方式，随机挑取一个可用的broker
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
	        //通过消息生产者发出消息  
	        producer.send(message);  
	    }  
	    session.commit();  
	    session.close();  
	    connection.close();  
	}

}
