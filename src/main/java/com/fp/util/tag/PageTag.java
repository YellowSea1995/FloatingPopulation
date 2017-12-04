package com.fp.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 标签处理类
 * @author Higer
 *
 */
public class PageTag extends TagSupport {
	
	private static final long serialVersionUID = -8946292220410791922L;
	
	private int pageIndex = 1;//当前页码 
	private int pageSize;//每页显示的记录数
	private int recordCount;//总记录数
	private String submitUrl;//提交地址  index.action?pageIndex = {0}
	
	private int totalPageNum;//总记录数
	private String pageStyle = "meneame";
	
	private final String TAG = "{0}";

	public PageTag() {
	}

	//画分页标签
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sbf = new StringBuffer();
		StringBuffer page = new StringBuffer();
		//判断总记录数
		if(this.recordCount>0){
			//定义请求请求的url地址
			String requestUrl = "";
			//计算总页数
			totalPageNum = this.getRecordCount() % this.getPageSize() ==0 ?this.getRecordCount() / this.getPageSize():this.getRecordCount() / this.getPageSize()+1;
			
			
			//假设当前页码在第一页
			if(this.getPageIndex()==1){
				//拼装上一页
				page.append("<span class='disabled'>上一页</span>");
				
				//拼装中间页码
				calcMiddelPage(page);
				
				//拼装下一页   当前页码等于尾页时 下一页不能点击
				if(this.getPageIndex()==totalPageNum){
					page.append("<span class='disabled'>下一页</span>");
				}else{
					//this.getSubmitUrl()：index.action?pageIndex = {0}
					requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(this.getPageIndex()+1));
					page.append("<a href='"+requestUrl+"'>下一页</a>");
				}
			   //假如当前页码在尾页
			}else if(this.getPageIndex()==totalPageNum){
				requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(this.getPageIndex()-1));
				page.append("<a href='"+requestUrl+"'>上一页</a>");
				
				//计算中间页码
				calcMiddelPage(page);
				
				page.append("<span class='disabled'>下一页</span>");
				
				//当前页码靠近中间  上一页以及下一页都能点击
			}else{
				requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(this.getPageIndex()-1));
				page.append("<a href='"+requestUrl+"'>上一页</a>");
				
				//计算中间页码
				calcMiddelPage(page);
				
				requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(this.getPageIndex()+1));
				page.append("<a href='"+requestUrl+"'>下一页</a>");
			}
			
			//计算当前页码的开始与结束行号
			int startSize = (this.getPageIndex() -1) * this.pageSize +1;
			//如果当前页码是尾页   将总记录数  做为结束行号
			int endSize = this.getPageIndex() == this.getTotalPageNum()?this.getRecordCount() : this.pageIndex *  this.pageSize;
					
			
			//将拼装好的分页标签响应至客户端
			sbf.append("<table align='center' width='100%' class='"+pageStyle+"' style='font-size:14px;'><tr><td>");
			sbf.append(page.toString());
			sbf.append("跳转到<input type='text' size='2' id='jump_num'/><input type='button' value='跳转'  id='jump_page'/></td></tr>");
			sbf.append("<tr><td>总共<font color='red'>"+recordCount+"</font>条记录,当前显示"+startSize+"-"+endSize+"条记录</td></tr>");
			sbf.append("</table>");
			
			
			
			sbf.append("<script type='text/javascript'>");
			sbf.append("document.getElementById('jump_page').onclick = function(){");
			sbf.append("var value = document.getElementById('jump_num').value;");
			sbf.append("if(!/^[1-9]\\d*$/.test(value)||value > "+this.getTotalPageNum()+"){");
			sbf.append("alert('请输入[1-"+this.getTotalPageNum()+"]之间的页码值！');");
			sbf.append("}else{");
			// index.action?pageIndex = {0}
			sbf.append("var submiturl = '"+this.submitUrl+"';");
			sbf.append("submiturl = submiturl.replace('"+this.TAG+"',value);");
			sbf.append("window.location = submiturl;");
			
			sbf.append("}");
			
			sbf.append("}");
			
			
			
			sbf.append("</script>");
			
			
			
		}else{
			//没有相关记录
			sbf.append("<table align='center' style='font-size:14px;'><tr><td>总共<font color='red'>0</font>条记录,当前显示0-0条记录</td></tr></table>");
			
		}
		
		try {
			out.print(sbf.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		//SKIP_BODY：跳过开始标签与结束标签之间的代码
		return SKIP_BODY;
	}

	//计算中间页码
	private void calcMiddelPage(StringBuffer page) {
		//定义请求的url地址
		String requestUrl = "";
		
		//假设总页数小于等于10  则全部显示
		// 1 2 3 4 5 6 7 8 9 10
		if(this.getTotalPageNum() <=10){
			for(int i=1;i<=this.getTotalPageNum();i++){
				//如果i等于当前页码则不可点击
				if(i==this.getPageIndex()){
					page.append("<span class='current'>"+i+"</span>");
				}else{
					requestUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					page.append("<a href='"+requestUrl+"'>"+i+"</a>");
				}
				
			}
			
			//当前页码靠近首页   1 2 3 4 5 6 7 8 9 ... 100
		}else if(this.getPageIndex()<=8){
			for(int i=1;i<=9;i++){
				//如果i等于当前页码则不可点击
				if(i==this.getPageIndex()){
					page.append("<span class='current'>"+i+"</span>");
				}else{
					requestUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					page.append("<a href='"+requestUrl+"'>"+i+"</a>");
				}
				
			}
			page.append("...");
			requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(this.getTotalPageNum()));
			page.append("<a href='"+requestUrl+"'>"+this.getTotalPageNum()+"</a>");
			
		  //假设当前页码靠近尾页  1 ... 91 92 93 94 95 96 97 98 99 100 
		}else if(this.getPageIndex() +8 >=this.getTotalPageNum()){
			requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(1));
			page.append("<a href='"+requestUrl+"'>"+1+"</a>");
			page.append("...");
			
			for(int i = this.getTotalPageNum() - 9;i<=this.getTotalPageNum();i++){
				if(i==this.getPageIndex()){
					page.append("<span class='current'>"+i+"</span>");
				}else{
					requestUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					page.append("<a href='"+requestUrl+"'>"+i+"</a>");
				}
			}
			
			//当前页码在页码中间    1 ...    40 41 42 43 44 45 46 47 48 ... 100
		}else{
			requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(1));
			page.append("<a href='"+requestUrl+"'>"+1+"</a>");
			page.append("...");
			
			
			for(int i = this.getPageIndex() - 4;i<=this.getPageIndex()+4;i++){
				if(i==this.getPageIndex()){
					page.append("<span class='current'>"+i+"</span>");
				}else{
					requestUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					page.append("<a href='"+requestUrl+"'>"+i+"</a>");
				}
			}
			
			page.append("...");
			requestUrl = this.getSubmitUrl().replace(TAG, String.valueOf(this.getTotalPageNum()));
			page.append("<a href='"+requestUrl+"'>"+this.getTotalPageNum()+"</a>");
			
		}
		
		
		
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		System.out.println("pageIndex:"+pageIndex);
		if(pageIndex == 0){
		   pageIndex = 1;
		}
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
	
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public String getPageStyle() {
		return pageStyle;
	}

	public void setPageStyle(String pageStyle) {
		this.pageStyle = pageStyle;
	}
	
	
	

}
