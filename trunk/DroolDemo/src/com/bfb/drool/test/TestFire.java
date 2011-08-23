package com.bfb.drool.test;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.runtime.StatefulKnowledgeSession;

import com.bfb.drool.model.stateful.Alarm;
import com.bfb.drool.model.stateful.Fire;
import com.bfb.drool.model.stateful.Room;
import com.bfb.drool.model.stateful.Sprinkler;
import com.bfb.drool.util.DroolUtil;

public class TestFire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KnowledgeBuilder kbuilder = DroolUtil.getKnowlegeBuilder("stateful.drl", new TestFire());
		KnowledgeBase kbase = DroolUtil.getKnowledgeBase(kbuilder);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		StatefulKnowledgeSession ksession2 = kbase.newStatefulKnowledgeSession();
		
		//�����Ż𱨾�����
		Room room = new Room("fire");
		Fire fire = new Fire();
		fire.setRoom(room);
		ksession.insert(room);
		ksession.insert(fire);
		System.out.println(ksession.fireAllRules());//���ؼ�����������
		
		//���ô�����������
		Sprinkler sprinkler = new Sprinkler(room);
		ksession.insert(sprinkler);
		System.out.println("��������ǰ������״̬========"+sprinkler.isOn());
		System.out.println(ksession.fireAllRules());//���ؼ�����������
		System.out.println("���������������״̬========"+sprinkler.isOn());
		
		
		//���ùر�����������
		Room room1 = new Room("not fire");
		ksession.insert(room1);
		Sprinkler sprinkler1 = new Sprinkler(room1);
		sprinkler1.setOn(true);
		ksession.insert(sprinkler1);
		
		System.out.println("�رչ���ǰ������״̬========"+sprinkler1.isOn());
		System.out.println(ksession.fireAllRules());//���ؼ�����������
		System.out.println("�رչ����������״̬========"+sprinkler1.isOn());
		ksession.dispose();
		
		//ȡ������
		Alarm alarm = new Alarm();
		ksession2.insert(alarm);
		System.out.println(ksession2.fireAllRules());//���ؼ�����������
		ksession2.dispose();
	}

}
