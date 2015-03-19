<%@page import="factory.DaoFactory"%>
<%@page import="model.Student"%>
<%@page import="model.Assistant"%>
<%@page import="model.Teacher"%>
<%@page import="model.PersonInCharge"%>
<%@page import="service.StudentServiceImpl"%>
<%@page import="service.AssistantService"%>
<%@page import="service.PersonInChargeService"%>
<%@page import="dao.impl.*"%>
<%@page import="java.net.URLDecoder"%>
<%@page import= "org.springframework.context.ApplicationContext"%>
<%@page import= "org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="student"
	class="model.Student" scope="page">
</jsp:useBean> 
<jsp:useBean id="assistant"
	class="model.Assistant" scope="page">
</jsp:useBean>
<jsp:useBean id="teacher"
	class="model.Teacher" scope="page">
</jsp:useBean>
<jsp:useBean id="personInCharge"
	class="model.PersonInCharge" scope="page">
</jsp:useBean>
<html>
<head>
<title>mnguser</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="col-xs-2"></div>
	<div class="col-xs-8">
		<form name="alter_user" method="post" action="../Alteruser">
		<div class="div-right">
			<a href="adduser.jsp" target="_parent">注册用户</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="javascript:document.alter_user.submit();" target="_parent">修改用户</a>
		</div>
		<table class="table table-bordered">
			<caption>选课学生</caption>
			<thead>
				<tr>
					<th>学号</th>
					<th>学生姓名</th>
					<th>学生密码</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			
					<%

					ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
					StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
									for (int i = 0; i < studentDaoImpl.getStudentList().size(); i++) {
																														Student stu=studentDaoImpl.getStudentList().get(i);
																														pageContext.setAttribute("student",stu );
								%>
					
					<TR>
						<TD><jsp:getProperty name="student" property="number" /></TD>
						<TD><jsp:getProperty name="student"	property="stuname" /></TD>
						<TD><jsp:getProperty name="student" property="stupwd" /></TD>
						<TD><input type="radio" name="alteruser" value="0<%=stu.getId()%>"></TD>
						<%
							}
						%>
					</TR>
			</tbody>
		</table>

		<table class="table table-bordered">
			<caption>助  教</caption>
			<thead>
				<tr>
					<th>助教编号</th>
					<th>助教姓名</th>
					<th>助教密码</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
				AssistantDaoImpl assistantDaoImpl = (AssistantDaoImpl) ac.getBean("assistantDaoImpl");
					for (int i = 0; i < assistantDaoImpl.getAssistantList().size(); i++) {
														Assistant ass=assistantDaoImpl.getAssistantList().get(i);
														pageContext.setAttribute("assistant",ass);
				%>
					
					<TR>
						<TD><jsp:getProperty name="assistant" property="assistantid" /></TD>
						<TD><jsp:getProperty name="assistant"	property="assistantname" /></TD>
						<TD><jsp:getProperty name="assistant" property="assistantpwd" /></TD>
						<TD><input type="radio" name="alteruser" value="1<%=ass.getAssistantid()%>"></TD>
						<%
							}
						%>
					</TR>
			</tbody>
		</table>

		<table class="table table-bordered">
			<caption>授课教师</caption>
			<thead>
				<tr>
					<th>教师编号</th>
					<th>教师姓名</th>
					<th>教师密码</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			
					<%
					TeacherDaoImpl teacherDaoImpl = (TeacherDaoImpl) ac.getBean("teacherDaoImpl");
									for (int i = 0; i < teacherDaoImpl.getTeacherList().size(); i++) {
																														Teacher tea=teacherDaoImpl.getTeacherList().get(i);
																														pageContext.setAttribute("teacher",tea );
								%>
					
					<TR>
						<TD><jsp:getProperty name="teacher" property="teaid" /></TD>
						<TD><jsp:getProperty name="teacher"	property="teaname" /></TD>
						<TD><jsp:getProperty name="teacher" property="teapwd" /></TD>
						<TD><input type="radio" name="alteruser" value="2<%=tea.getTeaid()%>"></TD>
						<%
							}
						%>
					</TR>
			</tbody>
		</table>

		<table class="table table-bordered">
			<caption>教学负责人</caption>
			<thead>
				<tr>
					<th>负责人编号</th>
					<th>负责人姓名</th>
					<th>负责人密码</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			
					<%
					PersonInChargeDaoImpl picDaoImpl = (PersonInChargeDaoImpl) ac.getBean("personInChargeDaoImpl");
					
									for (int i = 0; i < picDaoImpl.getPersonInCharge().size(); i++) {
																														PersonInCharge pinc=picDaoImpl.getPersonInCharge().get(i);
																														pageContext.setAttribute("personInCharge",pinc );
								%>
					
					<TR>
						<TD><jsp:getProperty name="personInCharge" property="picid" /></TD>
						<TD><jsp:getProperty name="personInCharge"	property="picname" /></TD>
						<TD><jsp:getProperty name="personInCharge" property="picpwd" /></TD>
						<TD><input type="radio" name="alteruser" value="3<%=pinc.getPicid()%>"></TD>
						<%
						} 
						%>
					</TR>
			</tbody>
		</table>
		</form>
	</div>
	<div class="col-xs-2"></div>



</body>
</html>