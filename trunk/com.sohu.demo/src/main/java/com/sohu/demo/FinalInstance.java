package com.sohu.demo;

public class FinalInstance {
	 private final static FinalInstance INSTANCE = new FinalInstance();
	 
	 public static FinalInstance getInstance() {
	        return INSTANCE;
	    }
}
