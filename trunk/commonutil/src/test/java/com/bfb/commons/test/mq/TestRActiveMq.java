package com.bfb.commons.test.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

import com.bfb.commons.mq.ActiveMqUtil;

public class TestRActiveMq {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActiveMqUtil.init("topic","", "","","test_queue_1");
		MessageConsumer consumer = ActiveMqUtil.getAcmqMsgConsuer();
		
		msgOnListen(consumer);
		
		MessageConsumer consumer1 = ActiveMqUtil.getAcmqMsgConsuer();
		
		msgOnListen(consumer1);
		//msgReceive(consumer);
		//AcMqQueueUtil.destorySession();
		//AcMqQueueUtil.destoryConnection();
	}

	public static void msgOnListen(final MessageConsumer consumer){
		try {
			consumer.setMessageListener(new MessageListener(){

				@Override
				public void onMessage(Message msg) {
					ActiveMQTextMessage acmsg = (ActiveMQTextMessage)msg;
					try {
						System.out.println("consumer is "+consumer+"---------"+acmsg.getText());
						ActiveMqUtil.session.commit();
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void msgReceive(MessageConsumer consumer){
		ActiveMQTextMessage acmsg;
		try {
			acmsg = (ActiveMQTextMessage)consumer.receive();
			System.out.println(acmsg.getText());
			ActiveMqUtil.session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
