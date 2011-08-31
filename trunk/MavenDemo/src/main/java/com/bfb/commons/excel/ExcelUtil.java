package com.bfb.commons.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelUtil {
	/**
	 * 生成excel文件
	 * @param fileName	excel文件名
	 * @param titles	excel文件title
	 * @param contents	excel文件正文内容，每条记录转成string数组
	 * @param res		httpservletresponse
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void createExcel(String fileName, String[] titles,
			List<String[]> contents,HttpServletResponse res) throws IOException, RowsExceededException,
			WriteException {
		int sheetId = 1;
		WritableWorkbook wwb = getWritableWorkbook(res,fileName);
		WritableSheet ws = getWritableSheet(wwb, sheetId, titles);
		int rowNum=0;
		for (int i = 0; i < contents.size(); i++) {
			rowNum++;
			addLabel(ws, rowNum, contents.get(i));
		}
		
		wwb.write();
		wwb.close();
	}
	
	/**
	 * 获取WritableWorkbook对象
	 * @param res	httpservletresponse
	 * @param fileName	excel文件名
	 * @return
	 * @throws IOException
	 */
	public static WritableWorkbook getWritableWorkbook(HttpServletResponse res,String fileName)
		throws IOException {
		res.setCharacterEncoding("gbk");
		OutputStream os = res.getOutputStream();// 取得输出流
		res.reset();// 清空输出流
		res.setHeader("Content-disposition", "filename=" + fileName);// 设定输出文件头
		res.setContentType("application/msexcel");// 定义输出类型
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		return wwb;
	}
	
	/**
	 * 写excel文件的cell单元格内容
	 * @param ws	excel中sheet
	 * @param row	行号
	 * @param labels	excel正文内容
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static void addLabel(WritableSheet ws, int row, String[] labels)
		throws RowsExceededException, WriteException {
		for (int i = 0; i < labels.length; i++) {
			Label label = new Label(i, row, labels[i]);
			ws.addCell(label);
		}
	}
	
	/**
	 * 获取WritableSheet对象
	 * @param wwb	WritableWorkbook对象
	 * @param sheetId	sheet名称
	 * @param titles	excel文件的title标题
	 * @return
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public static WritableSheet getWritableSheet(WritableWorkbook wwb, int sheetId,
		String[] titles) throws RowsExceededException, WriteException {
		WritableSheet ws = wwb.createSheet("data" + sheetId, sheetId);
		addLabel(ws, 0, titles);
		return ws;
	}
	
	 /**
	 * 读取excel文件内容
	 * @param fileName	文件路径
	 * @return	返回所有sheet中内容组成的string数组
	 */
	public static List<String[][]> readXsl(String fileName){
		Workbook workbook = null;
		List<String[][]> allSheetContent = new ArrayList<String[][]>();
		try {
			workbook = Workbook.getWorkbook(new File(fileName));
			Sheet[] sheets = workbook.getSheets();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
			
			for(Sheet sheet : sheets){
				Cell cell = null;
				int columnCount=sheet.getColumns();
				int rowCount=sheet.getRows();
				String[][] contents = new String[rowCount][columnCount];
				for (int i = 0; i <rowCount; i++) {
				   for (int j = 0; j <columnCount; j++) {
				       cell=sheet.getCell(j, i);
				       if(cell.getType()==CellType.NUMBER){
				    	   contents[i][j] = String.valueOf(((NumberCell)cell).getValue());
				       }
				       else if(cell.getType()==CellType.DATE){
				    	   contents[i][j] = sdf.format(((DateCell)cell).getDate());
				       }
				       else{
				    	   contents[i][j] = cell.getContents();
				       }
				   }
				}
				
				if(rowCount>0)
					allSheetContent.add(contents);
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			workbook.close();	
		}
		
		return allSheetContent;
	}
	
	
	public static void main(String[] args){
		readXsl("d:\\test.xls");
	}
}
