package com.zhuozhuo.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;

import com.zhuozhuo.mis.po.ScmStockOrder;
import com.zhuozhuo.mis.po.report.ScmStorage;

public class ExcelUtil {
	private static Logger log = Logger.getLogger(ExcelUtil.class);
	
	public static WritableWorkbook getWorkBook(HttpServletResponse res){
		try {
			OutputStream os = res.getOutputStream();			
			res.reset();// 清空输出流
			res.setHeader("Content-disposition", "filename=" + "output.xls");// 设定输出文件头
			res.setContentType("application/msexcel");// 定义输出类型
	
			return Workbook.createWorkbook(os);
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("ExcelUtil getWorkBook exception, e==="+e.getMessage());
			
			return null;
		}
	}
	
	/**
	 * 获取excel指定sheet
	 * @param workBook
	 * @param i
	 * @return
	 */
	public static WritableSheet getSheet(WritableWorkbook workBook,int i){
		return workBook.createSheet("sheet1",i);
	}
	
	/**
	 * 获取单元样式
	 * @param fontsize
	 * @return
	 */
	public static WritableCellFormat getCellFormat(int fontsize){
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"),fontsize, WritableFont.NO_BOLD);
		WritableCellFormat wcf = new WritableCellFormat(font);
		try {
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
		} catch (WriteException e) {			
			e.printStackTrace();
			log.error("ExcelUtil getCellFormat exception,e==="+e.getMessage());
		}
		
		return wcf;
	}
	
	/**
	 * 添加单元格
	 * @param sheet
	 * @param col
	 * @param row
	 * @param content
	 * @param wcf
	 */
	public static void addCell(WritableSheet sheet,int col,int row,String content,WritableCellFormat wcf){
		Label label = new Label(col,row,content,wcf);
		try {
			sheet.addCell(label);
		} catch (RowsExceededException e) {			
			e.printStackTrace();
		} catch (WriteException e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出进销存汇总报表
	 * @param o
	 * @param res
	 */
	public static void exportStoragetToExcel(ScmStorage o,HttpServletResponse res){		
		try {
			log.info("write storage list to excel start");
			WritableWorkbook workBook = ExcelUtil.getWorkBook(res);
			WritableSheet sheet = ExcelUtil.getSheet(workBook, 1);
			WritableCellFormat wcf = ExcelUtil.getCellFormat(18);
			
			sheet.mergeCells(0,0, 15, 0);
			ExcelUtil.addCell(sheet, 0, 0, "进销存商品汇总表", wcf);
			
			wcf = ExcelUtil.getCellFormat(10);
			addCell(sheet, 0, 1, "开始日期", wcf);
			addCell(sheet, 1, 1, o.getStartTime(), wcf);			
			addCell(sheet, 2, 1, "结束日期", wcf);
			addCell(sheet, 3, 1, o.getEndTime(), wcf);
			addCell(sheet, 4, 1, "制单人", wcf);
			addCell(sheet, 5, 1, "张三", wcf);					
			sheet.mergeCells(0, 2, 0, 3);
			addCell(sheet, 0, 2, "序号", wcf);			
			sheet.mergeCells(1, 2, 3, 2);
			addCell(sheet, 1, 2, "商品信息", wcf);		
			sheet.mergeCells(4, 2, 5, 2);
			addCell(sheet, 4, 2, "采购信息", wcf);		
			sheet.mergeCells(6, 2, 7, 2);
			addCell(sheet, 6, 2, "销售信息", wcf);
			sheet.mergeCells(8, 2, 9, 2);
			addCell(sheet, 8, 2, "总库存信息", wcf);
			sheet.mergeCells(10, 2, 11, 2);
			addCell(sheet, 10, 2, "入库信息", wcf);
			sheet.mergeCells(12, 2, 13, 2);
			addCell(sheet, 12, 2, "发库信息", wcf);
			sheet.mergeCells(14, 2, 15, 2);
			addCell(sheet, 14, 2, "退货信息", wcf);
			
			addCell(sheet, 1, 3, "商品编码", wcf);
			addCell(sheet, 2, 3, "商品名称", wcf);
			addCell(sheet, 3, 3, "商品类型", wcf);
			addCell(sheet, 4, 3, "数量", wcf);
			addCell(sheet, 5, 3, "金额", wcf);
			addCell(sheet, 6, 3, "数量", wcf);
			addCell(sheet, 7, 3, "金额", wcf);
			addCell(sheet, 8, 3, "数量", wcf);
			addCell(sheet, 9, 3, "金额", wcf);
			addCell(sheet, 10, 3, "数量", wcf);
			addCell(sheet, 11, 3, "金额", wcf);
			addCell(sheet, 12, 3, "数量", wcf);
			addCell(sheet, 13, 3, "金额", wcf);
			addCell(sheet, 14, 3, "数量", wcf);
			addCell(sheet, 15, 3, "金额", wcf);
			
			List<ScmStorage> storageList = o.getReportList();
			int i=0;
			for(ScmStorage storage : storageList){
				i++;
				addCell(sheet, 0, i+3, String.valueOf(i), wcf);
				addCell(sheet, 1, i+3, storage.getProductcode(), wcf);
				addCell(sheet, 2, i+3, storage.getProductname(), wcf);
				addCell(sheet, 3, i+3, storage.getProductTypeName(), wcf);
				addCell(sheet, 4, i+3, String.valueOf(storage.getCgqty()), wcf);  
				addCell(sheet, 5, i+3, String.valueOf(storage.getCgamt()), wcf);  
				addCell(sheet, 6, i+3, String.valueOf(storage.getXsqty()), wcf);  
				addCell(sheet, 7, i+3, String.valueOf(storage.getXsamt()), wcf);  
				addCell(sheet, 8, i+3, String.valueOf(storage.getHjqty()), wcf);  
				addCell(sheet, 9, i+3, String.valueOf(storage.getHjamt()), wcf);  
				addCell(sheet, 10, i+3,String.valueOf(storage.getRkqty()), wcf);  
				addCell(sheet, 11, i+3,String.valueOf(storage.getRkamt()), wcf);  
				addCell(sheet, 12, i+3,String.valueOf(storage.getCkqty()), wcf);  
				addCell(sheet, 13, i+3,String.valueOf(storage.getCkamt()), wcf);  
				addCell(sheet, 14, i+3,String.valueOf(storage.getThqty()), wcf);  
				addCell(sheet, 15, i+3,String.valueOf(storage.getThamt()), wcf);	
			}
			
			addCell(sheet, 0, i+4,"合计", wcf);
			sheet.mergeCells(1, i+4, 3, i+4);

			addCell(sheet, 4, i+4, String.valueOf(o.getHjcgqty()), wcf);          
			addCell(sheet, 5, i+4, String.valueOf(o.getHjcgamt()), wcf);  
			addCell(sheet, 6, i+4, String.valueOf(o.getXsqty()), wcf);  
			addCell(sheet, 7, i+4, String.valueOf(o.getXsamt()), wcf);  
			addCell(sheet, 8, i+4, String.valueOf(o.getHjzqty()), wcf);  
			addCell(sheet, 9, i+4, String.valueOf(o.getHjzamt()), wcf);  
			addCell(sheet, 10, i+4,String.valueOf(o.getHjrkqty()), wcf);  
			addCell(sheet, 11, i+4,String.valueOf(o.getHjrkamt()), wcf);  
			addCell(sheet, 12, i+4,String.valueOf(o.getHjckqty()), wcf);  
			addCell(sheet, 13, i+4,String.valueOf(o.getHjckamt()), wcf);  
			addCell(sheet, 14, i+4,String.valueOf(o.getHjthqty()), wcf);  
			addCell(sheet, 15, i+4,String.valueOf(o.getHjthamt()), wcf); 
			
			workBook.write();		
			workBook.close();
		
			log.info("write storage list to excel end");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("write storage list to excel exception");	
		}
	}
	
	/**
	 * 导出分仓汇总报表
	 * @param o
	 * @param res
	 */
	public static void exportBarnTypeStorageToExcel(ScmStorage o,HttpServletResponse res){		
		try {
			log.info("write exportBarnTypeStorageToExcel start");
			WritableWorkbook workBook = ExcelUtil.getWorkBook(res);
			WritableSheet sheet = ExcelUtil.getSheet(workBook, 1);
			WritableCellFormat wcf = ExcelUtil.getCellFormat(18);
			
			sheet.mergeCells(0,0, 7, 0);
			ExcelUtil.addCell(sheet, 0, 0, "分仓商品汇总表", wcf);
			
			wcf = ExcelUtil.getCellFormat(10);
			addCell(sheet, 0, 1, "开始日期", wcf);
			addCell(sheet, 1, 1, o.getStartTime(), wcf);			
			addCell(sheet, 2, 1, "结束日期", wcf);
			addCell(sheet, 3, 1, o.getEndTime(), wcf);
			addCell(sheet, 4, 1, "制单人", wcf);
			addCell(sheet, 5, 1, "张三", wcf);	
						
			addCell(sheet, 0, 2, "序号", wcf);						
			addCell(sheet, 1, 2, "仓库名称", wcf);					
			addCell(sheet, 2, 2, "商品名称", wcf);					
			addCell(sheet, 3, 2, "商品编码", wcf);			
			addCell(sheet, 4, 2, "商品规格", wcf);			
			addCell(sheet, 5, 2, "单位", wcf);
			addCell(sheet, 6, 2, "数量", wcf);
			addCell(sheet, 7, 2, "金额", wcf);
							
			List<ScmStorage> storageList = o.getReportList();
			int i=0;
			for(ScmStorage storage : storageList){				
				addCell(sheet, 0, i+3, String.valueOf(i+1), wcf);
				
				if(StringUtils.isEmpty(storage.getBarntypename())){
					addCell(sheet, 1, i+3, "合计", wcf);
				}
				else{
					if(StringUtils.isEmpty(storage.getProductname()))
						addCell(sheet, 1, i+3, "小计", wcf);
					else
						addCell(sheet, 1, i+3, storage.getBarntypename(), wcf);
				}
				
				addCell(sheet, 2, i+3, storage.getProductname(), wcf);
				addCell(sheet, 3, i+3, storage.getProductcode(), wcf);
				addCell(sheet, 4, i+3, storage.getProductspecs(), wcf);  
				addCell(sheet, 5, i+3, storage.getProductunit(), wcf);  
				addCell(sheet, 6, i+3, String.valueOf(storage.getQty()), wcf);  
				addCell(sheet, 7, i+3, String.valueOf(storage.getAmt()), wcf);  
				
				i++;
			}
			
			workBook.write();		
			workBook.close();
		
			log.info("write exportBarnTypeStorageToExcel end");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("write exportBarnTypeStorageToExcel exception");	
		}
	}
	
	/**
	 * 导出商品类型汇总报表
	 * @param o
	 * @param res
	 */
	public static void exportProductTypeStorageToExcel(ScmStorage o,HttpServletResponse res){		
		try {
			log.info("write exportProductTypeStorageToExcel start");
			WritableWorkbook workBook = ExcelUtil.getWorkBook(res);
			WritableSheet sheet = ExcelUtil.getSheet(workBook, 1);
			WritableCellFormat wcf = ExcelUtil.getCellFormat(18);
			
			sheet.mergeCells(0,0, 5, 0);
			ExcelUtil.addCell(sheet, 0, 0, "商品类型汇总表", wcf);
			
			wcf = ExcelUtil.getCellFormat(10);
			addCell(sheet, 0, 1, "开始日期", wcf);
			addCell(sheet, 1, 1, o.getStartTime(), wcf);			
			addCell(sheet, 2, 1, "结束日期", wcf);
			addCell(sheet, 3, 1, o.getEndTime(), wcf);
			addCell(sheet, 4, 1, "制单人", wcf);
			addCell(sheet, 5, 1, "张三", wcf);	
						
			addCell(sheet, 0, 2, "序号", wcf);						
			addCell(sheet, 1, 2, "商品类型", wcf);					
			addCell(sheet, 2, 2, "上期结存金额", wcf);					
			addCell(sheet, 3, 2, "本期收入金额", wcf);			
			addCell(sheet, 4, 2, "本期发出金额", wcf);			
			addCell(sheet, 5, 2, "本期结存金额", wcf);
			
							
			List<ScmStorage> storageList = o.getReportList();
			int i=0;
			for(ScmStorage storage : storageList){		
				i++;
				addCell(sheet, 0, i+2, String.valueOf(i), wcf);							
				addCell(sheet, 1, i+2, storage.getProductTypeName(), wcf);
				addCell(sheet, 2, i+2, String.valueOf(storage.getLastAmt()), wcf);
				addCell(sheet, 3, i+2, String.valueOf(storage.getInAmt()), wcf);  
				addCell(sheet, 4, i+2, String.valueOf(storage.getOutAmt()), wcf);
				addCell(sheet, 5, i+2, String.valueOf(storage.getLastAmt()+storage.getInAmt()+storage.getOutAmt()), wcf);  				
			}
			
			sheet.mergeCells(0, i+3, 1, i+3);
			addCell(sheet, 0, i+3,"合计", wcf);			        
			addCell(sheet, 2, i+3, String.valueOf(o.getHjLastAmt()), wcf);  
			addCell(sheet, 3, i+3, String.valueOf(o.getHjInAmt()), wcf);  
			addCell(sheet, 4, i+3, String.valueOf(o.getHjOutAmt()), wcf);  
			addCell(sheet, 5, i+3, String.valueOf(o.getHjLastAmt()+o.getHjInAmt()+o.getHjOutAmt()), wcf);  
						
			workBook.write();		
			workBook.close();
		
			log.info("write exportBarnTypeStorageToExcel end");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("write exportBarnTypeStorageToExcel exception");	
		}
	}
	
	public static void exportStockOrderToExcel(ScmStockOrder o ,HttpServletResponse res){
		try {
			log.info("write exportStockOrderToExcel start");
			WritableWorkbook workBook = ExcelUtil.getWorkBook(res);
			WritableSheet sheet = ExcelUtil.getSheet(workBook, 1);
			WritableCellFormat wcf = ExcelUtil.getCellFormat(18);
			
			sheet.mergeCells(0,0, 11, 0);
			ExcelUtil.addCell(sheet, 0, 0, "采购单表", wcf);
			
			wcf=ExcelUtil.getCellFormat(10);	
			addCell(sheet, 0, 1, "序号", wcf);						
			addCell(sheet, 1, 1, "单据编号", wcf);					
			addCell(sheet, 2, 1, "供应商", wcf);					
			addCell(sheet, 3, 1, "经办人", wcf);			
			addCell(sheet, 4, 1, "本单金额 ", wcf);			
			addCell(sheet, 5, 1, "本制单人", wcf);
			addCell(sheet, 6, 1, "单据状态", wcf);
			addCell(sheet, 7, 1, "单据来源编号", wcf);
			addCell(sheet, 8, 1, "开单日期", wcf);
			addCell(sheet, 9, 1, "支付日期", wcf);
			addCell(sheet, 10, 1, "预计交货日期", wcf);
			addCell(sheet, 11, 1, "交货日期", wcf);
			
							
			List<ScmStockOrder> storageList = o.getStockOrderList();
			int i=0;
			for(ScmStockOrder stockOrder : storageList){		
				i++;
				addCell(sheet, 0, i+1, String.valueOf(i), wcf);							
				addCell(sheet, 1, i+1, stockOrder.getSheetid(), wcf);
				addCell(sheet, 2, i+1, stockOrder.getProviderName(), wcf);
				addCell(sheet, 3, i+1, stockOrder.getUserName(), wcf);  
				addCell(sheet, 4, i+1, String.valueOf(stockOrder.getSumamt()), wcf);
				addCell(sheet, 5, i+1, stockOrder.getMakerName(), wcf);  	
				if(stockOrder.getSheetstate()==1)
					addCell(sheet, 6, i+1, "已审核", wcf);
				else
					addCell(sheet, 6, i+1, "未审核", wcf);
				
				addCell(sheet, 7, i+1, stockOrder.getSrcsheetid(), wcf);  
				addCell(sheet, 8, i+1, stockOrder.getCreatedate(), wcf);  
				addCell(sheet, 9, i+1, stockOrder.getPaydate(), wcf);  
				addCell(sheet, 10, i+1, stockOrder.getPretodate(), wcf);  
				addCell(sheet, 11, i+1, stockOrder.getTodate(), wcf);  
			}
					
			workBook.write();		
			workBook.close();
		
			log.info("write exportStockOrderToExcel end");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("write exportStockOrderToExcel exception");	
		}
	}
}
