package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.CourseDaoImpl;
import model.Course;

@WebServlet("/Altercourse2")
public class altercourse2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public altercourse2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Course c = (Course) session.getAttribute("course");
		int cid = (int) session.getAttribute("courseid");
		response.setContentType("text/html");
		String cnumber = request.getParameter("number");
		String cname = request.getParameter("name");
		String term = request.getParameter("term");
		String sc[] = request.getParameterValues("teacherlist");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<sc.length;i++){
			sb.append(sc[i]);
		}
		String teacherlist=sb.toString();
		c.setCid(cid);
		c.setCnumber(cnumber);
		c.setCname(cname);
		c.setTerm(term);
		c.setTeacherlist(teacherlist);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
		courseDaoImpl.alterCourse(c);
		response.sendRedirect(request.getContextPath() + "/admin/mngcourse.jsp");

	}
}
