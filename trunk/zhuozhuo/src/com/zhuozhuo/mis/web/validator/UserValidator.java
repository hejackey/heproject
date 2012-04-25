package com.zhuozhuo.mis.web.validator;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zhuozhuo.mis.po.TAdmUser;
import com.zhuozhuo.mis.vo.TUser;
public class UserValidator implements Validator {

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return TUser.class.equals(clazz);
	}

	public void validate(Object obj, Errors erros) {
		TUser formBean = (TUser) obj;
		if (StringUtils.isEmpty(formBean.getName())) {
			erros.rejectValue("name", null, null, "name 是需要的!");
		}
		if (StringUtils.isEmpty(formBean.getTelephone())) {
			erros.rejectValue("telephone", null, null, "telephone是需要的!");
		}
		if (StringUtils.isEmpty(formBean.getMail())) {
			erros.rejectValue("mail", null, null, "mail 是需要的!");
		} else if (!formBean.getMail().matches(
				"\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
			erros.rejectValue("mail", null, null, "不合法的邮件地址!");
		}
	}
	
	public static String validLoginInfo(HttpServletRequest req,TAdmUser user){
		if(StringUtils.isEmpty(user.getLoginName())
				||StringUtils.isEmpty(user.getPassword()))
			return "登录名或密码不能为空";
		
		if(!user.getCheckCode().equals(req.getSession().getAttribute("loginCheckImg")))
			return "验证码错误，请重新输入";
		
		return "";
	}
	
}
