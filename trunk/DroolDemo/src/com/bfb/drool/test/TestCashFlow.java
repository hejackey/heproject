package com.bfb.drool.test;

import java.util.Date;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.runtime.StatefulKnowledgeSession;

import com.bfb.drool.model.mix.Account;
import com.bfb.drool.model.mix.AccountPeriod;
import com.bfb.drool.model.mix.CashFlow;
import com.bfb.drool.util.DroolUtil;

public class TestCashFlow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KnowledgeBuilder kbuilder = DroolUtil.getKnowlegeBuilder("credit.drl", new TestCashFlow());
		KnowledgeBase kbase = DroolUtil.getKnowledgeBase(kbuilder);
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		
		AccountPeriod ap = new AccountPeriod();
		ap.setStart(new Date());
		ap.setEnd(new Date());
		
		Account ac = new Account(1l);
		CashFlow cf = new CashFlow(2,11,new Date(),12.5);
		ksession.insert(ap);
		ksession.insert(ac);
		ksession.insert(cf);
		
		System.out.println("加之前的余额="+ac.getBalance());
		ksession.fireAllRules();
		System.out.println("加之后的余额="+ac.getBalance());
		
		ksession.dispose();
	}

}
