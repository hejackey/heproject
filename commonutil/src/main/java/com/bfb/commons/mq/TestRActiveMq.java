package com.bfb.commons.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

public class TestRActiveMq {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActiveMqUtil util = new ActiveMqUtil("test_mq");
		MessageConsumer consumer = util.getAcmqMsgConsuer();
		
		try {
			consumer.setMessageListener(new MessageListener(){

				@Override
				public void onMessage(Message msg) {
					ActiveMQTextMessage acmsg = (ActiveMQTextMessage)msg;
					try {
						System.out.println(acmsg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			});
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		//util.destoryConnection();
		//util.destorySession();
	}

}
