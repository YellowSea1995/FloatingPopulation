<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>办公管理系统-添加用户</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache, must-revalidate"/>
		<meta name="Keywords" content="keyword1,keyword2,keyword3"/>
		<meta name="Description" content="网页信息的描述" />
		<meta name="Author" content="fkjava.org" />
		<meta name="Copyright" content="All Rights Reserved." />
		<link href="${ctx}/fkjava.ico" rel="shortcut icon" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="${ctx}/css/common/commons.css"/>
		<script type="text/javascript" src="${ctx}/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript">
			$(function(){
				
				//异步加载部门以及职位信息
				$.ajax({
	    		type:"post",
	    		url:"${ctx}/user/ajaxLoadDeptAndJob.action",
	    		dataType:"json",//指定返回的数据  json格式字符窜
	    		success:function(msg){
	    			//获取部门信息
	    			var depts = msg.depts;
	    			
	    			//获取职位信息
	    			var jobs = msg.jobs;
	    			//将部门信息填充至下拉框
                   $.each(depts,function(){
                    	$("#deptSelect").append("<option value='"+this.id+"'>"+this.name+"</option>");
                    })
                    
                    //将职位信息填充至下拉框
                    $.each(jobs,function(){
                    	$("#jobSelect").append("<option value='"+this.id+"'>"+this.name+"</option>");
                    })

	    		},error:function(){
	    			$.messager.alert('温馨提示','数据加载异常!','warning');

	    		}
	    	})
			
				
				/** 给提交按钮绑定点击事件 */
				$("#btn_submit").click(function(){
					// 对表单中所有字段做校验
					var userId = $("#userId");
					var name = $("#name");
					var passWord = $("#passWord");
					var repwd = $("#repwd");
					var email = $("#email");
					var tel = $("#tel");
					var phone = $("#phone");
					var qqNum = $("#qqNum");
					var answer = $("#answer");
					var msg = "";
					
					if ($.trim(userId.val()) == ""){
						msg += "用户登录名不能为空!";
						userId.focus();
					}else if (!/^\w{5,20}$/.test(userId.val())){
						msg += "用户登录名长度必须在5-20之间!";
						userId.focus();
					}else if ($.trim(name.val()) == ""){
						msg += "姓名不能为空!";
						name.focus();
					}else if ($.trim(passWord.val()) == ""){
						msg += "密码不能为空!";
						passWord.focus();
					}else if (!/^\w{6,20}$/.test(passWord.val())){
						msg += "密码长度必须为6-20之间!";
						passWord.focus();
					}else if (repwd.val() != passWord.val()){
						msg += "两次输入的密码不一致!";
						repwd.focus();
					}else if ($.trim(email.val()) == ""){
						msg += "邮箱不能为空!";
						email.focus();
					}else if (!/^\w+@\w{2,}\.\w{2,}$/.test(email.val())){
						msg += "邮箱格式不正确!";
						email.focus();
					}else if ($.trim(tel.val()) == ""){
						msg += "电话号码不能为空!";
						tel.focus();
					}else if (!/^0\d{2,3}-?\d{7,8}$/.test(tel.val())){
						msg += "电话号码格式不正确!";
						tel.focus();
					}else if ($.trim(phone.val()) == ""){
						msg += "手机号码不能为空!";
						phone.focus();
					}else if (!/^1[3|4|5|8]\d{9}$/.test(phone.val())){
						msg += "手机号码格式不正确!";
						phone.focus();
					}else if ($.trim(qqNum.val()) == ""){
						msg += "QQ号码不能为空!";
						qqNum.focus();
					}else if (!/^\d{5,12}$/.test(qqNum.val())){
						msg += "QQ号码长度必须在5-12之间!";
						qqNum.focus();
					}else if ($.trim(answer.val()) == ""){
						msg += "密保问题不能为空!";
						answer.focus();
					}
					
					if(msg){
		    			$.messager.alert('温馨提示',msg,'warning');

					}else{
						//提交表单
						$("#addUserForm").submit();
					}
					
				});
				
				
				if("${tip}"){
					parent.addMessage("${tip}");
				}
				
				
			});
			
			
			
			
			//验证用户名是否已存在
			function validUserInfo(obj){
				$.ajax({
		    		type:"post",
		    		url:"${ctx}/user/validUserName.action",
		    		data:"userName="+obj.value, //{userName:obj.value}
		    		dataType:"text",//指定返回的数据  json格式字符窜
		    		success:function(msg){		    			
		    			if(msg){
		    				$.messager.alert('温馨提示',msg,'warning')
		    				obj.value = "";
		    			}

		    		},error:function(){
		    			alert("检测用户名是否存在失败！")
		    			$.messager.alert('温馨提示','数据加载异常!','warning');

		    		}
		    	})
				
			}
		</script>
	</head>
<body>
	<table align="center">
	    <!-- 请求错误输出的地方 表单重复提交的错误-->
		<s:actionerror cssStyle="font-size:12px;color:red;"/>
		<!-- 后台校验失败输出的地方 -->
		<s:fielderror cssStyle="font-size:12px;color:red;"/>
		<s:if test="tip != null">
			<center><span style="color:red;">${tip}</span></center>
		</s:if>
		<s:form action="/user/addUser.jspx" method="post" id="addUserForm" theme="simple">
			<!-- 防止表单重复提交  -->
			<s:token></s:token>
			<s:actionerror/>
 
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>
					登&nbsp;录&nbsp;名：
					<s:textfield id="userId" name="user.userId" size="18" onblur="validUserInfo(this)"/>
				</td>
				<td>
					用户姓名：
					<s:textfield id="name" name="user.name" size="18" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td>
					用户密码：
					<s:password id="passWord" name="user.passWord" size="18"/>
				</td>
				<td>
					重输密码：
					<s:password id="repwd" name="repwd" size="18"/>
				</td>
			</tr>
			<tr>
				<td>
					性&nbsp;&nbsp;&nbsp;&nbsp;别：
					<s:select name="user.sex" list="#{1:'男',2:'女'}"/>
				</td>
				<td>
					部&nbsp;&nbsp;&nbsp;&nbsp;门：
					<select id="deptSelect" name="user.dept.id"></select>
				</td>
			</tr>

			<tr>
				<td>
					职&nbsp;&nbsp;&nbsp;&nbsp;位：
					<select id="jobSelect" name="user.job.code"></select>
				</td>
				<td>
					邮&nbsp;&nbsp;&nbsp;&nbsp;箱：
					<s:textfield id="email" name="user.email" size="18"
						maxlength="50"/>
				</td>
			</tr>
			<tr>
				<td>
					电&nbsp;&nbsp;&nbsp;&nbsp;话：
					<s:textfield id="tel" name="user.tel" size="18"/>
				</td>
				<td>
					手&nbsp;&nbsp;&nbsp;&nbsp;机：
					<s:textfield id="phone" name="user.phone" size="18" maxlength="11"/>
				</td>
			</tr>
			<tr>
				<td>
					Q&nbsp;Q号&nbsp;码：
					<s:textfield id="qqNum" name="user.qqNum" size="18" maxlength="20"/>
				</td>
				<td>
					问&nbsp;&nbsp;&nbsp;&nbsp;题：
					<s:select name="user.question"
						list="#{0:'您的生日？',1:'您父亲的姓名？',2:'您的出生地？',3:'您母亲的名字？'}" />
				</td>
			</tr>

			<tr>
				<td colspan="2">
					结&nbsp;&nbsp;&nbsp;&nbsp;果：
					<s:textfield id="answer" name="user.answer" size="48" maxlength="50"></s:textfield>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input value="提 交" type="button" id="btn_submit" />
					&nbsp;&nbsp;
					<input value="重 置" type="reset" />
				</td>
			</tr>
		</s:form>
	</table>
</body>
</html>	