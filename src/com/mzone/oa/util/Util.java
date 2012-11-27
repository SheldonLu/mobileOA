package com.mzone.oa.util;

import java.util.ArrayList;

import com.mzone.oa.bean.TodoDocumentBean;

public class Util {

	public static ArrayList<TodoDocumentBean> docBeans = new ArrayList<TodoDocumentBean>();

	public static TodoDocumentBean clickBean;

	static{
		TodoDocumentBean tb = new TodoDocumentBean();
		tb.title = "政协换届以来工作总结";
		tb.time = "12-10-21 10:12:44";
		tb.suggess = "已阅";
		tb.dengji = "等级1";
		tb.desc = "desc";
		tb.jinbanren = "花花";
		
		docBeans.add(tb);
		
		tb = new TodoDocumentBean();
		tb.title = "在党校学习贯彻十八大精神会议上的讲话";
		tb.time = "12-10-22 10:12:44";
		tb.suggess = "已阅";
		tb.dengji = "等级2";
		tb.desc = "desc讲话";
		tb.jinbanren = "毛毛";
		
		docBeans.add(tb);
	}
}
