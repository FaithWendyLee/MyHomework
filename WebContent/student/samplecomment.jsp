<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="factory.DaoFactory"%>
<%@page import="model.Course"%>
<%@page import="model.SampleComment"%>
<%@page import="service.SampleCommentService"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="dao.impl.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>samplecomment</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/homeworks.js"></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="left.jsp"></jsp:include>
	<div class="col-xs-8">
		<%
			request.setCharacterEncoding("utf-8");
			String cid = (String) request.getParameter("param");
		%>
		<div class="panel panel-default">
			<div class="panel-body courseinfo">
				请下载文件<br>
				<hr>
				<%
				ApplicationContext ac = new ClassPathXmlApplicationContext(
						"applicationContext.xml");
				CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac
						.getBean("courseDaoImpl");
				SampleCommentDaoImpl sampleCommentDaoImpl = (SampleCommentDaoImpl) ac
						.getBean("sampleCommentDaoImpl");
					for (int i = courseDaoImpl.getCourseList()
							.size() - 1; i >= 0; i--) {
						Course c = courseDaoImpl.getCourseList()
								.get(i);
						String ccid = c.getCid() + "";

						if (ccid.equals(cid)) {
							ArrayList<SampleComment> sl = sampleCommentDaoImpl.getSampleComment();
							ArrayList<String> slString = new ArrayList<String>();
							for (int j = 0; j < sl.size(); j++) {
								if (sl.get(j).getCourseid() == c.getCid()) {
									slString.add(sl.get(j).getFilename());
								}
							}
							String pathwenjian = "D://apache-tomcat-7.0.56//webapps//data//teacher";
							//声明集合存放目录下所有文件的文件名
							ArrayList<String> fileList = new ArrayList<String>();
							File folder = new File(pathwenjian);
							//判断文件夹是否存在并且是否是一个目录
							if (folder.exists() && folder.isDirectory()) {
								//获得目录中所有文件及目录
								File[] files = folder.listFiles();
								for (File file : files) {
									//如果是文件
									if (file.isFile()
											&& slString.contains(file.getName())) {
										//将文件名放入集合中
										fileList.add(file.getName());
									}
								}
							}

							Date nowdate=new Date();
							for (int k = 0; k < fileList.size(); k++) {
								if (c.Isbetween(nowdate)) {
									%>
									<h4><%=fileList.get(k)%></h4>
									<%
								}
								else{
				%>
				<a href="../downloadsamplecomment?filename=<%=fileList.get(k)%>"><%=fileList.get(k)%></a><br />
				<%
								}}}}
				%>
			</div>
		</div>

	</div>
	<div class="col-xs-2"></div>



</body>
</html>