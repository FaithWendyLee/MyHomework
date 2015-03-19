<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="factory.DaoFactory"%>
<%@page import="model.Homework"%>
<%@page import="model.Course"%>
<%@page import="model.Student"%>
<%@page import="model.Plan"%>
<%@page import="service.CourseService"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="course" class="model.Course" scope="page">
</jsp:useBean>
<jsp:useBean id="homework" class="model.Homework" scope="page">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Homework</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="col-xs-2"></div>
	<div class="col-xs-8">
		<form name="frm" method="post" action="">
			<table class="table table-bordered">
				<caption>我的作业</caption>
				<thead>
					<tr>
						<th>课程</th>
						<th>作业编号</th>
						<th>截止日期</th>
						<th>作业状态</th>
					</tr>
				</thead>
				<tbody>

					<%
						request.setCharacterEncoding("utf-8");
						int id = (Integer)request.getSession().getAttribute("stuid");
						ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
						StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
						Student stu=studentDaoImpl.findById(id);
						HomeworkDaoImpl homeworkDaoImpl = (HomeworkDaoImpl) ac.getBean("homeworkDaoImpl");
						CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
						PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
						String stuname=stu.getStuname();
						ArrayList<String> hs=new ArrayList<String>();
						for(int t=0;t<homeworkDaoImpl
								.getHomework().size();t++){
							hs.add(homeworkDaoImpl
									.getHomework().get(t).getPid()+"");
						}
						for (int i = 0; i < courseDaoImpl
								.getCourseList().size(); i++) {
							boolean isExist = false;
							Course c = courseDaoImpl
									.getCourseList().get(i);
							pageContext.setAttribute("course", c);
							if(c.getStudentlist()==null){
								continue;
							}else if (c.getStudentlist().equals(stuname))
								isExist = true;
							String[] arr = c.getStudentlist().split(" ");
							for (int j = 0; j < arr.length; j++) {
								if (arr[j].equals(stuname)) {
									isExist = true;
								}
							}
							if (isExist) {
								for (int j = 0; j < planDaoImpl
										.getPlanList().size(); j++) {
									Plan p = planDaoImpl
											.getPlanList().get(j);
									if(p.getCid()==c.getCid()){
					%>
					<TR>
						<TD><a href="courseinfostu.jsp?param=<%=c.getCid()%>"><%=c.getCname()%></a></TD>
						<TD><a href="uploadhomework.jsp?param=<%=c.getCid()%>"><%=p.getPlannumber()%></a></TD>
						
						
						<%
							if (c.isOverdue(p.getStuduetime())) {
						%>
						<TD>已结束提交</TD>
						<%
							} else {
						%>
						<TD><%=p.getStuduetime()%></TD>
						<%
							}
							if(hs.contains(p.getPid()+"")){
%>
								
								<TD>已提交</TD>
<% 							}
							else{
								%>
								<TD>未提交</TD>
<% 							
}}}}}
						%>
					</TR>
				</tbody>
			</table>
		</form>
	</div>
	<div class="col-xs-2"></div>



</body>
</html>