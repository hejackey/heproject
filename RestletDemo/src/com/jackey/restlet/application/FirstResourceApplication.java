package com.jackey.restlet.application;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Router;
import com.jackey.restlet.resource.ItemResource;
import com.jackey.restlet.domain.Item;

public class FirstResourceApplication extends Application {
	private final Map<String, Item> items;

	public FirstResourceApplication(Context parentContext) { 
	super(parentContext); 
	// We make sure that this attribute will support concurrent access. 
	items = new ConcurrentHashMap<String, Item>(); 
	}

	/** *//** 
	* Creates a root Restlet that will receive all incoming calls. 
	*/ 
	@Override 
	public synchronized Restlet createRoot() { 
	// Create a router Restlet that defines routes. 
	Router router = new Router(getContext());

	// Defines a route for the resource "list of items" 
	router.attach("/items", ItemResource.class); 
	// Defines a route for the resource "item" 
	router.attach("/items/{itemName}", ItemResource.class);

	return router; 
	}

	/** *//** 
	* Returns the list of registered items. 
	* 
	* @return the list of registered items. 
	*/ 
	public Map<String, Item> getItems() { 
		return items; 
	}
}
