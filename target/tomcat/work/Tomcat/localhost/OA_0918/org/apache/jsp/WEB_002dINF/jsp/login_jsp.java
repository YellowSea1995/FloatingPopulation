/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-09-25 05:52:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/jsp/common/common.jsp", Long.valueOf(1505914552535L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("    <!-- 引入c标签 -->\r\n");
      out.write("    \r\n");
      out.write("    <!-- 引入struts标签 -->\r\n");
      out.write("    \r\n");
      out.write("    <!-- 设置项目别名 -->\r\n");
      out.write("    ");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    <!-- 引入jQuery -->\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery.js\"></script>\r\n");
      out.write("\t<!-- 引入项目公共样式 -->\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/common/global.css\"/>");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<title>办公管理系统-登录页面</title>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\"/>\r\n");
      out.write("\t<meta http-equiv=\"Cache-Control\" content=\"no-cache, must-revalidate\"/>\r\n");
      out.write("\t<meta name=\"Keywords\" content=\"keyword1,keyword2,keyword3\"/>\r\n");
      out.write("\t<meta name=\"Description\" content=\"网页信息的描述\" />\r\n");
      out.write("\t<meta name=\"Author\" content=\"gdcct.gov.cn\" />\r\n");
      out.write("\t<meta name=\"Copyright\" content=\"All Rights Reserved.\" />\r\n");
      out.write("\t<link href=\"pujin.ico\" rel=\"shortcut icon\" type=\"image/x-icon\" />\r\n");
      out.write("\r\n");
      out.write("\t<style type=\"text/css\">\r\n");
      out.write("\t\tbody{ \r\n");
      out.write("\t\t\tbackground-repeat: repeat; \r\n");
      out.write("\t\t\tbackground-positon: 100%, 100%; \r\n");
      out.write("\t\t} \r\n");
      out.write("\t\tli{\r\n");
      out.write("\t\t\tmargin-left:20px;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</style>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\t$(\"#vimg\").click(function(){\r\n");
      out.write("\t\t\t\t$(\"#vimg\").attr(\"src\",\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/verify.action?date=\"+new Date());\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t\t$(\"#img\").bind(\"click\",function(){\r\n");
      out.write("\t\t\t\t//触发验证码的点击事件\r\n");
      out.write("\t\t\t\t$(\"#vimg\").trigger(\"click\");\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t\t//为登录按钮绑定事件\r\n");
      out.write("\t\t\t$(\"#login_id\").click(function(){\r\n");
      out.write("\t\t\t\t//获取用户名\r\n");
      out.write("\t\t\t\tvar userName=$(\"#userName\").val();\r\n");
      out.write("\t\t\t\t//获取密码\r\n");
      out.write("\t\t\t\tvar pass=$(\"#password\").val();\r\n");
      out.write("\t\t\t\t//获取验证码\r\n");
      out.write("\t\t\t\tvar vcode=$(\"#vcode\").val();\r\n");
      out.write("\t\t\t\tif ($.trim(userName)==null||$.trim(userName)==\"\"){\r\n");
      out.write("\t\t\t\t\talert(\"请输入用户名！\");\r\n");
      out.write("\t\t\t\t}else if(!/^[0-9a-zA-Z]{5,15}$/.test(userName)){\r\n");
      out.write("\t\t\t\t\talert(\"您输入的用户名 不合法，请输入[0-9a-zA-Z]，并且长度为5-15位！\");\r\n");
      out.write("\t\t\t\t}else if ($.trim(pass)==null||$.trim(pass)==\"\"){\r\n");
      out.write("\t\t\t\t\talert(\"请输入密码！\");\r\n");
      out.write("\t\t\t\t}else if(!/^[0-9a-zA-Z]{6,16}$/.test(pass)){\r\n");
      out.write("\t\t\t\t\talert(\"您输入的密码 不合法，请输入[0-9a-zA-Z]，并且长度为6-16位！\");\r\n");
      out.write("\t\t\t\t}else if(!/^[0-9]{4}$/.test(vcode)){\r\n");
      out.write("\t\t\t\t\talert(\"您输入的验证码 不合法，只能输入4位数字！\");\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t//将表单数据序列化\r\n");
      out.write("\t\t\t\t\tvar params=$(\"#form\").serialize();\r\n");
      out.write("\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t type: \"POST\",\r\n");
      out.write("\t    \t\t\t\t   url: \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/ajaxLogin.action\",\r\n");
      out.write("\t    \t\t\t\t   data: params,\r\n");
      out.write("\t    \t\t\t\t   dataType:\"text\",\r\n");
      out.write("\t    \t\t\t\t   success: function(msg){\t    \t\t\t\t\t   \r\n");
      out.write("\t    \t\t\t\t     if(msg){\r\n");
      out.write("\t    \t\t\t\t    \t alert(msg);\r\n");
      out.write("\t    \t\t\t\t     }else{\r\n");
      out.write("\t    \t\t\t\t    \t //跳转至首页\r\n");
      out.write("\t    \t\t\t\t    \t window.location = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/main.action\";\r\n");
      out.write("\t    \t\t\t\t     }\r\n");
      out.write("\t    \t\t\t\t     \r\n");
      out.write("\t    \t\t\t\t   },error:function(){\r\n");
      out.write("\t    \t\t\t\t\t   alert(\"数据加载异常\");\r\n");
      out.write("\t    \t\t\t\t   }\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t}\t\t\t\t\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t\t$(document).keydown(function(event){\r\n");
      out.write("\t\t\t\tif(event.keyCode==13){\r\n");
      out.write("\t\t\t\t\t//提交表单,触发登录按钮的点击事件\r\n");
      out.write("\t\t\t\t\t$(\"#login_id\").trigger(\"click\");\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t})\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/login/9.png\">\r\n");
      out.write("\t<div align=\"center\" style=\"height:100%\">\r\n");
      out.write("\t\t<form action=\"#\" method=\"post\" id=\"form\">\r\n");
      out.write("\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top:120px;\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\" width=\"447\" height=\"104\" background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/login/1_01.jpg\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td width=\"92\" height=\"200\" background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/login/1_02.gif\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t<td width=\"355\" height=\"200\" background=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/images/login/1_03.gif\">\r\n");
      out.write("\t\t\t\t\t\t<table style=\"font-size:13px;margin:0 0 0 30px;\" >\r\n");
      out.write("\t\t\t\t\t\t\t<tr align=\"left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>用户名：</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"userName\" size=\"13\" id=\"userName\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr align=\"left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>密&nbsp;&nbsp;码：</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"password\" name=\"password\" size=\"13\" id=\"password\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0)\" id=\"findpwd\" style=\"font-size:12px;color:white;\">忘了密码?</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t   <tr align=\"left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>验证码：</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"vcode\" size=\"4\" maxlength=\"4\" id=\"vcode\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div style=\"float:left;padding:0 0 0 5px;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<img src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/verify.action\" width=\"55\" height=\"22\" title=\"验证码\" id=\"vimg\"/>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td align=\"left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" id=\"img\" style=\"font-size:12px;color:white;\">看不清楚</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t   </tr>\r\n");
      out.write("\t\t\t\t\t\t   <tr align=\"left\">\r\n");
      out.write("\t\t\t\t\t\t   \t\t<td>&nbsp;</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"2\"><input type=\"radio\" name=\"key\" value=\"1\" id=\"key\"/>记住用户</td>\r\n");
      out.write("\t\t\t\t\t\t   </tr>\r\n");
      out.write("\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t  <tr align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"button\" value=\"登 录\" width=\"67\" height=\"22\" id=\"login_id\"/>&nbsp;\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"reset\" value=\"重 置\"/>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"3\" width=\"447\" height=\"34\" background=\"{ctx}/images/login/1_04.gif\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /WEB-INF/jsp/common/common.jsp(9,4) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/common/common.jsp(9,4) '${pageContext.request.contextPath }'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${pageContext.request.contextPath }",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/common/common.jsp(9,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }
}
