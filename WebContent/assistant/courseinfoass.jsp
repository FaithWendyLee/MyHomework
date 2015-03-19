<%@page import="factory.DaoFactory"%>
<%@page import="model.Course"%>
<%@page import="service.CourseService"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="course" class="model.Course" scope="page">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>courseinfostu</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<%
		request.setCharacterEncoding("utf-8");
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
	String id = (String) request.getParameter("param");
		int cid = Integer.parseInt(id);
		Course c = courseDaoImpl.getCourse(cid);
	%>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="col-xs-8">
		<div class="panel panel-default">
			<div class="panel-body courseinfo">
				基本信息<br>
				<hr>
				课程编号：<%=c.getCnumber()%><br> 课程名称：<%=c.getCname()%><br>
				授课学期：<%=c.getTerm()%><br> 授课教师：<%=c.getTeacherlist()%><br>
			</div>
		</div>
	</div>
	<div class="col-xs-2"></div>



</body>
</html>