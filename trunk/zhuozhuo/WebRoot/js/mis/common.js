
//判断是否为数字
function IsNumeric(input){	
     var RE = /^-{0,1}\d*\.{0,1}\d+$/;
          return (RE.test(input)); 
}

//LTrim(string):去除左边的空格 
	function LTrim(str) { 
		var whitespace = new String(" \t\n\r"); 
		var s = new String(str); 
		
		if (whitespace.indexOf(s.charAt(0)) != -1) { 
			var j = 0, i = s.length; 
			while (j < i && whitespace.indexOf(s.charAt(j)) != -1) { 
				j++; 
			} 
			s = s.substring(j, i); 
		} 
		return s; 
	} 


//RTrim(string):去除右边的空格
function RTrim(str) { 
	var whitespace = new String(" \t\n\r"); 
	var s = new String(str); 
	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) { 
		var i = s.length - 1; 
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1) { 
			i--; 
		} 
		s = s.substring(0, i + 1); 
	} 
	return s; 
} 


//Trim(string):去除前后空格

function Trim(str) { 
	return RTrim(LTrim(str)); 
} 