package servlets.personincharge;
import java.awt.Color;  
import java.awt.Font;  
import java.io.IOException;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.DefaultCategoryDataset;  
@SuppressWarnings("serial")  
public class homeworkarrange1 extends HttpServlet {  
    protected void doPost(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
    	request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String month=request.getParameter("month");
		session.setAttribute("month", month);
		response.sendRedirect(request.getContextPath() + "/personincharge/homeworkarrange.jsp");
		
    }  
}  