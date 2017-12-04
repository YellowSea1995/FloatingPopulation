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
</head>
<!-- 框架集：包含两个页面 -->
<frameset cols="138,*">
	<!-- 左边 -->
	<frame src="${ctx}/popedom/popedomLeft.jspx?role.id=${role.id}" name="popedomLeftFrame"/>
	<!-- 右边 -->
	<frame name="popedomRightFrame"/>
</frameset>
</html>