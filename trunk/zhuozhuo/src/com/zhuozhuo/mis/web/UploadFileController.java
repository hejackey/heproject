package com.zhuozhuo.mis.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.zhuozhuo.utils.Constants;
import common.Logger;

/**
 * 文件上传控件
 * @author dell
 *
 */
public class UploadFileController extends MultiActionController {
	private Logger log = Logger.getLogger(UploadFileController.class);
	
	public ModelAndView uploadFile(HttpServletRequest req,HttpServletResponse res){
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8"); 

		try {
			List items = upload.parseRequest(req);
			if (items != null) {
				Iterator itr = items.iterator();
				log.info("upload file start");
				
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					
					if (item.isFormField()) {
						continue;
					} else {
						File fullFile=new File(item.getName());						
						File savedFile=new File(Constants.UPLOAD_PATH);
						
						if(!savedFile.isDirectory())
							savedFile.mkdir();
						
						item.write(new File(Constants.UPLOAD_PATH+fullFile.getName()));
					}
				}
			}
			
			log.info("upload file end");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("upload file exception");
		}				
		
		return null;
	}
}
