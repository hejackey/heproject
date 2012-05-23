package com.liu.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.FileAppender;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Priority;

public class Log4jTest {
	static Logger logger = Logger.getLogger(Log4jTest.class.getName());

	public Log4jTest() {
	}
	public static void main(String[] args) {
		String path = "F:\\log4j.properties";
		PropertyConfigurator.configure(path);
		Logger logger = Logger.getLogger(Log4jTest.class);
		logger.debug("a test.");
	}
}