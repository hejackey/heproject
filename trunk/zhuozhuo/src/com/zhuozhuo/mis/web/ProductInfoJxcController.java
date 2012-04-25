package com.zhuozhuo.mis.web;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.model.ProductInfoJxc;
import com.zhuozhuo.mis.model.QueryProductInfoJxc;
import com.zhuozhuo.mis.po.PProductInfoJxc;
import com.zhuozhuo.mis.service.ProductInfoJxcService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.DateUtils;
import com.zhuozhuo.utils.StringUtils;


public class ProductInfoJxcController extends BaseMultiActionController  {
	private static final String productInfoJxcListView = "productInfoJxc/listProductInfoJxcs";		
	private static final String productInfoJxcFormView = "productInfoJxc/productInfoJxcForm";	
	private static final String listProductInfoJxcs = "listProductInfoJxc.do";
	private static final String updateProductInfoJxcFormView = "productInfoJxc/updateProductInfoJxcForm";	
	private static final String queryProductInfoJxcView = "productInfoJxc/queryProductInfoJxc";
	private static final String uploadImageProductInfoJxcView = "productInfoJxc/uploadImage";
	
	
	

	private ProductInfoJxcService productInfoJxcService;	
	
	/**
	 * 上传一个商品图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView uploadImageProductInfoJxc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");		
		ModelAndView mv;		
		ProductInfoJxc productInfoJxc=productInfoJxcService.getProductInfoJxc(id);
		mv = new ModelAndView(uploadImageProductInfoJxcView,"command",productInfoJxc);		
		return mv;		
	}
	/**
	 * 上传一个商品图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveImageProductInfoJxc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id="";
		String productType="";
		String productName="";
		String productCode="";
		ServletContext sc=this.getServletContext();
		String savePath = sc.getRealPath("/");		
		savePath = savePath + "uploads";		
	    File uploadPath = new File(savePath);// 上传文件目录
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		} // 临时文件目录
		File tempPathFile = new File(savePath+"/buffer");
		if (!tempPathFile.exists()) {
			tempPathFile.mkdirs();
		}
		try { // Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory(); // Set  factory constraints
			factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
			factory.setRepository(tempPathFile);// 设置缓冲区目录 // Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory); // Set overall request  size constraint
			upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
			List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
			for(int i=0;i<items.size();i++){
				FileItem fi=items.get(i);
				if(fi.isFormField()){
					String fieldName=fi.getFieldName();					
					if(fieldName.equals("id")){
						id=fi.getString("utf-8");						
					}
					if(fieldName.equals("productType")){
						productType=fi.getString("utf-8");						
					}
					if(fieldName.equals("productName")){
						productName=fi.getString("utf-8");						
					}
					if(fieldName.equals("productCode")){
						productCode=fi.getString("utf-8");						
					}
				}
			}
			Iterator<FileItem> i = items.iterator();
			int j=0;
			while (i.hasNext()) {				
				j=j+1;
				FileItem fi = (FileItem) i.next();
				
				String fileName = fi.getName();
				if (fileName != null) {
					File fullFile = new File(fi.getName());
					String productTypePath = savePath + "/"+productType+"/";
					//System.out.println("productTypePath:"+productTypePath);
				    File uploadProductTypePath = new File(productTypePath);// 上传文件目录
					if (!uploadProductTypePath.exists()) {
						uploadProductTypePath.mkdirs();
					} // 临时文件目录
					File savedFile = new File(uploadProductTypePath, productName+productCode+fullFile.getName());
					fi.write(savedFile);
				}
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}	
		//更新表PRODUCT.PRODUCT_PHOTO
		

		ModelAndView mv = new ModelAndView();
		View v = new RedirectView(listProductInfoJxcs);
		mv.setView(v);
		return mv;		
	}
	/**
	 * 增加一个商品类别
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addorEditProductInfoJxc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("id");
		ModelAndView mv;
		ProductInfoJxc productInfoJxc;		
		if(id==null){
			 productInfoJxc=new ProductInfoJxc();
			 String produceDate=DateUtils.getDateDayFormat(new java.util.Date());
			 productInfoJxc.setProduceDate(produceDate);//将成员变量赋予当前系统时间			 
			 mv = new ModelAndView(productInfoJxcFormView,"command",productInfoJxc);
		}else{
			productInfoJxc=productInfoJxcService.getProductInfoJxc(id);
			mv = new ModelAndView(updateProductInfoJxcFormView,"command",productInfoJxc);
		}
		return mv;		
	}
	
	/**
	 * 保存一个商品类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveProductInfoJxc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProductInfoJxc productInfoJxc=new ProductInfoJxc();		
		ModelAndView mv = new ModelAndView();
		if (!validation(request, productInfoJxc, mv)) { // 校验失败
			mv.setViewName(productInfoJxcFormView);// 跳转到修改页
			mv.addObject("command",productInfoJxc);
		} else { // 校验成功
			productInfoJxcService.saveProductInfoJxc(productInfoJxc);
			View v = new RedirectView(listProductInfoJxcs);
			mv.setView(v);
		}
		return mv;
	}
	/**
	 * 检索商品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listProductInfoJxcs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryProductInfoJxc queryProductInfoJxc=new QueryProductInfoJxc();
		bind(request, queryProductInfoJxc);
		List<PProductInfoJxc> productInfoJxcList;
		if((queryProductInfoJxc.getProductName()==null)&&(queryProductInfoJxc.getProductCode()==null)&&(queryProductInfoJxc.getProductType()==null)){
			productInfoJxcList=productInfoJxcService.getAllProductInfoJxc();
		}else{
			productInfoJxcList=productInfoJxcService.getAllProductInfoJxc(queryProductInfoJxc);
		}		
		ModelAndView mv = new ModelAndView(productInfoJxcListView,"productInfoJxcList", productInfoJxcList);		
		return mv;
	}
	
	/**
	 * 综合查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView queryProductInfoJxcs(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		QueryProductInfoJxc queryProductInfoJxc=new QueryProductInfoJxc();		
		bind(request, queryProductInfoJxc);
		ModelAndView mv;
		if((queryProductInfoJxc.getCreateBy()==null)&&(queryProductInfoJxc.getProductCode()==null)&&(queryProductInfoJxc.getProductName()==null)&&(queryProductInfoJxc.getProductType()==null)){
			mv = new ModelAndView(queryProductInfoJxcView,"command",queryProductInfoJxc);
		}else{
			List<PProductInfoJxc> list=productInfoJxcService.queryProductInfoJxcs(queryProductInfoJxc);			
			Map map=new HashMap();
			map.put("command",queryProductInfoJxc);
			map.put("productInfoJxcs",list);			
			mv = new ModelAndView(queryProductInfoJxcView,map);
		}		
		return mv;
	}
	
	public ProductInfoJxcService getProductInfoJxcService() {
		return productInfoJxcService;
	}

	
	/**
	 * 更新商品信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateProductInfoJxc(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ProductInfoJxc productInfoJxc = new ProductInfoJxc();
		ModelAndView view = new ModelAndView();		
		if (!validation(request, productInfoJxc, view)) { // 校验失败
			view.setViewName(updateProductInfoJxcFormView);// 跳转到修改页
			view.addObject("command",productInfoJxc);			
		} else { // 校验成功
			// 更新数据库
			productInfoJxcService.updateProductInfoJxc(productInfoJxc);
			View v = new RedirectView(listProductInfoJxcs);
			view.setView(v);			
		}
		return view;
	} 
	/**
	 * 删除商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public  ModelAndView  deleteProductInfoJxcs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] ids=request.getParameterValues("id");
		if(ids!=null){
			productInfoJxcService.deleteProductInfoJxcs(ids);
		}
		
		ModelAndView view = new ModelAndView();
		View v = new RedirectView(listProductInfoJxcs);
		view.setView(v);		
		return view;
	}
	
	
	public void setProductInfoJxcService(ProductInfoJxcService productInfoJxcService) {
		this.productInfoJxcService = productInfoJxcService;
	}
	
	public ModelAndView qByProductCodeProductInfoJxc(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		QueryProductInfoJxc queryProductInfoJxc=new QueryProductInfoJxc();		
		bind(request, queryProductInfoJxc);
		ModelAndView mv;
		if(!StringUtils.isEmpty(queryProductInfoJxc.getProductCode()))
			queryProductInfoJxc.setProductCode(new String(queryProductInfoJxc.getProductCode().getBytes("iso-8859-1"),"utf-8"));

		queryProductInfoJxc.setQuerytype(request.getParameter("querytype"));
		PageWraper pw = productInfoJxcService.qByProductCodeProdInfoJxcs(queryProductInfoJxc);
		if(pw!=null);
		queryProductInfoJxc.setProductList(pw.getResult());
		
		return new ModelAndView("/productInfoJxc/qProductResult","model",queryProductInfoJxc);		
	}
}
