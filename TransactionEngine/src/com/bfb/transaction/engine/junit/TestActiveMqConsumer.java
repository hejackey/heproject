package com.bfb.transaction.engine.junit;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestActiveMqConsumer {

	/**
	 * @param args
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws JMSException {
		 ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover:(nio://localhost:61616,nio://localhost:61617)");  
		  
		    Connection connection = connectionFactory.createConnection();  
		    connection.start();  
		  
		    final Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
		   /* Destination destination = session.createQueue("test_queue_5");  
		  
		    MessageConsumer consumer = session.createConsumer(destination); 
		    
		    MessageConsumer consumer2 = session.createConsumer(destination);
		    */
		    Topic topic = session.createTopic("mq_topic_1");  
			  
		    MessageConsumer consumer = session.createConsumer(topic); 
		    
		    MessageConsumer consumer2 = session.createConsumer(topic);
		    
		   //listener 方式 
		   /* consumer.setMessageListener(new MessageListener() { 
		 
		        public void onMessage(Message msg) { 
		            MapMessage message = (MapMessage) msg; 
		            try {
						System.out.println("消费者1-收到消息：" + new Date(message.getLong("count"))+"==="+message.getString("url"));
						session.commit(); 
					} catch (JMSException e) {
						e.printStackTrace();
					} 
		            
		        } 
		 
		    }); 
		    
		    consumer2.setMessageListener(new MessageListener() { 
				 
		        public void onMessage(Message msg) { 
		            MapMessage message = (MapMessage) msg; 
		            try {
						System.out.println("消费者2-收到消息：" + new Date(message.getLong("count"))+"==="+message.getString("url"));
						session.commit(); 
					} catch (JMSException e) {
						e.printStackTrace();
					} 
		            
		        } 
		 
		    }); */
		    consumer.setMessageListener(new MessageListener() { 
				 
		        public void onMessage(Message msg) { 
		        	TextMessage message = (TextMessage) msg; 
		            try {
						System.out.println("消费者1-收到消息：" + message.getText());
						session.commit(); 
					} catch (JMSException e) {
						e.printStackTrace();
					}
		            
		        } 
		 
		    }); 
		    
		    consumer2.setMessageListener(new MessageListener() { 
				 
		        public void onMessage(Message msg) { 
		        	TextMessage message = (TextMessage) msg; 
		            try {
						System.out.println("消费者2-收到消息：" + message.getText());
						session.commit(); 
					} catch (JMSException e) {
						e.printStackTrace();
					} 
		            
		        } 
		 
		    }); 
		    try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		     
		    
		    //从队列中同步接收消息,满足读取的消息数量后，关闭连接
		    /*int i=0;  
		    while(i<100) {  
		        i++;  
		        MapMessage message = (MapMessage) consumer.receive();  
		        session.commit();  
		  
		        //TODO something....  
		        System.out.println("收到消息：" + new Date(message.getLong("count"))+"==="+message.getString("url"));  
		    }  */
		    
		   /* MapMessage message = (MapMessage) consumer.receive();  
	        session.commit();  
	  
	        //TODO something....  
	        System.out.println("收到消息：" + new Date(message.getLong("count"))+"==="+message.getString("url"));  
		  
		    session.close();  */
		    connection.close();  
	}

}
