package servlets.admin;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.CourseDaoImpl;
import model.Course;

/**
 * Servlet implementation class AddUser
 */
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourse() {
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

		response.setContentType("text/html");
		String number=request.getParameter("number");
		String name = request.getParameter("name");
		String term = request.getParameter("term");
		String sc[] = request.getParameterValues("teacherlist");
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<sc.length;i++){
			sb.append(sc[i]+" ");
		}
		String teacherlist=sb.toString();
		Course c = new Course();
		c.setCnumber(number);
		c.setCname(name);
		c.setTerm(term);
		c.setTeacherlist(teacherlist);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
		courseDaoImpl.addCourse(c);
		session.setAttribute("course", c);
		response.sendRedirect(request.getContextPath() + "/admin/mngcourse.jsp");

	}
}
