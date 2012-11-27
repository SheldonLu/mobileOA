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
		tb.desc = "今年以来，按照县委县政府的总体部署和县政协常委会工作要点安排，县政协认真履行工作";
		tb.jinbanren = "陈国华";
		
		docBeans.add(tb);
		
		tb = new TodoDocumentBean();
		tb.title = "在党校学习贯彻十八大精神会议上的讲话";
		tb.time = "12-10-22 11:12:44";
		tb.suggess = "通过";
		tb.dengji = "等级2";
		tb.desc = "镇原县委党校学习贯彻党的十八大精神专题研讨会召开，6位教学骨干围绕精心准备的讲稿，先后发言";
		tb.jinbanren = "李建国";
		
		docBeans.add(tb);
	}
}
