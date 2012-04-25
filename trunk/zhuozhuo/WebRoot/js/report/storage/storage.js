function expStorageToExcel(){
	document.form1.action="listExcelScmStorage.do";
	document.form1.submit();
	document.form1.action="listScmStorage.do"
}

function expBarnTypeStorageToExcel(){
	document.form1.action="listExcelBarnTypeStorage.do";
	document.form1.submit();
	document.form1.action="listBarnTypeStorage.do"
}

function expProductTypeStorageToExcel(){
	document.form1.action="listExcelProductTypeStorage.do";
	document.form1.submit();
	document.form1.action="listProductTypeStorage.do"
}