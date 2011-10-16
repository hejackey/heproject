package com.bfb.commons.mq;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class TestWActiveMq {

	/**
	 * @param args
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws JMSException {
		ActiveMqUtil util = new ActiveMqUtil("test_mq");
		MessageProducer msgp = util.getAcmqMsgProducer();
		TextMessage msg = util.session.createTextMessage();
		msg.setText("测试activemq");
		msgp.send(msg);
		
	}

}
