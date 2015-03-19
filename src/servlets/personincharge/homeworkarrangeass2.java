package servlets.personincharge;
import java.awt.Color;  
import java.awt.Font;  
import java.io.IOException;  
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import model.Course;
import model.Plan;

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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.CourseDaoImpl;
import dao.impl.PlanDaoImpl;
@SuppressWarnings("serial")  
public class homeworkarrangeass2 extends HttpServlet {  
    protected void service(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");  
        // ʹ����ͨ���ݼ�  
        DefaultCategoryDataset chartDate = new DefaultCategoryDataset(); 

		
		String month = (String) request.getParameter("param");
		ApplicationContext acc = new ClassPathXmlApplicationContext("applicationContext.xml");
		CourseDaoImpl courseDaoImpl = (CourseDaoImpl) acc.getBean("courseDaoImpl");
		
		ArrayList<Course> clist=new ArrayList<Course>();
		ArrayList<Course> ac= courseDaoImpl.getCourseList();
		for(int j=0;j<ac.size();j++){
			try {
				if(ac.get(j).getIsbetween(month)){
					clist.add(ac.get(j));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0;i<clist.size();i++){
			ArrayList<Plan> plist=new ArrayList<Plan>();
			PlanDaoImpl planDaoImpl = (PlanDaoImpl) acc.getBean("planDaoImpl");
			ArrayList<Plan> ap= planDaoImpl.getPlanList();
			for(int j=0;j<ap.size();j++){
				try {
					if((ap.get(j).getCid()==clist.get(i).getCid())&&(ap.get(j).getExistass(month))){
						plist.add(ap.get(j));
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			chartDate.addValue(plist.size(), "��ֹ��ҵ��", clist.get(i).getCname());
		}
       
        try {  
            // �����ݿ��л�����ݼ�  
            DefaultCategoryDataset data = chartDate;  
              
            // ʹ��ChartFactory����3D��״ͼ������ʹ��3D��ֱ��ʹ��createBarChart  
            JFreeChart chart = ChartFactory.createBarChart3D(  
                    "", // ͼ�����  
                    "�γ�", // Ŀ¼�����ʾ��ǩ  
                    "��ֹ��ҵ��", // ��ֵ�����ʾ��ǩ  
                    data, // ���ݼ�  
                    PlotOrientation.VERTICAL, // ͼ���򣬴˴�Ϊ��ֱ����  
                    // PlotOrientation.HORIZONTAL, //ͼ���򣬴˴�Ϊˮƽ����  
                    true, // �Ƿ���ʾͼ��  
                    true, // �Ƿ����ɹ���  
                    false // �Ƿ�����URL����  
                    );            
            // ��������ͼƬ�ı���ɫ  
            chart.setBackgroundPaint(Color.WHITE);  
            // ����ͼƬ�б߿�  
            chart.setBorderVisible(true);  
            Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�  
            Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����  
            // ͼƬ����  
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
            // �ײ�  
            chart.getLegend().setItemFont(kfont);  
            // �õ�������������������  
            CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
            categoryplot.setDomainGridlinesVisible(true);  
            categoryplot.setRangeCrosshairVisible(true);  
            categoryplot.setRangeCrosshairPaint(Color.blue);  
            NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();  
            barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));  
            barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12));  
            CategoryAxis domainAxis = categoryplot.getDomainAxis();           
            /*------����X�������ϵ�����-----------*/  
            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
            /*------����X��ı�������------------*/  
            domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
            /*------����Y�������ϵ�����-----------*/  
            numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
            /*------����Y��ı�������------------*/  
            numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
            /*------���������˵ײ��������������-----------*/  
            chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
            // ����ͼƬ�����  
            ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,  
                    chart, 500, 300, null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  