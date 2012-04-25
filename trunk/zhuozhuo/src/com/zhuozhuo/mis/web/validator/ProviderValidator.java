package com.zhuozhuo.mis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.zhuozhuo.mis.model.Provider;

public class ProviderValidator implements Validator {
	@Override
	public boolean supports(Class clazz) {
		return Provider.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}
}
