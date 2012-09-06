package com.jackey.commons.broswer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RunBroswer {
	public static void runBroswer(String webSite){
		try {   
		    Desktop desktop = Desktop.getDesktop();   
		    if (desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)){   
		        URI uri = new URI(webSite);   
		        desktop.browse(uri);   
		    }   
		} catch (IOException ex) {   
		    ex.printStackTrace();   
		} catch (URISyntaxException ex) {   
		    ex.printStackTrace();   
		}   	
	}
	
	public static void main(String[] args){
		runBroswer("http://share.renren.com/share/480118926/14147534197?from=0101010302&ref=hotnewsfeed&sfet=110&fin=0&ff_id=480118926");
	}
}
