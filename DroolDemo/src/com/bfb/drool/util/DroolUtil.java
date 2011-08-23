package com.bfb.drool.util;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

public class DroolUtil {
	public static KnowledgeBuilder getKnowlegeBuilder(String fileName,Object obj){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add( ResourceFactory.newClassPathResource( fileName, obj.getClass() ),
				ResourceType.DRL );
		if ( kbuilder.hasErrors() ) {
			System.err.println( kbuilder.getErrors().toString() );
		}
		
		return kbuilder;
	}
	
	public static KnowledgeBase getKnowledgeBase(KnowledgeBuilder kbuilder){
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages() );
		
		return kbase;
	}
}
