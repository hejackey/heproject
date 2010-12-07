package com.jackey.restlet.resource;

import org.restlet.Component;
import org.restlet.data.Protocol;

import com.jackey.restlet.application.FirstResourceApplication;

public class FirstResourceMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Component component = new Component();

		// Add a new HTTP server listening on port 8182. 
		component.getServers().add(Protocol.HTTP, 8182);

		// Attach the sample application. 
		component.getDefaultHost().attach("/firstResource", 
		new FirstResourceApplication(component.getContext()));

		// Start the component. 
		component.start(); 
	}

}
