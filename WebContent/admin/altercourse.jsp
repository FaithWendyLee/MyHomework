<%@page import="model.Teacher"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="dao.impl.*"%>
<%@page import= "org.springframework.context.ApplicationContext"%>
<%@page import= "org.springframework.context.support.ClassPathXmlApplicationContext"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyHomework</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="container">
		<form method="post" action="../Altercourse2" class="form-signin">
		<h2>修改课程</h2>
			<label>课程编号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="number"/><br> <br> 
			<label>课程名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" name="name"/><br> <br> 
			
			<label>学期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
			<input type="text" name="term"/><br>
			<label style="font-size:15px;">For example,2014 Winter(2014-09-01至2015-01-18)</label>
			<br><br> 
			<label >授课教师 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><br><%

			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			TeacherDaoImpl teacherDaoImpl = (TeacherDaoImpl) ac.getBean("teacherDaoImpl");
	
			for (int i = 0; i < teacherDaoImpl
 				.getTeacherList().size(); i++) {
 		    Teacher tea = teacherDaoImpl
 					.getTeacherList().get(i);
 			pageContext.setAttribute("teacher", tea);
 %>
            <label class="checkbox">
			<input type="checkbox" name="teacherlist" value="<%=tea.getTeaname()%> "/><%=tea.getTeaname()%><br>
			</label>
			<%
				}
			%><br> <br> 
			<button class="btn btn-lg btn-primary "">确定</button>
		</form>
	</div>
</body>
</html>