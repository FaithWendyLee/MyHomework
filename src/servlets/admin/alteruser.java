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

import dao.impl.AssistantDaoImpl;
import dao.impl.PersonInChargeDaoImpl;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Assistant;
import model.PersonInCharge;
import model.Student;
import model.Teacher;

/**
 * Servlet implementation class alteruser
 */
@WebServlet(("/Alteruser"))
public class alteruser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alteruser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		String alter=request.getParameter("alteruser");			

		if(alter==null){
			JOptionPane.showMessageDialog(null,"请选择要修改的用户！");
			response.sendRedirect(request.getContextPath() + "/admin/mnguser.jsp");
			return;
		}
		char identity=alter.charAt(0);
		String alter2=alter.substring(1);
		int userid=Integer.parseInt(alter2);
		
		if(identity=='0'){
			StudentDaoImpl studentDaoImpl = (StudentDaoImpl) ac.getBean("studentDaoImpl");
			Student stu=studentDaoImpl.findById(userid);
			session.setAttribute("flag",identity);
			session.setAttribute("student", stu);
			session.setAttribute("studentid", userid);
			response.setContentType("text/html");
			response.sendRedirect(request.getContextPath() + "/admin/alteruser.jsp");
		}else if(identity=='1'){
			AssistantDaoImpl assistantDaoImpl = (AssistantDaoImpl) ac.getBean("assistantDaoImpl");
			Assistant ass=assistantDaoImpl.findbyID(userid);
			session.setAttribute("flag",identity);
			session.setAttribute("assistant", ass);
			session.setAttribute("assistantid", userid);
			response.setContentType("text/html");
			response.sendRedirect(request.getContextPath()+"/admin/alteruser.jsp");
		}else if(identity=='2'){
			TeacherDaoImpl teacherDaoImpl = (TeacherDaoImpl) ac.getBean("teacherDaoImpl");
			Teacher tea=teacherDaoImpl.getTeacher(userid);
			session.setAttribute("flag", identity);
			session.setAttribute("teacher", tea);
			session.setAttribute("teacherid", userid);
			response.setContentType("text/html");
			response.sendRedirect(request.getContextPath()+"/admin/alteruser.jsp");
			
		}else if(identity=='3'){
			PersonInChargeDaoImpl picDaoImpl = (PersonInChargeDaoImpl) ac.getBean("personInChargeDaoImpl");
			
			PersonInCharge pic=picDaoImpl.getPersonInCharge(userid);
			session.setAttribute("flag", identity);
			session.setAttribute("pic", pic);
			session.setAttribute("picid", userid);
			response.setContentType("text/html");
			response.sendRedirect(request.getContextPath()+"/admin/alteruser.jsp");
		}
		
		
		
	}

}
