package com.bfb.portal.validator;

import java.io.UnsupportedEncodingException;

import com.bfb.portal.model.HelloWorld;
import com.bfb.portal.base.util.StringUtil;

public class HelloWorldValidator extends BaseValidator {
	private HelloWorld world = new HelloWorld();
	
	public String validForm(Object o) {
		try {
			world = (HelloWorld)o;
			
			if(StringUtil.isEmpty(world.getStr())
					||StringUtil.validStrExceedLen(world.getStr(), 50))
				res = "str不能为空，且最大的长度50";
			
			if(StringUtil.isEmpty(world.getParam())
					||StringUtil.validStrExceedLen(world.getParam(),50))
				res = "param不能为空，且最大的长度50";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			res = "不支持的字符集";
		}
		
		return res;
	}
}
