package com.bfb.portal.manager.impl;

import com.bfb.portal.manager.TestHelloWorldManager;

public class TestHelloWorldManagerImpl implements TestHelloWorldManager {

	public String sayHello(String arg) {
		return "I'm from service layer,say hello to"+arg;
	}

}
