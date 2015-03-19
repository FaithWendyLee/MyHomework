package servlets.personincharge;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class scoreupload1
 */
public class scoreupload1 extends HttpServlet {
	protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
    	request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String month=request.getParameter("month");
		session.setAttribute("month", month);
		response.sendRedirect(request.getContextPath() + "/personincharge/scoreupload.jsp");
		
    }  

}
