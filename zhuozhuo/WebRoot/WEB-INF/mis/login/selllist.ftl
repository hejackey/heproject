<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/master.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">子菜单名称</span>
    </div>
    <div id="container">
    <div class="addsell">
   	  <div class="left"><input name="" type="image" src="images/btn_add.gif" title="增加新单据" /></div>        
    	<div class="right"><span class="ch">表示冲红单据</span><span class="cl">表示该单据需尽快处理</span><span class="th">表示退货单</span></div>
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	<input name="" type="image" src="images/btn_select.gif" />
        	<input name="" type="image" src="images/btn_del.gif" />
			<input name="" type="image" src="images/btn_excel.gif" />
            <input name="" type="image" src="images/btn_refresh.gif" />
            <input name="" type="image" src="images/btn_print.gif" />
		</div>
        <div class="sellsearch fl_left">
        	<input name="" type="text" />
        </div>
        <div class="pages fl_right">
        	<span>共<b>126</b>条销售开单</span><a href="#" class="green">首页</a><a href="#" class="green">下一页</a><a href="#" class="green">尾页</a><span><select name="">
        	  <option>1/21</option>
        	  <option>2/21</option>
        	  <option>3/21</option>
        	</select></span>
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--列表开始-->
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th scope="col" class="tabheader nb_left" width="3%"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></th>
            <th scope="col" class="tabheader" width="6%">序号</th>
            <th scope="col" class="tabheader" width="4%">状态</th>
            <th scope="col" class="tabheader" width="14%">销售开单编号</th>
            <th scope="col" class="tabheader" width="17%">客户名称</th>
            <th scope="col" class="tabheader" width="6%">业务代表</th>
            <th scope="col" class="tabheader" width="11%">本单金额合计</th>
            <th scope="col" class="tabheader" width="6%">制单人</th>
            <th scope="col" class="tabheader" width="14%">单据来源编号</th>
            <th scope="col" class="tabheader" width="10%">开单时间</th>
            <th scope="col" class="tabheader nb_right" width="8%">备注</th>
          </tr>
          <tr>
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></td>
            <td class="tablist">1</td>
            <td class="tablist"><img src="images/icon03.gif" width="16" height="16" /></td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">北京安邦保险集团有限公司</td>
            <td class="tablist">李珊珊</td>
            <td class="tablist"><b>1200.00</b> 元</td>
            <td class="tablist">王奇瑞</td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">2010年8月24日</td>
            <td class="tablist nb_right">&nbsp;</td>
          </tr>
          <tr bgcolor="#f7f7f7">
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></td>
            <td class="tablist">2</td>
            <td class="tablist"><img src="images/icon02.gif" width="16" height="16" /></td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">北京安邦保险集团有限公司</td>
            <td class="tablist">李珊珊</td>
            <td class="tablist"><b>1200.00</b> 元</td>
            <td class="tablist">王奇瑞</td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">2010年8月24日</td>
            <td class="tablist nb_right">&nbsp;</td>
          </tr>
          <tr>
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></td>
            <td class="tablist">3</td>
            <td class="tablist"><img src="images/icon01.gif" width="16" height="16" /></td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">北京安邦保险集团有限公司</td>
            <td class="tablist">李珊珊</td>
            <td class="tablist"><b>1200.00</b> 元</td>
            <td class="tablist">王奇瑞</td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">2010年8月24日</td>
            <td class="tablist nb_right">&nbsp;</td>
          </tr>
          <tr bgcolor="#f7f7f7">
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></td>
            <td class="tablist">4</td>
            <td class="tablist"><img src="images/icon03.gif" width="16" height="16" /></td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">北京安邦保险集团有限公司</td>
            <td class="tablist">李珊珊</td>
            <td class="tablist"><b>1200.00</b> 元</td>
            <td class="tablist">王奇瑞</td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">2010年8月24日</td>
            <td class="tablist nb_right">&nbsp;</td>
          </tr>
          <tr>
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></td>
            <td class="tablist">5</td>
            <td class="tablist"><img src="images/icon03.gif" width="16" height="16" /></td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">北京安邦保险集团有限公司</td>
            <td class="tablist">李珊珊</td>
            <td class="tablist"><b>1200.00</b> 元</td>
            <td class="tablist">王奇瑞</td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">2010年8月24日</td>
            <td class="tablist nb_right">&nbsp;</td>
          </tr>
          <tr bgcolor="#f7f7f7">
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" /></td>
            <td class="tablist">6</td>
            <td class="tablist"><img src="images/icon03.gif" width="16" height="16" /></td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">北京安邦保险集团有限公司</td>
            <td class="tablist">李珊珊</td>
            <td class="tablist"><b>1200.00</b> 元</td>
            <td class="tablist">王奇瑞</td>
            <td class="tablist"><a href="#" class="green">XKSD20100824001</a></td>
            <td class="tablist">2010年8月24日</td>
            <td class="tablist nb_right">&nbsp;</td>
          </tr>
          <tr bgcolor="#f7f7f7">
            <td colspan="11" class="tablist nb_right">当前单据合计：<b>3600.00</b> 元</td>
          </tr>
</table>

    </div>
    <!--列表结束-->
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	<input name="" type="image" src="images/btn_select.gif" />
        	<input name="" type="image" src="images/btn_del.gif" />
			<input name="" type="image" src="images/btn_excel.gif" />
            <input name="" type="image" src="images/btn_refresh.gif" />
            <input name="" type="image" src="images/btn_print.gif" />
		</div>
  <div class="pages fl_right">
        	<span>共<b>126</b>条销售开单</span><a href="#" class="green">首页</a><a href="#" class="green">下一页</a><a href="#" class="green">尾页</a><span><select name="">
        	  <option>1/21</option>
        	  <option>2/21</option>
        	  <option>3/21</option>
        	</select></span>
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
</div>
</body>
</html>
