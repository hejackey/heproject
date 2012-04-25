package com.zhuozhuo.mis.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.apache.commons.lang.StringUtils;
import com.zhuozhuo.mis.model.ProductType;
import com.zhuozhuo.utils.StringUtil;

public class ProductTypeValidator implements Validator {	
	
	@Override
	public boolean supports(Class clazz) {
		return ProductType.class.equals(clazz);		
	}

	@Override
	public void validate(Object obj, Errors erros) {
		ProductType productType = (ProductType) obj;
		if (StringUtils.isEmpty(productType.getGoodsTypeCode())) {
			erros.rejectValue("goodsTypeCode", null, null, "部品类别编码是必需的!");
		}
		if (StringUtils.isEmpty(productType.getGoodsTypeName())) {
			erros.rejectValue("goodsTypeName", null, null, "商品类别名称是必需的!");
		}
//		System.out.println(0);
//		if((StringUtil.trim(new Integer(productType.getClassLevel()).toString())).equals("")){
//			erros.rejectValue("classLevel", null, null, "货品级别必须是数字!");
//		}
//		boolean test=StringUtil.isNumLegal(new Integer(productType.getClassLevel()).toString(),4);
//		if(StringUtil.isNumLegal(new Integer(productType.getClassLevel()).toString(),4)){
//			erros.rejectValue("classLevel", null, null, "货品级别必须是数字!");
//		}
	}
}
