
package servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.AssistantDaoImpl;
import dao.impl.PersonInChargeDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Assistant;
import model.PersonInCharge;
import model.Student;
import model.Teacher;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		response.setContentType("text/html");
		String number=request.getParameter("number");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String identity=request.getParameter("identity");
		
		if(identity.equals("选课学生")){
			Student stu=new Student(name,pwd);
			stu.setNumber(number);
			StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
			studentDaoImpl.add(stu);
			response.sendRedirect(request.getContextPath()+"/admin/mnguser.jsp");
		}else if(identity.equals("助教")){
			Assistant assistant=new Assistant(name,pwd);
			AssistantDaoImpl assistantDaoImpl = (AssistantDaoImpl) ac.getBean("assistantDaoImpl");
			assistantDaoImpl.add(assistant);
			response.sendRedirect(request.getContextPath()+"/admin/mnguser.jsp");
		}else if(identity.equals("授课教师")){
			Teacher tea=new Teacher(name,pwd);
			TeacherDaoImpl teacherDaoImpl = (TeacherDaoImpl) ac.getBean("teacherDaoImpl");
			teacherDaoImpl.addTeacher(tea);
			response.sendRedirect(request.getContextPath()+"/admin/mnguser.jsp");			
		}else if(identity.equals("教学负责人")){
			PersonInChargeDaoImpl picDaoImpl = (PersonInChargeDaoImpl) ac.getBean("personInChargeDaoImpl");
			PersonInCharge rp=new PersonInCharge(name,pwd);
			picDaoImpl.addPersonInCharge(rp);
			response.sendRedirect(request.getContextPath()+"/admin/mnguser.jsp");			
		}
	}
}
