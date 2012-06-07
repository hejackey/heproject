package com.jackey.mq.topic.consumer;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import com.thoughtworks.xstream.XStream;

/**
 * topic模式 消息消费者实现
 * @author xiaolianghe
 *
 */
public class VideoTopicListen implements MessageListener {

    private Connection connection;
    private Session session;
    private Topic topic;

    private String url = "tcp://10.11.132.36:61616";

    public static void main(String[] argv) throws Exception {
        VideoTopicListen l = new VideoTopicListen();
      
        l.run();
    }

    public void run() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        connection = factory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
       
        
        //消费者监听topic
        topic = session.createTopic("VideoInfoChangeTopic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(this);
        
        connection.start();

        System.out.println("Waiting for messages...");
    }

    public void onMessage(Message message) {
        ActiveMQTextMessage textMsg = (ActiveMQTextMessage)message;
        try {
            String text = textMsg.getText();
            System.out.println(text);
            
            String operate = text.substring(text.indexOf("<arg0>"), text.indexOf("</arg0>")).replace("<arg0>","");
            System.out.println(operate);
            
            String videoInfoStr = "<com.sohu.spaces.videos.model.VideoInfo>"+text.substring(text.indexOf("<arg2>"), text.indexOf("</arg2>")).replace("<arg2>","")+"</com.sohu.spaces.videos.model.VideoInfo>";
            System.out.println(videoInfoStr);
            
            XStream xs = new XStream();
            try{
                Object object = xs.fromXML(videoInfoStr);
                System.out.println(object);
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (JMSException e1) {
            e1.printStackTrace();
        }
       
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
