package com.zhuozhuo.mis.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import com.zhuozhuo.mis.common.BaseMultiActionController;
import com.zhuozhuo.mis.model.ProductType;
import com.zhuozhuo.mis.model.QueryProductType;
import com.zhuozhuo.mis.po.PProductType;
import com.zhuozhuo.mis.service.ProductTypeService;
import com.zhuozhuo.mis.util.page.PageWraper;
import com.zhuozhuo.utils.NumberUtils;
import com.zhuozhuo.utils.StringUtils;

@SuppressWarnings("deprecation")
public class ProductTypeController extends BaseMultiActionController {
	private static final long productTypeRoot = 0;//设置根目录的recordId为零
	private static final String parentName = "商品类别";//设置根目录的名称
	
	private static final String productTypeListView = "productType/listProductTypes";		
	private static final String productTypeFormView = "productType/productTypeForm";
	private static final String editProductTypeForm = "productType/editProductTypeForm";
	private static final String treeProductType = "productType/treeProductType";
	// 商品信息分类信息列表key值
	private static final String listProductTypes = "listProductType.do";
	
	//用于综合查询页面
	private static final String queryProductTypeView = "productType/queryProductType";	
	private ProductTypeService productTypeService;
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}
	/**
	 * 商品类别树
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView treeProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		//ModelAndView mv = new ModelAndView(treeProductType,"command",new ProductType());
		ModelAndView mv = new ModelAndView(treeProductType);
		return mv;
	}
	/**
	 * 增加一个商品类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView addorEditProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		ModelAndView mv = new ModelAndView(productTypeFormView,"command",new ProductType());
		return mv;
	}
	
	/**
	 * 保存一个商品类型
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView saveProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ProductType productType=new ProductType();
		ModelAndView mv = new ModelAndView();
		if (!validation(request, productType, mv)) { //校验失败
			mv.setViewName(productTypeFormView);//跳转到修改页
			mv.addObject("command",productType);
		} else { //校验成功
			productTypeService.saveProductType(productType);
			View v = new RedirectView(listProductTypes);
			mv.setView(v);
		}
		return mv;		
	}
	
	/**
	 * 检索商品类型，如果是根则检索所有的商品类型，否则，只检索其所属子类型,包括生成树
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listProductTypes(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String recordId=request.getParameter("recordId");
		if(recordId==null)
			recordId=new Long(productTypeRoot).toString();
		long recordIdl=NumberUtils.parseLong(recordId);
		List productTypesList;
		ProductType productType=new ProductType();
		if(recordIdl==productTypeRoot){
			productTypesList=productTypeService.getAllProductTypes();
			productType.setRecordId(0);
			productType.setParentId(null);
			productType.setParentName(null);
		}else{
			productTypesList=productTypeService.getChildProductTypes(recordIdl);
			productType=productTypeService.getProductType(recordId);
		}
		
		Map map=new HashMap();
		map.put("productTypesList",productTypesList);
		List<PProductType> list=productTypeService.productTypeTreeList();
		map.put("productTypeTreeList",list);
		map.put("productType",productType);
		ModelAndView mv = new ModelAndView(productTypeListView,map);		
		return mv;		
	}
	
	public ModelAndView deleteProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String[] recordIds=request.getParameterValues("recordId");
		productTypeService.deleteProductTypes(recordIds);
		View v = new RedirectView(listProductTypes);
		ModelAndView mv = new ModelAndView(v);
		return mv;	
	}
	
	/**
	 * 修改一个商品类别
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView editProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String recordId=request.getParameter("recordId");		
		ProductType productType=productTypeService.getProductType(recordId);
		ModelAndView mv = new ModelAndView(editProductTypeForm, "command",productType);		
		return mv;
	}
	
	/**
	 * 更新一个数据库记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView updateProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		ProductType productType = new  ProductType();
		ModelAndView view = new ModelAndView();		
		if (!validation(request, productType, view)) { //校验失败
			view.setViewName(editProductTypeForm);//跳转到修改页
			view.addObject("command",productType);			
		} else { //校验成功			
			//更新数据库
			productTypeService.updateProductType(productType);
			View v = new RedirectView(listProductTypes);
			view.setView(v);			
		}
		return view;
	}
	
	/**
	 * 用于综合查询页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView queryProductType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryProductType queryProductType=new QueryProductType();		
		bind(request, queryProductType);
		ModelAndView mv;
		if((queryProductType.getClassLevel()==null)&&(queryProductType.getGoodsTypeCode()==null)&&(queryProductType.getGoodsTypeName()==null)&&(queryProductType.getParentName()==null)){
			mv = new ModelAndView(queryProductTypeView,"command",queryProductType);
		}else{
			List<PProductType> list=productTypeService.queryProductType(queryProductType);			
			Map map=new HashMap();
			map.put("command",queryProductType);
			map.put("productTypes",list);			
			mv = new ModelAndView(queryProductTypeView,map);
		}		
		return mv;
	}
	
	public ModelAndView qByCodeProductType(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			PProductType queryProductType=new PProductType();		
			bind(request, queryProductType);
			ModelAndView mv;
			if(!StringUtils.isEmpty(queryProductType.getGoodsTypeCode()))
				queryProductType.setGoodsTypeCode(new String(queryProductType.getGoodsTypeCode().getBytes("iso-8859-1"),"utf-8"));
			
			PageWraper pw = this.productTypeService.productTypeList(queryProductType);
			if(pw!=null)
				queryProductType.setProductTypeList(pw.getResult());
			
			return new ModelAndView("productType/qProductTypeResult","model",queryProductType);
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("dataAccessFail");
		}
	}
}
