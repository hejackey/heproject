package com.zhuozhuo.mis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zhuozhuo.mis.po.ScmStorageOutDetail;

public class ScmStorageOutDetailValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return ScmStorageOutDetail.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}