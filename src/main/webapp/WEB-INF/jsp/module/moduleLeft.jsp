<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>办公管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
	<link href="${ctx}/css/common/commons.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/dtree/dtree.css"/>
	<script type="text/javascript" src="${ctx }/js/dtree/dtree.js"></script>
	<script type="text/javascript">
	   //发送异步请求，加载模块信息
	   $(function(){
		   d = new dTree('d', "${ctx}/js/detree/");
		   //创建根节点
		   d.add("0", -1, '模块管理');
		   d.add("1", "0", '全部', "${ctx}/module/selectModule.jspx", "全部", "moduleRightFrame");
		   //发送ajax 查询数据库
		   $.ajax({
			   type : "post",
			   dataType : "json",
			   url : "${ctx}/module/ajaxLoadModule.jspx",
			   async : false,	//同步
			   success : function(msg){
				   //第一个参数：节点id 第二个参数：父节点id 第三个参数：节点名称 第四个参数：请求地址 第五个参数：节点标题  第六个参数：跳转的目标位置 第七个参数：图片路径
				   $.each(msg, function(){
					   d.add(this.id, this.pid, this.name, "${ctx}/module/selectModule.jspx?parentCode=" + this.id, this.name, "moduleRightFrame");
				   })
				   //将dtree放在页面中
				   $("#tree").html(d.toString());
				   d.openTo("1");
			   },
			   error : function(){
				   $.messager.alert("温馨提示", "数据加载异常", "warning");
			   }
		   })
	   })
	</script>
</head>
<body style="overflow: hidden;">
    <div class="topdivyc">
    	<a href="javascript:void(0);" class="a_link" title="隐藏菜单栏" id="nav_title"><img src="${ctx }/images/system/left_yc.gif" id= "shownav"/><!--隐藏时反显示的图片<img src="images/left_xs.gif" />--></a>
    </div>
    <div class="bodytextmenu" id="shumenu">
	    <div class="shumenu" >
	    	<div id="tree" >
	    	
			</div>
	    </div>
    </div>
</body>
</html>