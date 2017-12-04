package com.fp.user.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fp.user.dao.IGeneralDao;
import com.fp.user.dao.base.impl.HibernateDaoImpl;
import com.fp.util.OAContant;

//工具类：用于计算编号
public class GeneralDaoImpl extends HibernateDaoImpl implements IGeneralDao {

	//计算最大编号     0001   
	public  String getModuleCode(String columnName, Class<?> table, String parentCode) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		hql.append("select max(tb.").append(columnName).append(")").append(" from ").append(table.getSimpleName()).append(" tb");
		hql.append(" where length(tb.").append(columnName).append(")=? ");
		hql.append(" and tb.").append(columnName).append(" like ?");
		
		List<Object> params = new ArrayList<>();
		params.add(StringUtils.isEmpty(parentCode)?OAContant.COODLENGTH : parentCode.length()+OAContant.COODLENGTH );
		params.add(StringUtils.isEmpty(parentCode)?"%":parentCode + "%");
	   
		//获取最大的子模块编号
		String maxCode =  this.findUniqueEntity(hql.toString(),params.toArray());
	    
		System.out.println("maxCode:"+maxCode);
		
		String code = parentCode ==null?"":parentCode;
		// 0001  00010001
		//判断当前模块下没有子模块   即maxCode是否为空
		if(StringUtils.isEmpty(maxCode)){
			
			for(int i=1;i<OAContant.COODLENGTH;i++){
				code += "0";
			}
			code +="1";
			
			return code;
		}else{
			//判断当前模块下有子模块   即判断maxCode是否为空
			//获取最大子节点的模块编号     0001    00010009
			String maxCode2 = maxCode.substring(code.length());
			System.out.println("maxCode2:"+maxCode2);
			//计算新的code值
			String maxCode3 = String.valueOf(Integer.valueOf(maxCode2) + 1);
			System.out.println("maxCode3:"+maxCode3);
			//计算需要补多少个零
			int length = OAContant.COODLENGTH - maxCode3.length();
			for(int i=0;i<length;i++){
			
				maxCode3 = "0" + maxCode3;
			}
			System.out.println("parentCode+maxCode3:"+parentCode+maxCode3);
			return parentCode+maxCode3;
			
		}
		
		
	}

	
	

}
