<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<%@ page import="servlets.teacher.*"%>
<%@page import="model.Course"%>
<%@page import="java.net.*"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%
	String cid = (String) request.getParameter("param");
	int ccid = Integer.parseInt(cid);
	ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac
			.getBean("courseDaoImpl");
	Course c = courseDaoImpl
			.getCourse(ccid);
	String fname = c.getCname()+"的作业计划";
	OutputStream os = response.getOutputStream();//取得输出流
	response.reset();//清空输出流

	//下面是对中文文件名的处理
	response.setCharacterEncoding("utf-8");//设置相应内容的编码格式
	fname=URLEncoder.encode(fname, "UTF-8");
    fname=URLEncoder.encode(fname,"UTF-8");
    fname=URLDecoder.decode(fname, "UTF-8");
    fname=URLDecoder.decode(fname, "UTF-8");
	response.setHeader("Content-Disposition", "attachment;filename="
			+ new String(fname.getBytes("gb2312"), "ISO8859-1") + ".xls");
	response.setContentType("application/msexcel");//定义输出类型
	importhp sw = new importhp();
	sw.createExcel(os, cid);
%>
<html>
<head>

<title></title>

</head>

<body>
</body>
</html>