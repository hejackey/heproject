package com.bfb.drool.test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.bfb.drool.model.stateful.Alarm;
import com.bfb.drool.model.stateful.Fire;
import com.bfb.drool.model.stateful.Room;
import com.bfb.drool.model.stateful.Sprinkler;

public class TestFire {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource( "stateful.drl", TestFire.class ),
				ResourceType.DRL );
		if ( kbuilder.hasErrors() ) {
			System.err.println( kbuilder.getErrors().toString() );
		}
		
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		StatefulKnowledgeSession ksession2 = kbase.newStatefulKnowledgeSession();
		
		//调用着火报警规则
		Room room = new Room("fire");
		Fire fire = new Fire();
		fire.setRoom(room);
		ksession.insert(room);
		ksession.insert(fire);
		System.out.println(ksession.fireAllRules());//返回激活规则的数量
		
		//调用打开消防车规则
		Sprinkler sprinkler = new Sprinkler(room);
		ksession.insert(sprinkler);
		System.out.println("触发规则前消防车状态========"+sprinkler.isOn());
		System.out.println(ksession.fireAllRules());//返回激活规则的数量
		System.out.println("触发规则后消防车状态========"+sprinkler.isOn());
		
		
		//调用关闭消防车规则
		Room room1 = new Room("not fire");
		ksession.insert(room1);
		Sprinkler sprinkler1 = new Sprinkler(room1);
		sprinkler1.setOn(true);
		ksession.insert(sprinkler1);
		
		System.out.println("关闭规则前消防车状态========"+sprinkler1.isOn());
		System.out.println(ksession.fireAllRules());//返回激活规则的数量
		System.out.println("关闭规则后消防车状态========"+sprinkler1.isOn());
		ksession.dispose();
		
		//取消报警
		Alarm alarm = new Alarm();
		ksession2.insert(alarm);
		System.out.println(ksession2.fireAllRules());//返回激活规则的数量
		ksession2.dispose();
	}

}
