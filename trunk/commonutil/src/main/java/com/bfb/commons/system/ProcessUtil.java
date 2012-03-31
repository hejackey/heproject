package com.bfb.commons.system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 执行系统命令工具类
 * @author xiaolianghe
 *
 */
public class ProcessUtil {
	/**
	 * 获取进程输出流，判断系统命令是否执行完
	 * @param pro
	 * @return
	 * @throws Exception
	 */
	public static String readInputFromProcess(Process pro)throws Exception{
        StringBuffer sb = new StringBuffer();
        String line=null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        try {
            while((line=reader.readLine())!=null){
                sb.append(line).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取本地进程输出流失败!");
        }finally{
            reader.close();
        }
        return sb.toString();
	}

}
