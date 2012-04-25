package com.zhuozhuo.mis.web.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zhuozhuo.mis.model.ProductInfoJxc;

public class ProductInfoJxcValidator implements Validator  {	
	@Override
	public boolean supports(Class clazz) {
		return ProductInfoJxc.class.equals(clazz);		
	}

	@Override
	public void validate(Object obj, Errors erros) {
		ProductInfoJxc productInfoJxc = (ProductInfoJxc) obj;
		if (StringUtils.isEmpty(productInfoJxc.getProductCode())) {
			erros.rejectValue("productCode", null, null, "部品编码是必需的!");
		}
		if (StringUtils.isEmpty(productInfoJxc.getProductName())) {
			erros.rejectValue("productName", null, null, "商品名称是必需的!");
		}
		
		
		if (StringUtils.isEmpty(productInfoJxc.getProductUnit())) {
			erros.rejectValue("productUnit", null, null, "商品单位是必需的!");
		}	
		if (StringUtils.isEmpty(productInfoJxc.getCreateBy())) {
			erros.rejectValue("createBy", null, null, "添加人员是必需的!");
		}	
	}
	 

}
