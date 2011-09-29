package com.bfb.transaction.engine.service;

import java.util.Map;

public class AccountNoService {
	public Map<Object,Object> checkAmt(Map<Object,Object> ob){
		System.out.println("test transaction engine");
		System.out.println(ob.get("userid"));
		return null ;
	}
	
	public Map<Object,Object> payOrder(Map<Object,Object> ob){
		return null ;
	}
}
