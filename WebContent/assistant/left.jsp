<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="model.Assistant"%>
<%@page import="model.Course"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
</head>
<body>
<%
		request.setCharacterEncoding("utf-8");
		String csid = (String) request.getParameter("param");
		int id = (Integer)request.getSession().getAttribute("assid");
		int cid = Integer.parseInt(csid);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
		Course c = courseDaoImpl.getCourse(cid);
		session.setAttribute("course", c);
		session.setAttribute("courseid", cid);
		session.setAttribute("assid", id);
	%>
	<div class="col-xs-2">
		<div class="list-group">
			<a href="courseinfoass.jsp?param=<%=cid%>" class="list-group-item">基本信息</a> 
			<a href="stuhomework.jsp?param=<%=cid%>" class="list-group-item">学生作业</a> 
			<a href="uploadscore.jsp?param=<%=cid%>" class="list-group-item">上传分数及点评</a>
		</div>
	</div>
</body>
</html>