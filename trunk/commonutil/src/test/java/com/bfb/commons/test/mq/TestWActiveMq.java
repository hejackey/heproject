package com.bfb.commons.test.mq;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import com.bfb.commons.mq.ActiveMqUtil;

public class TestWActiveMq {

	/**
	 * @param args
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws JMSException {
		/*ActiveMqUtil util = new ActiveMqUtil("test_mq");
		MessageProducer msgp = util.getAcmqMsgProducer();
		TextMessage msg = util.session.createTextMessage();
		msg.setText("已经测试成功，测试activemq");
		msgp.send(msg);
		
		util.destorySession();
		util.destoryConnection();*/
		
		ActiveMqUtil.init("topic","", "","","test_queue_1");
		MessageProducer msgp = ActiveMqUtil.getAcmqMsgProducer();
		TextMessage msg = ActiveMqUtil.session.createTextMessage();
		for(int i=0;i<3;i++){
			msg.setText("已经测试成功，测试activemq_"+i);
			msgp.send(msg);
		}
		
		ActiveMqUtil.destorySession();
		ActiveMqUtil.destoryConnection();
	}

}
