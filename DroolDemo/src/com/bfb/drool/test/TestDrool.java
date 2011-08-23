package com.bfb.drool.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import com.bfb.drool.model.Applicant;
import com.bfb.drool.model.Application;

public class TestDrool {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource( "test.drl",TestDrool.class),ResourceType.DRL );
		
		if ( kbuilder.hasErrors() ) {
			System.err.println( kbuilder.getErrors().toString() );
		}
		
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages( kbuilder.getKnowledgePackages() );
		StatelessKnowledgeSession ksession = kbase.newStatelessKnowledgeSession();
		
		Application applicant = new Application( "Mr John Smith", 19);
		System.out.println(applicant.isValid() );
		//传单个对象 
		ksession.execute( applicant );
		System.out.println(applicant.isValid() );
		
		Applicant ap = new Applicant();
	    String format = "dd-MM-yyyy";  
	    System.setProperty("drools.dateformat", format);  
	    DateFormat df = new SimpleDateFormat(format);  
	    ap.setDate(df.parse("22-08-2011"));  
		System.out.println("传多个对象==="+ap.isValid() );
		//传多个对象 
		ksession.execute( Arrays.asList(new Object[]{applicant,ap}) );
		System.out.println("传多个对象==="+ap.isValid() );
	}

}
