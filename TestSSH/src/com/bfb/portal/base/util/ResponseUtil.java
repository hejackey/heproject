package com.bfb.portal.base.util;

import java.io.PrintWriter;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import com.bfb.portal.base.model.BaseDataGridJson;

/**
 * 输出json串工具类
 * @author Administrator
 *
 */
public class ResponseUtil {
	/**
	 * 输入json串
	 * @param <T>
	 * @param object json串对象
	 * @param response HttpServletResponse
	 * @param type	json串类型
	 * @return
	 */
	public static <T> boolean printJson(BaseDataGridJson<T> object,
			HttpServletResponse response,Type type){
		PrintWriter writer;
		try {
			String res = GsonUtil.toGson(object, type);
			
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			writer.write(res);
			writer.flush();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
		finally{
			writer = null;
		}
	}
	
	public static void printStr(String res,HttpServletResponse response){
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			//writer.write(res);
			
			writer.print(res);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally{
			writer.close();
			writer = null;
		}
	}
}