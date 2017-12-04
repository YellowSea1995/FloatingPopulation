<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
    <!-- 引入c标签 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <!-- 引入struts标签 -->
    <%@ taglib uri="/struts-tags" prefix="s"%>
    <!-- 设置项目别名 -->
    <c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
    <!-- 引入jQuery -->
	<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
	<!-- 引入项目公共样式 -->
	<link rel="stylesheet" type="text/css" href="${ctx }/css/common/global.css"/>
	<!-- 引入easyui插件 -->
	<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui-1.4.4/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/jquery-easyui-1.4.4/themes/icon.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>