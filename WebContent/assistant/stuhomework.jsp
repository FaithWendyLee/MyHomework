<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="factory.DaoFactory"%>
<%@page import="model.Plan"%>
<%@page import="model.Course"%>
<%@page import="model.Homework"%>
<%@page import="service.HomeworkService"%>
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
<title>stuhomework</title>
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
			int id = (Integer) request.getSession().getAttribute("assid");
			String cid = (String) request.getParameter("param");
			int ccid=Integer.parseInt(cid);
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
			PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
			Course c= courseDaoImpl.getCourse(ccid);
			for (int i = planDaoImpl.getPlanList()
					.size() - 1; i >= 0; i--) {
				Plan p =planDaoImpl.getPlanList()
						.get(i);
				String pcid = p.getCid() + "";

				if (pcid.equals(cid)) {
		%>
		<div class="panel panel-default">
			<div class="panel-body courseinfo">
				作业<%=p.getPlannumber()%><br>
				<hr>
				<%
				HomeworkDaoImpl homeworkDaoImpl = (HomeworkDaoImpl) ac.getBean("homeworkDaoImpl");
				    ArrayList<Homework> hwl=homeworkDaoImpl.getHomework();
				    ArrayList<String> hwlString=new ArrayList<String>();
				    for(int j=0;j<hwl.size();j++){
				    	if(hwl.get(j).getPid()==p.getPid()){
					    	hwlString.add(hwl.get(j).getHname());
					    }
				    }
					String pathwenjian = "D://apache-tomcat-7.0.56//webapps//data//student";
							//声明集合存放目录下所有文件的文件名
							ArrayList<String> fileList = new ArrayList<String>();
							File folder = new File(pathwenjian);
							//判断文件夹是否存在并且是否是一个目录
							if (folder.exists() && folder.isDirectory()) {
								//获得目录中所有文件及目录
								File[] files = folder.listFiles();
								for (File file : files) {
									//如果是文件
									if (file.isFile()&&hwlString.contains(file.getName())) {
										//将文件名放入集合中
										fileList.add(file.getName());
									}
								}
							}

							Date nowDate = new Date();
							for (int k = 0; k < fileList.size(); k++) {
								if (c.Isbetween(nowDate)) {
									%>
									<h4><%=fileList.get(k)%></h4>
									<%
								}
								else{
				%>
				<a href="../downloadhomework?filename=<%=fileList.get(k)%>"><%=fileList.get(k)%></a><br />
				<%
					}}
							
				%>
			</div>
		</div>

		<%
			}
			}
		%>

	</div>
	<div class="col-xs-2"></div>



</body>
</html>