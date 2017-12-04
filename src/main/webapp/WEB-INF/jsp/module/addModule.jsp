<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-添加模块</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
		<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/common/commons.css"/>
		<script type="text/javascript">
			$(function(){
				if ("${tip}") {
					//刷新左侧页面
					parent.parent.moduleLeftFrame.location.reload();
				}
				
				$("#btn_submit").click(function(){
					var name = $("#name");
					var url = $("#url");
					var remark = $("#remark");
					var msg = "";
					
					if ($.trim(name.val()) == "") {
						msg = "名称不能为空！";
						name.focus();
					} else if ($.trim(url.val()) == "") {
						msg = "链接不能为空！";
						url.focus();
					} else if ($.trim(remark.val()) == "") {
						msg = "备注不能为空！";
						remark.focus();
					}
					
					if (msg != "") {
						$.messager.alert("温馨提示", msg, "warning");
					} else {
						$("#addModuleForm").submit();
					}
				})
			})
		</script>
	</head>
<body>
	<table align="center">
		<s:actionerror cssStyle="font-size:12px;color:red;"/>
		<s:fielderror cssStyle="font-size:12px;color:red;"/>
		<s:if test="tip != null">
			<center><span style="color:red;">${tip}</span></center>
		</s:if>
		<s:form action="/module/addModule.jspx" method="post" id="addModuleForm" theme="simple">
			<s:hidden name="parentCode"></s:hidden>
			<s:token></s:token>
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>名称：</td>
				<td>
					<s:textfield id="name" name="module.name" size="40"/>
				</td>
			</tr>
			<tr>
				<td>链接：</td>
				<td>
					<s:textfield id="url" name="module.url" size="40"/>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					<s:textarea id="remark" name="module.remark" rows="5" cols="33"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="button" id="btn_submit" />
					&nbsp;
					<input value="重 置" type="reset" />
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	