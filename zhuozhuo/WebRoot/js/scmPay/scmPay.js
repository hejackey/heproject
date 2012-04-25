var contextPath="/zhuozhuo";

//展示明细
		function showScmPayDetail(id){							
			if($("#sp"+id).html()=="+展开"&& $("#sp"+id).attr('title')=="1"){	
			var title="<tr><td width='8%' class='detailtabheader'>序号</td><td width='12%'  class='detailtabheader'>来源单号</td><td width='20%'  class='detailtabheader'>本单金额</td>"+
					  "<td width='20%'  class='detailtabheader'>已付金额</td><td width='10%' class='detailtabheader'>本次现付</td><td width='10%' class='detailtabheader'>余额</td>"+
					  "<td width='10%' class='detailtabheader'>备注</td></tr>";   
			
			$.get("showDetailsScmPay.do?id="+id,function(data){				
				if(data!=null && data!=""){
					var objList=eval('(' + data + ')');			
					var count = objList.length;
					var content="";
					
					if(count>0){
						for(i=0;i<count;i++){
							var srcSheetId=objList[i].srcSheetId;
							if(typeof srcSheetId=='undefined')
								srcSheetId='';
							
							var amt=objList[i].amt;						
							if(typeof amt =='undefined')
								amt='';
							
							var payedAmt=objList[i].payedAmt;						
							if(typeof payedAmt =='undefined')
								payedAmt='';
							
							var payAmt=objList[i].payAmt;						
							if(typeof payAmt =='undefined')
								payAmt='';
							
							var plusAmt=objList[i].plusAmt;						
							if(typeof plusAmt =='undefined')
								plusAmt='';
							
							var remark=objList[i].remark;						
							if(typeof remark =='undefined')
								remark='';
							
							content+="<tr><td class='tablist'>"+parseInt(i+1)+"</td><td class='tablist'>"+srcSheetId+"</td><td class='tablist'>"+amt+"</td>"+
									  "<td class='tablist'>"+payedAmt+"</td><td class='tablist'>"+payAmt+"</td>"+
									  "<td class='tablist'>"+plusAmt+"</td>"+"<td class='tablist'>"+remark+"</td></tr>";
						}
						
						title=title+content;
						
						$("#detail_table_"+id).html(title);
						$("#detail"+id).css("display","");
						$("#sp"+id).html("-收缩");
						$("#sp"+id).attr('title',"1");
					}
				}
			});
			
		}
		else if($("#sp"+id).attr('title')=="2"){
			$("#detail"+id).css("display","");
			$("#sp"+id).html("-收缩");
			$("#sp"+id).attr('title',"1");
		}
		else{
			$("#detail"+id).css("display","none");
			$("#sp"+id).html("+展开");
			$("#sp"+id).attr('title',"2");
		}
		
}




//删除一行
	function deleteTR(obj){
		var oTr = obj.parentElement.parentElement.parentElement;		
		var oTable = oTr.parentElement.parentElement;		
		var trIndex = oTr.rowIndex;		
		oTable.deleteRow(trIndex);
	}
	
function addDetail(obj){
	var oTr = obj.parentElement.parentElement.parentElement;	
	var oTable = oTr.parentElement;	
	var trIndex = oTr.rowIndex;
	var pTr = oTable.insertRow(trIndex+2);
	var iCell =	pTr.insertCell(0);
	iCell.style.textAlign = "center";	
	iCell.innerHTML = '<input type="text" name="textfield" id="textfield" class="sellinput" />';
	pTr.insertCell(1).innerHTML ='<input type="text" name="did" id="did" class="sellinput" />';
	pTr.insertCell(2).innerHTML ='<input type="text" name="textfield" id="textfield" class="sellinput" />';
	pTr.insertCell(3).innerHTML ='<input type="text" name="textfield" id="textfield" class="sellinput" />';
	pTr.insertCell(4).innerHTML ='<input type="text" name="amt" id="amt" class="sellinput" />';
	pTr.insertCell(5).innerHTML ='<input type="text" name="payedAmt" id="payedAmt" class="sellinput" />';
	pTr.insertCell(6).innerHTML ='<input type="text" name="payAmt" id="payAmt" class="sellinput" />';
	pTr.insertCell(7).innerHTML ='<input type="text" name="plusAmt" id="plusAmt" class="sellinput" />';
	pTr.insertCell(8).innerHTML ='<input type="text" name="payDate" id="payDate"  class="Wdate" size="22"  onClick="WdatePicker1()" />';
	pTr.insertCell(9).innerHTML ='<input type="text" name="textfield" id="textfield" class="sellinput" />';
	pTr.insertCell(10).innerHTML ='<input type="text" name="remarks" id="remarks" class="sellinput" />';
	var lastCell=	pTr.insertCell(11);
	lastCell.style.textAlign = "center";	
	lastCell.innerHTML ='<a href="#"><img src="'+contextPath+'/images/icon_del.gif" width="16" height="16" title="删除该条信息"  onclick="deleteTR(this)" /></a>';	
}

function queryPayDetail(){
	var pid=$("#providerid").val();
	$.get("getDetailByProviderIdScmPay.do?providerid="+pid,function(data){
		if(data!=null && data!=""){
			var objList=eval('(' + data + ')');			
			var count = objList.length;
			var content="";
			var sumAmt=0;
			if(count>0){
				for(i=0;i<count;i++){
					var pname=objList[i].providername;
					if(typeof pname=='undefined')
						pname='';
					
					var pcode=objList[i].providercode;						
					if(typeof pcode =='undefined')
						pcode='';
										
					var amt=objList[i].amt;						
					if(typeof amt =='undefined')
						amt='';
					
					var remark=objList[i].remark;						
					if(typeof remark =='undefined')
						remark='';
					var payDate=objList[i].payDate;						
					if(typeof payDate =='undefined')
						payDate='';
					
					content+="<tr bgcolor='#f9ffe2'>"+
			                 "<td><input type='checkbox' name='detailid' id='detailid' value='"+objList[i].id+"'/>"+(i+1)+
			                 "<input type='hidden' name='arrid' id='arrid' value='"+objList[i].id+"'></td>"+
			                 "<td>"+objList[i].srcSheetId+"</td>"+
			                 "<td>"+pcode+"</td>"+
			                 "<td>"+pname+"</td>"+
			                 "<td>"+amt+"</td>"+
			                 "<td>"+payDate+"</td>"+
			                 "<td>"+remark+"&nbsp;</td></tr>";
					
					sumAmt+=parseFloat(amt);
				}
				
				$("#detail_content").html(content);
				$("#sumAmt").val(sumAmt);
			}
			else{
				$("#detail_content").html("");
			}
		}
		else{
			$("#detail_content").html("");
		}
	});	
}

function saveScmPay(){
	document.form1.submit();
}
function editScmPay(){
	document.form1.action="editScmPay.do"
	document.form1.submit();
}
                                                 