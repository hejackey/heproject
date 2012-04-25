package com.zhuozhuo.mis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.zhuozhuo.mis.po.ScmBarnType;

public class ScmBarnTypeValidator implements Validator {
	@Override
	public boolean supports(Class clazz) {
		return ScmBarnType.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		
	}
}