//选择复选框checkbox
		function checkCheckBox(obj){
			var iCnt = 0;
			var formObj = document.forms[0].elements;			
			if(obj.checked){
				while(formObj.length > iCnt){
					if(formObj[iCnt].type == "checkbox"){
						formObj[iCnt].checked = true;
					}
					iCnt++;
				}
			}else{
				while(formObj.length > iCnt){
					if(formObj[iCnt].type == "checkbox"){
						formObj[iCnt].checked = false;
					}
					iCnt++;
				}
			}
		}
		

		//全部选择复选框checkbox按钮
		function checkAllCheckBox(){
			var iCnt = 0;
			var formObj = document.forms[0].elements;			
			while(formObj.length > iCnt){
				if(formObj[iCnt].type == "checkbox"){
					formObj[iCnt].checked = true;
				}
				iCnt++;
			}			
		}
		
