package com.zhuozhuo.mis.po;

import java.math.BigDecimal;
import java.util.List;

import com.zhuozhuo.mis.util.BaseModel;

public class ScmDiaoBo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -211590362894996258L;
	private String id;
	private String[] aid;
	private String sheetid;	
	private String makerid;
	private String makerName;
	private TAdmUser makerUser=new TAdmUser();
	private String createdate;
	private int sheetstate;
	private String providerid;
	private String providerName;
	private PProvider provider = new PProvider();
	private List<PProvider> providerList;
	private String departmentid;
	private TAdmDept dept = new TAdmDept();
	private List<TAdmDept> deptList;
	private String userid;
	private String userName;
	private TAdmUser user;
	private List<TAdmUser> userList; 
	private int sumqty;
	private BigDecimal sumamt;
	private String memo;
	private int stockopentype;
	private String bargaincode;
	private String paydate;
	private String barnid;
	private ScmBarnType barn;
	private List<ScmBarnType> barnTypeList;
	private String transmodecode;
	private String todate;
	private String toaddress;
	private String auditid;
	private String auditName;
	private TAdmUser auditUser;
	private String auditdate;
	private String srcsheetid;
	private String remark;
	private int isreceive;
	private String pretodate;
	private String phone;
	private String address;
	private ScmDiaoBoDetail DiaoBoDetail = new ScmDiaoBoDetail();
	private List<ScmDiaoBoDetail> DiaoBoDetailList;
	private List<ScmDiaoBo> diaoBoList;	
	private String minAmt;
	private String maxAmt;
	private String prodCond;
	private String productName;
	private String productType;
	private String productTypeName;
	private String qsheetid;
	private String businessman;
	private String barnPhone;
	private String barnAddress;
	private String storageInId;
	
	public String getSheetid() {
		return sheetid;
	}
	public void setSheetid(String sheetid) {
		this.sheetid = sheetid;
	}
	
	public TAdmUser getMakerUser() {
		return makerUser;
	}
	public void setMakerUser(TAdmUser makerUser) {
		this.makerUser = makerUser;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getSheetstate() {
		return sheetstate;
	}
	public void setSheetstate(int sheetstate) {
		this.sheetstate = sheetstate;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	
	public TAdmDept getDept() {
		return dept;
	}
	public void setDept(TAdmDept dept) {
		this.dept = dept;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public TAdmUser getUser() {
		return user;
	}
	public void setUser(TAdmUser user) {
		this.user = user;
	}
	public int getSumqty() {
		return sumqty;
	}
	public void setSumqty(int sumqty) {
		this.sumqty = sumqty;
	}
	public BigDecimal getSumamt() {
		return sumamt;
	}
	public void setSumamt(BigDecimal sumamt) {
		this.sumamt = sumamt;
	}
	public int getStockopentype() {
		return stockopentype;
	}
	public void setStockopentype(int stockopentype) {
		this.stockopentype = stockopentype;
	}
	public String getBargaincode() {
		return bargaincode;
	}
	public void setBargaincode(String bargaincode) {
		this.bargaincode = bargaincode;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getBarnid() {
		return barnid;
	}
	public void setBarnid(String barnid) {
		this.barnid = barnid;
	}
	public ScmBarnType getBarn() {
		return barn;
	}
	public void setBarn(ScmBarnType barn) {
		this.barn = barn;
	}
	public String getTransmodecode() {
		return transmodecode;
	}
	public void setTransmodecode(String transmodecode) {
		this.transmodecode = transmodecode;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getToaddress() {
		return toaddress;
	}
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	public String getAuditid() {
		return auditid;
	}
	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}
	public TAdmUser getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(TAdmUser auditUser) {
		this.auditUser = auditUser;
	}
	public String getAuditdate() {
		return auditdate;
	}
	public void setAuditdate(String auditdate) {
		this.auditdate = auditdate;
	}
	public String getSrcsheetid() {
		return srcsheetid;
	}
	public void setSrcsheetid(String srcsheetid) {
		this.srcsheetid = srcsheetid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<TAdmDept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<TAdmDept> deptList) {
		this.deptList = deptList;
	}
	public PProvider getProvider() {
		return provider;
	}
	public void setProvider(PProvider provider) {
		this.provider = provider;
	}
	public List<PProvider> getProviderList() {
		return providerList;
	}
	public void setProviderList(List<PProvider> providerList) {
		this.providerList = providerList;
	}
	
	public int getIsreceive() {
		return isreceive;
	}
	public void setIsreceive(int isreceive) {
		this.isreceive = isreceive;
	}
	public String getPretodate() {
		return pretodate;
	}
	public void setPretodate(String pretodate) {
		this.pretodate = pretodate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMakerid() {
		return makerid;
	}
	public void setMakerid(String makerid) {
		this.makerid = makerid;
	}
	public List<ScmBarnType> getBarnTypeList() {
		return barnTypeList;
	}
	public void setBarnTypeList(List<ScmBarnType> barnTypeList) {
		this.barnTypeList = barnTypeList;
	}
	public List<TAdmUser> getUserList() {
		return userList;
	}
	public void setUserList(List<TAdmUser> userList) {
		this.userList = userList;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public ScmDiaoBoDetail getDiaoBoDetail() {
		return DiaoBoDetail;
	}
	public void setDiaoBoDetail(ScmDiaoBoDetail DiaoBoDetail) {
		this.DiaoBoDetail = DiaoBoDetail;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	
	public List<ScmDiaoBo> getDiaoBoList() {
		return diaoBoList;
	}
	public void setDiaoBoList(List<ScmDiaoBo> diaoBoList) {
		this.diaoBoList = diaoBoList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getMinAmt() {
		return minAmt;
	}
	public void setMinAmt(String minAmt) {
		this.minAmt = minAmt;
	}
	public String getMaxAmt() {
		return maxAmt;
	}
	public void setMaxAmt(String maxAmt) {
		this.maxAmt = maxAmt;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProdCond() {
		return prodCond;
	}
	public void setProdCond(String prodCond) {
		this.prodCond = prodCond;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public List<ScmDiaoBoDetail> getDiaoBoDetailList() {
		return DiaoBoDetailList;
	}
	public void setDiaoBoDetailList(
			List<ScmDiaoBoDetail> DiaoBoDetailList) {
		this.DiaoBoDetailList = DiaoBoDetailList;
	}
	public String getQsheetid() {
		return qsheetid;
	}
	public void setQsheetid(String qsheetid) {
		this.qsheetid = qsheetid;
	}
	public String getBusinessman() {
		return businessman;
	}
	public void setBusinessman(String businessman) {
		this.businessman = businessman;
	}
	public String getBarnPhone() {
		return barnPhone;
	}
	public void setBarnPhone(String barnPhone) {
		this.barnPhone = barnPhone;
	}
	public String getBarnAddress() {
		return barnAddress;
	}
	public void setBarnAddress(String barnAddress) {
		this.barnAddress = barnAddress;
	}
	
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getAid() {
		return aid;
	}
	public void setAid(String[] aid) {
		this.aid = aid;
	}
	public String getStorageInId() {
		return storageInId;
	}
	public void setStorageInId(String storageInId) {
		this.storageInId = storageInId;
	}
	
}
