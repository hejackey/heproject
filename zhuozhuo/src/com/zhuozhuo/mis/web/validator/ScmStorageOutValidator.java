package com.zhuozhuo.mis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.zhuozhuo.mis.po.ScmStorageOut;

public class ScmStorageOutValidator  implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return ScmStorageOut.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}