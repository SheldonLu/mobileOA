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
		
		docBeans.add(tb);
	}
}
