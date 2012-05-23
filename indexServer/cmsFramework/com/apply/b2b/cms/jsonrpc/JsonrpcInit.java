package com.apply.b2b.cms.jsonrpc;

import com.apply.b2b.cms.common.IJsonrpc;
import com.metaparadigm.jsonrpc.JSONRPCBridge;


public class JsonrpcInit {
	public static void register(){		
		JSONRPCBridge bridge= JSONRPCBridge.getGlobalBridge();
		IJsonrpc jsonServer=new JsonRpcServer();
		bridge.registerObject("jsonServer",jsonServer);
	}
}
