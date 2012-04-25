package com.zhuozhuo.mis.po.report;

import java.util.List;

import com.zhuozhuo.mis.po.ScmBarnType;
import com.zhuozhuo.mis.util.BaseModel;

public class ScmStorage extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9164191675186462118L;
	private String startTime;
	private String endTime;
	private String productcode;
	private String productid;
	private String productname;
	private String productspecs;
	private String productunit;
	private String productTypeName;
	private String productType;
	private String productTypes;
	private long recordid;
	private String barntypecode;
	private String barntypename;
	private String barnid;	
	private String barnids;
	private long rkqty;                                               
	private double rkamt;
	private long ckqty;
	private double ckamt;
	private long thqty;
	private double thamt;
	private long cgqty;
	private double cgamt;
	private long xsqty;
	private double xsamt; 
	private long hjqty;
	private double hjamt;
	
	private long hjrkqty;
	private double hjrkamt;
	private long hjckqty;
	private double hjckamt;
	private long hjthqty;
	private double hjthamt;
	private long hjcgqty;
	private double hjcgamt;
	private long hjxsqty;
	private double hjxsamt;
	private long hjzqty;
	private double hjzamt;
	private long qty;
	private double amt;
	
	private double lastAmt;
	private double inAmt;
	private double outAmt;
	private double hjLastAmt;
	private double hjInAmt;
	private double hjOutAmt;
	
	private List<ScmStorage> reportList;
	private List<ScmBarnType> barnTypeList;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public long getRecordid() {
		return recordid;
	}
	public void setRecordid(long recordid) {
		this.recordid = recordid;
	}
	public String getBarntypecode() {
		return barntypecode;
	}
	public void setBarntypecode(String barntypecode) {
		this.barntypecode = barntypecode;
	}
	public String getBarnid() {
		return barnid;
	}
	public void setBarnid(String barnid) {
		this.barnid = barnid;
	}
	public long getRkqty() {
		return rkqty;
	}
	public void setRkqty(long rkqty) {
		this.rkqty = rkqty;
	}
	public double getRkamt() {
		return rkamt;
	}
	public void setRkamt(double rkamt) {
		this.rkamt = rkamt;
	}
	public long getCkqty() {
		return ckqty;
	}
	public void setCkqty(long ckqty) {
		this.ckqty = ckqty;
	}
	public double getCkamt() {
		return ckamt;
	}
	public void setCkamt(double ckamt) {
		this.ckamt = ckamt;
	}
	public long getThqty() {
		return thqty;
	}
	public void setThqty(long thqty) {
		this.thqty = thqty;
	}
	public double getThamt() {
		return thamt;
	}
	public void setThamt(double thamt) {
		this.thamt = thamt;
	}
	public long getCgqty() {
		return cgqty;
	}
	public void setCgqty(long cgqty) {
		this.cgqty = cgqty;
	}
	public double getCgamt() {
		return cgamt;
	}
	public void setCgamt(double cgamt) {
		this.cgamt = cgamt;
	}
	public long getXsqty() {
		return xsqty;
	}
	public void setXsqty(long xsqty) {
		this.xsqty = xsqty;
	}
	public double getXsamt() {
		return xsamt;
	}
	public void setXsamt(double xsamt) {
		this.xsamt = xsamt;
	}
	public List<ScmStorage> getReportList() {
		return reportList;
	}
	public void setReportList(List<ScmStorage> reportList) {
		this.reportList = reportList;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public List<ScmBarnType> getBarnTypeList() {
		return barnTypeList;
	}
	public void setBarnTypeList(List<ScmBarnType> barnTypeList) {
		this.barnTypeList = barnTypeList;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public long getHjqty() {
		return hjqty;
	}
	public void setHjqty(long hjqty) {
		this.hjqty = hjqty;
	}
	public double getHjamt() {
		return hjamt;
	}
	public void setHjamt(double hjamt) {
		this.hjamt = hjamt;
	}
	public long getHjrkqty() {
		return hjrkqty;
	}
	public void setHjrkqty(long hjrkqty) {
		this.hjrkqty = hjrkqty;
	}
	public double getHjrkamt() {
		return hjrkamt;
	}
	public void setHjrkamt(double hjrkamt) {
		this.hjrkamt = hjrkamt;
	}
	public long getHjckqty() {
		return hjckqty;
	}
	public void setHjckqty(long hjckqty) {
		this.hjckqty = hjckqty;
	}
	public double getHjckamt() {
		return hjckamt;
	}
	public void setHjckamt(double hjckamt) {
		this.hjckamt = hjckamt;
	}
	public long getHjthqty() {
		return hjthqty;
	}
	public void setHjthqty(long hjthqty) {
		this.hjthqty = hjthqty;
	}
	public double getHjthamt() {
		return hjthamt;
	}
	public void setHjthamt(double hjthamt) {
		this.hjthamt = hjthamt;
	}
	public long getHjcgqty() {
		return hjcgqty;
	}
	public void setHjcgqty(long hjcgqty) {
		this.hjcgqty = hjcgqty;
	}
	public double getHjcgamt() {
		return hjcgamt;
	}
	public void setHjcgamt(double hjcgamt) {
		this.hjcgamt = hjcgamt;
	}
	public long getHjxsqty() {
		return hjxsqty;
	}
	public void setHjxsqty(long hjxsqty) {
		this.hjxsqty = hjxsqty;
	}
	public double getHjxsamt() {
		return hjxsamt;
	}
	public void setHjxsamt(double hjxsamt) {
		this.hjxsamt = hjxsamt;
	}
	public long getHjzqty() {
		return hjzqty;
	}
	public void setHjzqty(long hjzqty) {
		this.hjzqty = hjzqty;
	}
	public double getHjzamt() {
		return hjzamt;
	}
	public void setHjzamt(double hjzamt) {
		this.hjzamt = hjzamt;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getProductspecs() {
		return productspecs;
	}
	public void setProductspecs(String productspecs) {
		this.productspecs = productspecs;
	}
	public String getProductunit() {
		return productunit;
	}
	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}
	public String getBarntypename() {
		return barntypename;
	}
	public void setBarntypename(String barntypename) {
		this.barntypename = barntypename;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public String getBarnids() {
		return barnids;
	}
	public void setBarnids(String barnids) {
		this.barnids = barnids;
	}
	public String getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(String productTypes) {
		this.productTypes = productTypes;
	}
	public double getLastAmt() {
		return lastAmt;
	}
	public void setLastAmt(double lastAmt) {
		this.lastAmt = lastAmt;
	}
	public double getInAmt() {
		return inAmt;
	}
	public void setInAmt(double inAmt) {
		this.inAmt = inAmt;
	}
	public double getOutAmt() {
		return outAmt;
	}
	public void setOutAmt(double outAmt) {
		this.outAmt = outAmt;
	}
	public double getHjLastAmt() {
		return hjLastAmt;
	}
	public void setHjLastAmt(double hjLastAmt) {
		this.hjLastAmt = hjLastAmt;
	}
	public double getHjInAmt() {
		return hjInAmt;
	}
	public void setHjInAmt(double hjInAmt) {
		this.hjInAmt = hjInAmt;
	}
	public double getHjOutAmt() {
		return hjOutAmt;
	}
	public void setHjOutAmt(double hjOutAmt) {
		this.hjOutAmt = hjOutAmt;
	}
}
