package com.jackey.restlet.resource;

import java.util.Map;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Resource;

import com.jackey.restlet.application.FirstResourceApplication;
import com.jackey.restlet.domain.Item;

public abstract class BaseResource extends Resource {
	public BaseResource(Context context, Request request, Response response) {
		super(context, request, response);
	}

	/** */
	/**
	 * Returns the map of items managed by this application.
	 * 
	 * @return the map of items managed by this application.
	 */
	protected Map<String, Item> getItems() {
		return ((FirstResourceApplication) getContext().getAttributes().get(
				Application.getCurrent())).getItems();
	}
}
