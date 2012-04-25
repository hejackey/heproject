// Function Name: trim
// Function Description: 去除字符串的首尾的空格
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.trim=function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
// Function Name: ltrim
// Function Description: 去除字符串的左侧的空格
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.ltrim=function()
{
    return this.replace(/(^\s*)/g, "");
}
// Function Name: rtrim
// Function Description: 去除字符串的右侧的空格
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.rtrim=function()
{
    return this.replace(/(\s*$)/g, "");
}
// Function Name: len
// Function Description: 返回字符串的实际长度, 一个汉字算2个长度
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.getByteLength=function()
{
    var str=this;
    return str.replace(/[^\x00-\xff]/g, "**").length
}
// Function Name: isValidDate
// Function Description: 判断输入是否是有效的短日期格式 - "YYYY-MM-DD"
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidDate=function()
{
    var result=this.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if(result==null) return false;
    var d=new Date(result[1], result[3]-1, result[4]);
    return (d.getFullYear()==result[1]&&d.getMonth()+1==result[3]&&d.getDate()==result[4]);
}
// Function Name: isValidTime
// Function Description: 判断输入是否是有效的时间格式 - "HH:MM:SS"
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidTime=function()
{
    var result=this.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (result==null) return false;
    if (result[1]>24 || result[3]>=60 || result[4]>=60) return false;
    return true;
}
// Function Name: isValidEmail
// Function Description: 判断输入是否是有效的电子邮件
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidEmail=function()
{
	/* 老版本
	var result=this.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
    if(result==null) return false;
    return true;
	*/
	// 可以存在亚洲文字的email
	var objForm = this;
	if(objForm=="")
		return false;
		
	if(objForm.length>100)
		return false;

	//var re = /[^\x00-\xff]/; 

	var num = 1;
	
	var start = objForm.indexOf("@", 0);
	if(start<1){			
		return false;
	}

	var i=0 ;
	for(; objForm.indexOf("@", start+1)!=-1 ; i++){
		start = objForm.indexOf("@", start+1);
		num++;			
		if(num>1)
			break;
	}
	if(num!=1)
		return false;
		
	var re = /\s*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; 
	return re.test(objForm);			

}
// Function Name: isValidDatetime
// Function Description: 判断输入是否是有效的长日期格式 - "YYYY-MM-DD HH:MM:SS"
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidDatetime=function()
{
    var result=this.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
    
    if(result==null) return false;
    var d= new Date(result[1], result[3]-1, result[4], result[5], result[6], result[7]);
    
    return (d.getFullYear()==result[1]&&(d.getMonth()+1)==result[3]&&d.getDate()==result[4]&&d.getHours()==result[5]&&d.getMinutes()==result[6]&&d.getSeconds()==result[7]);
   
}

// Function Name: isValidDatetime
// Function Description: 判断输入是否是有效的长日期格式 - "YYYY-MM-DD HH:MM"
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidShortDatetime=function()
{
    var result=this.match(/^(\d{1,4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2})$/);
    
    if(result==null) 
    	return false;
    else
    	return true;
    	
    //var d= new Date(result[1], result[2], result[3], result[4], result[5]);    alert(d.getDate()
    //return (d.getFullYear()==result[1]&&d.getMonth()==parseInt(result[2])&&d.getDate()==parseInt(result[3])&&d.getHours()==result[4]&&d.getMinutes()==result[5]);
}

// Function Name: compareTime
// Function Description: 比较2个日期是否相差一天
String.prototype.compareTime=function()
{
var result=this.match(/^(\d{1,4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2})$/);
var oldTime = new Date(result[1], result[2]-1, result[3], result[4], result[5],"01");
var newTime = new Date()

if((newTime.getTime()-oldTime.getTime())/86400000>1)
	return false;
else
	return true;
  
}
// Function Name: compareNowTime
// Function Description: 比较是否大于当前日期
String.prototype.compareNowTime=function()
{
var result=this.match(/^(\d{1,4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2})$/);
var oldTime = new Date(result[1], result[2]-1, result[3], result[4], result[5],"01");
var newTime = new Date()
//alert(oldTime.getTime()/86400000);
//alert(newTime.getTime()/86400000);
if((oldTime.getTime()-newTime.getTime())>0)
	return false;
else
	return true;
  
}
// Function Name: isValidInteger
// Function Description: 判断输入是否是一个整数
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidInteger=function()
{
    var result=this.match(/^(-|\+)?\d+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidPositiveInteger
// Function Description: 判断输入是否是一个正整数
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidPositiveInteger=function()
{
    var result=this.match(/^\d+$/);
    if(result==null) return false;
    if(parseInt(this)>0) return true;
    return false;
}
// Function Name: isValidNegativeInteger
// Function Description: 判断输入是否是一个负整数
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidNegativeInteger=function()
{
    var result=this.match(/^-\d+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidNumber
// Function Description: 判断输入是否是一个数字
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidNumber=function()
{
    return !isNaN(this);
}
// Function Name: isValidLetters
// Function Description: 判断输入是否是一个由 A-Z / a-z 组成的字符串
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidLetters=function()
{
    var result=this.match(/^[a-zA-Z]+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidDigits
// Function Description: 判断输入是否是一个由 0-9 组成的数字
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidDigits=function()
{
    var result=this.match(/^[1-9][0-9]+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidAlphanumeric
// Function Description: 判断输入是否是一个由 0-9 / A-Z / a-z 组成的字符串
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidAlphanumeric=function()
{
    var result=this.match(/^[a-zA-Z0-9]+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidString
// Function Description: 判断输入是否是一个由 0-9 / A-Z / a-z / . / _ 组成的字符串
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidString=function()
{
    var result=this.match(/^[a-zA-Z0-9\s.\-_]+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidStrings
// Function Description: 判断输入是否是一个由 0-9 / A-Z / a-z  / _ 组成的字符串
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidStrings=function()
{
    var result=this.match(/^[a-zA-Z0-9\_]+$/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidPostalcode
// Function Description: 判断输入是否是一个有效的邮政编码
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidPostalcode=function()
{
    var result=this.match(/(^[0-9]{6}$)/);
    if(result==null) return false;
    return true;
}
// Function Name: isValidPhoneNo
// Function Description: 判断输入是否是一个有效的电话号码
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30
String.prototype.isValidPhoneNo=function()
{
	/*
    var result=this.match(/(^[0-9]{1,4}\-[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^\([0-9]{1,4}\)([0-9]{3,4}\)[0-9]{3,8}$)/);
    if(result==null) return false;
    return true;
	*/
	var strNo = this;
	//var result=/(^[0-9]{1,4}\-[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^\([0-9]{1,4}\)\([0-9]{3,4}\)[0-9]{3,8}$)/.test(strNo);
    var re=/(^[0-9]{1,4}\-[0-9]{3,4}\-[0-9]{5,}(\-[0-9]{1,6}){0,1}$)|(^[0-9]{3,4}\-[0-9]{5,}(\-[0-9]{1,6}){0,1}$)|(^[0-9]{5,}(\-[0-9]{1,6}){0,1}$)|(^\([0-9]{3,4}\)[0-9]{5,}(\-[0-9]{1,6}){0,1}$)|(^\([0-9]{1,4}\)\([0-9]{3,4}\)[0-9]{5,}(\-[0-9]{1,6}){0,1}$)/;
    
	return re.test(strNo);
}
// Function Name: isValidMobileNo
// Function Description: 判断输入是否是一个有效的手机号码
// Creation Date: 2005-9-28 15:30
// Last Modify By: Weiwenqi
// Last Modify Date: 2005-09-30

String.prototype.isValidMobileNo=function()
{
	/*
    var result=this.match(/(^\d{0,4}13[0-9]{9}$)/);
    if(result==null) return false;
    return true;
	*/
	var strMobileNo = this;

    if (strMobileNo == "") {
        return false;
    }
    
    var re = /(^[0-9]{1,4}\-{0,1}1\d*$)|(^\([0-9]{1,4}\)1[0-9]*$)|(^1[0-9]*$)/;

	return re.test(strMobileNo);
}

function HTMLEncode(text){
	text = text.replace(/&/g, "&amp;") ;
	text = text.replace(/"/g, "&quot;") ;
	text = text.replace(/</g, "&lt;") ;
	text = text.replace(/>/g, "&gt;") ;
	text = text.replace(/'/g, "&#146;") ;
	text = text.replace(/\ /g,"&nbsp;");
	text = text.replace(/\n/g,"<br>");
	text = text.replace(/\t/g,"&nbsp;&nbsp;&nbsp;&nbsp;");
	return text;
}

/**
  * 方法名：checkDateRange
  * 输入参数：BeginDate,EndDate  _输出类型：boolean
  * 方法说明：比较2个时间大小
  * 版本信息：2005-09-30 _BY_Weiwenqi
  */
function checkTimeRange(BeginDate,EndDate)
{	  
    var strBegindate = BeginDate.substring(0,4)+BeginDate.substring(5,7)+BeginDate.substring(8,10)
    					+BeginDate.substring(11,13)+BeginDate.substring(14,16);
    var strEnddate = EndDate.substring(0,4)+EndDate.substring(5,7)+EndDate.substring(8,10)
    					+EndDate.substring(11,13)+EndDate.substring(14,16);;
    if(parseInt(strBegindate)>parseInt(strEnddate))
    {
        alert( "修改的日期不能大于当前日期！");
        return false;
    }
    return true;
}

String.prototype.isEmpty=function(){
	var str = this;
	if(str==null || str==""){
		return true;
	}
	
	return false;
}

// 全选复选框
function selectAll(flag,id)
{
	var cks = document.getElementsByName(id);
	
	if(cks == null)
	{
		return;
	}
	
	for(var i = 0; i < cks.length;i++)
	{
		if(!cks[i].disabled)
		{
			cks[i].checked = flag;
		}
	}	
}

function updateState(flag,id,url){
	var count = 0;
	var id = document.getElementsByName(id);
	var allowDel="";
	var delCount=0;
	
	if(url=="updateStatusUser")
		allowDel=document.getElementsByName("td_allow_del");
	
	for(i=0;i<id.length;i++){			
		if(id[i].checked){
			count+=1;
			
			if(url=="updateStatusUser" && flag==3 && allowDel[i].innerHTML=="是")
				delCount+=1;
		}
	}

	if(count==0){
		alert('请选择要设置的记录');
		return false;
	}
	else{
		if(url=="updateStatusUser" && flag==3 && delCount!=count){
			alert('您选择的记录中有不允许删除记录，请重新选择');
			return false;
		}
		if(!confirm("确定要改变记录的状态吗？"))
		{
			return false;
		}
		
		document.form1.action = url+".do?flag="+flag;
		document.form1.submit();
	}

}