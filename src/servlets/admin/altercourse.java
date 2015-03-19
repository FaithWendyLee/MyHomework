package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.CourseDaoImpl;
import model.Course;

@WebServlet(("/Altercourse"))
public class altercourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public altercourse() {
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

		String alter = request.getParameter("altercourse");
		if (alter == null) {
			JOptionPane.showMessageDialog(null, "请选择要修改的课程！balala~~~");
			response.sendRedirect(request.getContextPath()
					+ "/admin/mngcourser.jsp");
			return;
		}
		int courseid = Integer.parseInt(alter);

		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) ac.getBean("courseDaoImpl");
		Course course = courseDaoImpl.getCourse(courseid);
		session.setAttribute("course", course);
		session.setAttribute("courseid", courseid);
		response.setContentType("text/html");
		response.sendRedirect(request.getContextPath() + "/admin/altercourse.jsp");
	}

}
