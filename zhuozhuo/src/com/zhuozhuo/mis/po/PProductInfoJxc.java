package com.zhuozhuo.mis.po;

import java.io.Serializable;
import java.util.Date;

public class PProductInfoJxc implements Serializable{	
	private static final long serialVersionUID = -3555469382294946916L;
	//商品id，主键，
	private Long id ;	//商品id，主键，序列(jxc)（seq_product_list）	number(10)	10
	//商品编码，
	private String productCode;//商品编码，货号(jxc)	varchar2(20)	20
	//商品名称
	private String productName;//商品名称(jxc)	varchar2(100)	100
	//分类#商品等级
	private Integer productClass ;//分类#商品等级#(jxc) number(1)	1
	//添加人员
	private String createBy;//添加人员(jxc)	varchar2(40)	40
	//添加时间
	private Date createTime;//添加时间(jxc)	date
	//产品单位
	private String productUnit;//产品单位(jxc)	varchar2(100)
	//产品规格
	private String productSpecs;//产品规格(jxc)	varchar2(50)	50
	//产品类型
	private String productType;//产品类型(jxc)	varchar2(600)	600
	//产品性能
	private String productProperty;//产品性能(jxc)	varchar2(50)	50
	//产品重量
	private Double productWeight ;//产品重量(jxc)	number	
	//产品体积
	private Double productVolume ;//产品体积#产品pk商品#(jxc)	number
	//商品颜色
	private String productCorlor;//商品颜色(jxc)	varchar2(50)	50
	//产品尺寸
	private String porductSize;//产品尺寸(jxc)	varchar2(50)	50
	//实际库存
	private Long factStorage ;//实际库存(jxc)	number	
	//成本计算
	private String costCalculate;//成本计算(jxc)	varchar2(50)	50
	//商品条码
	private String productentry;//商品条码(jxc)	varchar2(50)	50
	//称重编码
	private String weightilyCode;//称重编码(jxc)	varchar2(50)	50
	//是否停用
	private Integer ifStoped ;//是否停用(jxc)	number	
	//最小订货量
	private Long leastOrder ;//最小订货量(jxc)	number	
	//提前量
	private Long aheadDay ;//提前量(jxc)	number
	//批次标记
	private String batchMark;//批次标记(jxc)	varchar2(50)	50
	//生产日期
	private String produceDate;//生产日期(jxc)	date
	//产品增值
	private Double productIncrement ;//产品增值(jxc)	number	
	//有效期
	private Date availability;//有效期(jxc)	date	
	//进价
	private Double pricePurchase ;//进价(jxc)	number(15,3)	15
	//成本价
	private Double costPrice ;//成本价(jxc)	number	
	//销售价
	private Double priceNet ;//销售价(jxc)	number(15,3)	15
	//市场价
	private Double priceMarket ;//市场价(jxc)	number(15,3)	15
	//自定义1
	private String define1;//自定义1(jxc)	varchar2(50)	50
	//自定义2
	private String define2;//自定义1(jxc)	varchar2(50)	50
	//自定义4
	private String define3;//自定义1(jxc)	varchar2(50)	50
	//商品详细描述
	private String remark;//商品详细描述(jxc)	clob
	
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductSpecs() {
		return productSpecs;
	}
	public void setProductSpecs(String productSpecs) {
		this.productSpecs = productSpecs;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductProperty() {
		return productProperty;
	}
	public void setProductProperty(String productProperty) {
		this.productProperty = productProperty;
	}
	
	public String getProductCorlor() {
		return productCorlor;
	}
	public void setProductCorlor(String productCorlor) {
		this.productCorlor = productCorlor;
	}
	public String getPorductSize() {
		return porductSize;
	}
	public void setPorductSize(String porductSize) {
		this.porductSize = porductSize;
	}
	
	public String getCostCalculate() {
		return costCalculate;
	}
	public void setCostCalculate(String costCalculate) {
		this.costCalculate = costCalculate;
	}
	public String getProductentry() {
		return productentry;
	}
	public void setProductentry(String productentry) {
		this.productentry = productentry;
	}
	public String getWeightilyCode() {
		return weightilyCode;
	}
	public void setWeightilyCode(String weightilyCode) {
		this.weightilyCode = weightilyCode;
	}	
	
	public String getBatchMark() {
		return batchMark;
	}
	public void setBatchMark(String batchMark) {
		this.batchMark = batchMark;
	}
	
	public Date getAvailability() {
		return availability;
	}
	public void setAvailability(Date availability) {
		this.availability = availability;
	}
	
	public String getDefine1() {
		return define1;
	}
	public void setDefine1(String define1) {
		this.define1 = define1;
	}
	public String getDefine2() {
		return define2;
	}
	public void setDefine2(String define2) {
		this.define2 = define2;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDefine3() {
		return define3;
	}
	public void setDefine3(String define3) {
		this.define3 = define3;
	}
	
	public String getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getProductClass() {
		return productClass;
	}
	public void setProductClass(Integer productClass) {
		this.productClass = productClass;
	}
	public Double getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}
	public Double getProductVolume() {
		return productVolume;
	}
	public void setProductVolume(Double productVolume) {
		this.productVolume = productVolume;
	}
	public Long getFactStorage() {
		return factStorage;
	}
	public void setFactStorage(Long factStorage) {
		this.factStorage = factStorage;
	}
	public Integer getIfStoped() {
		return ifStoped;
	}
	public void setIfStoped(Integer ifStoped) {
		this.ifStoped = ifStoped;
	}
	public Long getLeastOrder() {
		return leastOrder;
	}
	public void setLeastOrder(Long leastOrder) {
		this.leastOrder = leastOrder;
	}
	public Long getAheadDay() {
		return aheadDay;
	}
	public void setAheadDay(Long aheadDay) {
		this.aheadDay = aheadDay;
	}
	public Double getProductIncrement() {
		return productIncrement;
	}
	public void setProductIncrement(Double productIncrement) {
		this.productIncrement = productIncrement;
	}
	public Double getPricePurchase() {
		return pricePurchase;
	}
	public void setPricePurchase(Double pricePurchase) {
		this.pricePurchase = pricePurchase;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getPriceNet() {
		return priceNet;
	}
	public void setPriceNet(Double priceNet) {
		this.priceNet = priceNet;
	}
	public Double getPriceMarket() {
		return priceMarket;
	}
	public void setPriceMarket(Double priceMarket) {
		this.priceMarket = priceMarket;
	}
	
}
