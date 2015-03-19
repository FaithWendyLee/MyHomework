package servlets.personincharge;
import java.awt.Color;  
import java.awt.Font;  
import java.io.IOException;  
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import model.Plan;

import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.PlanDaoImpl;

@SuppressWarnings("serial")  
public class difficulty extends HttpServlet {  
    protected void service(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");  
        // ʹ����ͨ���ݼ�  

		
		String month = (String) request.getParameter("param");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
		
		ArrayList<Plan> plist=new ArrayList<Plan>();
		ArrayList<Plan> ps=planDaoImpl.getPlanList();
		for(int j=0;j<ps.size();j++){
			try {
				if(ps.get(j).getIsupload(month)){
					plist.add(ps.get(j));				
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int hardnum=0;
		int mediumnum=0;
		int easynum=0;
		for(int i=0;i<plist.size();i++){
			if(plist.get(i).getDifficulty().equals("hard")){
				hardnum++;
			}else if(plist.get(i).getDifficulty().equals("medium")){
				mediumnum++;
			}else if(plist.get(i).getDifficulty().equals("easy")){
				easynum++;
			}
		}
		System.out.println(hardnum+",,"+mediumnum+",,"+easynum);

        // Ĭ����������  
        DefaultPieDataset dataType = new DefaultPieDataset();  
        // ���ݲ��� ���ݣ�����  
        dataType.setValue("hard", hardnum);  
        dataType.setValue("medium", mediumnum);  
        dataType.setValue("easy", easynum);  
        try {  
            DefaultPieDataset data = dataType;  
            // ������ͨ��״ͼ���� 3D ����  
            // ����3D��״ͼ  
            PiePlot3D plot = new PiePlot3D(data);  
            JFreeChart chart = new JFreeChart(  
                    "",            // ͼ�α���  
                    JFreeChart.DEFAULT_TITLE_FONT, // ��������  
                    plot,                          // ͼ��������  
                    true                           // �Ƿ���ʾͼ��  
            );  
            // ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����                  
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getInstance(), new DecimalFormat("0.00%")));
             plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));  
            // ��������ͼƬ�ı���ɫ  
            chart.setBackgroundPaint(Color.WHITE);  
            // ����ͼƬ�б߿�  
            chart.setBorderVisible(true);  
            // ��������  
            Font kfont = new Font("����", Font.PLAIN, 12);    // �ײ�  
            Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����  
            // ͼƬ����  
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
            // �ײ�  
            chart.getLegend().setItemFont(kfont);  
            ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,  
                    chart, 500, 300, null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  