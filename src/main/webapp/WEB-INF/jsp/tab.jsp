<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
  	<title>办公管理系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
	<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
	<meta name="Description" content="网页信息的描述" />
	<meta name="Author" content="gdcct.gov.cn" />
	<meta name="Copyright" content="All Rights Reserved." />
	<link href="/pujin.ico" rel="shortcut icon" type="image/x-icon" />
	
	<!-- 引入选项卡插件 -->
	<script type="text/javascript" src="${ctx }/js/tabpanel/TabPanel.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/tabpanel/TabPanel.css" />
	
  	<style type="text/css">
		html, body {
		width : 100%;
		height : 100%;
		padding : 0;
		margin : 0;
		overflow : hidden;
	</style>
	
	<script type="text/javascript">
		$(function(){
				//创建选项卡
				window.tabpanel = new TabPanel({
					renderTo : "tab",	//承载容器
					width : "100%",		//宽度
					height : "100%",	//高度
					active : 0,			//激活第一个
					maxLength : 8,		//最大选项卡的数量
					items : [{
						id : "tab_1",	//id(不能重复)
						title : "百度",	//标题
						html : "<iframe width='100%' height='100%' src='http://www.baidu.com/' frameborder='0'></iframe>",	//内容
						closable : false	//是否可以关闭（不能关闭）
					}]
				});
			});
		
		//定义添加标签页的函数
		var addTab = function(id, title, url){
			tabpanel.addTab({
				id : id,	//id(不能重复)
				title : title,	//标题
				html : '<iframe width="100%" height="100%" src="'+url+'" frameborder="0"></iframe>'	//内容
			});
		};
	</script>
</head>
<body>
<div id="tab"></div>
</body>
</html>