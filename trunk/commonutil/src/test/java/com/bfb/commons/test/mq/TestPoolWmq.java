package com.bfb.commons.test.mq;

import javax.jms.JMSException;
import javax.jms.MessageProducer;

import org.apache.activemq.command.ActiveMQTextMessage;

import com.bfb.commons.mq.ActiveMqPoolUtil;

public class TestPoolWmq {

	public static void main(String[] args) throws JMSException{
		ActiveMqPoolUtil util = new ActiveMqPoolUtil("pool_queue");
		
		MessageProducer msgProducer = util.getMsgProducer();
		
		ActiveMQTextMessage msg = new ActiveMQTextMessage();
		msg.setText("池化消息队列");
		msgProducer.send(msg);
		
		util.session.commit();
		util.destoryConnection();
		util.destorySession();
	}

}
