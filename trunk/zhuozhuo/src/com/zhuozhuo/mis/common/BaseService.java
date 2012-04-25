package com.zhuozhuo.mis.common;

import org.springframework.beans.BeanUtils;


public class BaseService {
	protected void copyProperties(Object source, Object target){
		BeanUtils.copyProperties(source,target);
	}
}
