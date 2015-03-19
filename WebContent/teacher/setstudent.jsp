<%@page import="model.Student"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="model.Course"%>
<%@page import="dao.impl.*"%>
<%@page import= "org.springframework.context.ApplicationContext"%>
<%@page import= "org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:useBean id="student" class="model.Student" scope="page">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MyHomework</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<script type="text/javascript" src="../js/homeworks.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<%
	    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	    CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
		request.setCharacterEncoding("utf-8");
		String id = (String) request.getParameter("param");
		int cid = Integer.parseInt(id);
		Course c = courseDaoImpl.getCourse(cid);
		session.setAttribute("course", c);
		session.setAttribute("courseid", id);
	%>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="col-xs-8">
	<div class="panel panel-default">
			<div class="panel-body courseinfo">
				选课学生<br>
				<hr>
				学生：<%=c.getStudentlist()%><br>
			</div>
		</div>
	
	
	<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	Date date = sdf.parse("2015-04-01");
    Date nowDate = new Date();
    if(!c.Isbetween(nowDate)){
		%>
		&nbsp;
		<%		
	}else{
				if (date.before(nowDate)) {
			%>
			&nbsp;
			<%
					}
			else{
	%>
	
		<form name="set_student" method="post" action="../setstudent">
			<div class="div-right">
				<input type="text" placeholder="输入学号 " name="aa" /> <input
					type="button" value="查找" onClick="gorow()" /> <a
					href="javascript:document.set_student.submit();">确定</a>
			</div>
			<table class="table table-bordered" id="t1">
				<caption>
					<h3>安排学生</h3>
				</caption>
				<thead>
					<tr>
						<th>学生编号</th>
						<th>学生姓名</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
					    StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
						for (int i = 0; i < studentDaoImpl
								.getStudentList().size(); i++) {
							Student stu = studentDaoImpl
									.getStudentList().get(i);
							pageContext.setAttribute("student", stu);
					%>

					<TR>
						<TD id="<%=stu.getNumber()%>"><jsp:getProperty name="student"
								property="number" /></TD>
						<TD><jsp:getProperty name="student" property="stuname" /></TD>
						<TD><input type="checkbox" name="studentlist"
							value="<%=stu.getStuname()%>"></TD>
						<%
							}
						%>
					</TR>
				</tbody>
			</table>
		</form>
		<%
			}}
						%>
	</div>
	<div class="col-xs-2"></div>

</body>
</html>