var contextPath="/zhuozhuo";

//展示明细
		function showScmStorageInDetail(id){					
			if($("#si"+id).html()=="+展开"&& $("#si"+id).attr('title')=="1"){	
			var title="<tr><td width='8%' class='detailtabheader'>序号</td><td width='12%'  class='detailtabheader'>产品名称</td><td width='20%'  class='detailtabheader'>数量</td>"+
					  "<td width='20%'  class='detailtabheader'>实际价格</td><td width='10%' class='detailtabheader'>金额</td><td width='10%' class='detailtabheader'>仓库名称</td><td width='10%' class='detailtabheader'>单据来源</td>"+
					  "<td width='10%' class='detailtabheader'>备注</td></tr>";   
			
			$.get("showDetailScmStorageIn.do?id="+id,function(data){				
				if(data!=null && data!=""){
					var objList=eval('(' + data + ')');			
					var count = objList.length;
					var content="";
					
					if(count>0){
						for(i=0;i<count;i++){
							var productId=objList[i].productId;
							if(typeof productId=='undefined')
								productId='';
								
							var productname=objList[i].productname;
							if(typeof productname=='undefined')
								productname='';
							
							var qty=objList[i].qty;						
							if(typeof qty =='undefined')
								qty='';
							
							var factInprrice=objList[i].factInprrice;						
							if(typeof factInprrice =='undefined')
								factInprrice='';
							
							var amt=objList[i].amt;						
							if(typeof amt =='undefined')
								amt='';
								
							var barnid=objList[i].barnid;						
							if(typeof barnid =='undefined')
								barnid='';
							
							var sheetIdOriginal=objList[i].sheetIdOriginal;						
							if(typeof sheetIdOriginal =='undefined')
								sheetIdOriginal='';
							
							var remark=objList[i].remark;						
							if(typeof remark =='undefined')
								remark='';
							
							content+="<tr><td class='tablist'>"+parseInt(i+1)+"</td><td class='tablist'>"+productname+"</td><td class='tablist'>"+qty+"</td>"+
									  "<td class='tablist'>"+factInprrice+"</td><td class='tablist'>"+amt+"</td>"+"<td class='tablist'>"+barnid+"</td>"+
									  "<td class='tablist'>"+sheetIdOriginal+"</td>"+"<td class='tablist'>"+remark+"</td></tr>";
						}
						
						title=title+content;
						
						$("#detail_table_"+id).html(title);
						$("#detail"+id).css("display","");
						$("#si"+id).html("-收缩");
						$("#si"+id).attr('title',"1");
					}
				}
			});
			
		}
		else if($("#si"+id).attr('title')=="2"){
			$("#detail"+id).css("display","");
			$("#si"+id).html("-收缩");
			$("#si"+id).attr('title',"1");
		}
		else{
			$("#detail"+id).css("display","none");
			$("#si"+id).html("+展开");
			$("#si"+id).attr('title',"2");
		}
		
}




//删除一行
	function deleteDetailTR(obj){		
		$("#"+obj).remove();		
	}
	
//增加明细表的内容
function addSSIDetail(context){	
	var addRowCount = $("#rowCount").val();
	if(addRowCount==null || addRowCount==""){
		alert('请输入新增行数');
		return ;
	}
	if(isNaN(addRowCount)){
		$("#rowCount").val(1);
		addRowCount=1;
	}	
	
	var rowId = $("#product_table tr").eq($("#product_table tr").length-2).attr("id");		
	if(isNaN(rowId)||rowId==null||rowId=="")
		rowId=0;
	
	str="";
	for(var i=0;i<addRowCount;i++){
		str+="<tr bgcolor='#f9ffe2' id='"+(parseInt(rowId)+i+1)+"'><td id='seq"+(parseInt(rowId)+i+1)+"'>"+(parseInt(rowId)+i+1)+"</td>" +
			"<td><input type='text' name='productcode' id='productcode"+(parseInt(rowId)+i+1)+"' class='sellinput'/>" +
			"<img src='"+context+"/images/calendar.gif' onclick=queryProductOut('"+(parseInt(rowId)+i+1)+"','"+context+"/mis/productInfoJxc/qByProductCodeProductInfoJxc.do') "+
			"style='cursor:pointer;'></td>"+			                
			"<td><input type='text' name='productname' readonly id='productname"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
            "<td><input type='text' name='productspecs' readonly id='productspecs"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
            "<td><input type='text' name='qty' onchange='calStorageOutSumPrice("+(parseInt(rowId)+i+1)+")' id='amount"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<td><input type='text' name='factSellPrice' onchange='calStorageOutSumPrice("+(parseInt(rowId)+i+1)+")' id='productpurchase"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+                
	        "<td><input type='text' name='amt' readonly id='sumprice"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<td><input type='text' name='productentry' readonly id='productentry"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<td><input type='text' name='sheetIdOriginal' id='sheetIdOriginal"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<input type='hidden' name='remark' id='remark"+(parseInt(rowId)+i+1)+"' class='sellinput' />"+
	        "<input type='hidden' name='taxrate' id='taxrate"+(parseInt(rowId)+i+1)+"' class='sellinput' />"+
	        "<input type='hidden' name='productId' id='productid"+(parseInt(rowId)+i+1)+"' class='sellinput' />"+
	        "<td align='center' class='nb_right'><a href='#'><img src='"+context+"/images/icon_del.gif' width='16' height='16' title='删除该条信息'"+
	        "onclick='deleteDetailTR("+(parseInt(rowId)+i+1)+")' /></a></td></tr>"	
  
	}
	
	$("#product_table tr").eq($("#product_table tr").length-2).after(str);	
}
                                           