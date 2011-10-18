package com.bfb.commons.test.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

import com.bfb.commons.mq.ActiveMqUtil;

public class TestPoolRmq {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActiveMqUtil.init("queue","","","","pool_queue");
		MessageConsumer consumer = ActiveMqUtil.getAcmqMsgConsuer();
		
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
		
	}

}
