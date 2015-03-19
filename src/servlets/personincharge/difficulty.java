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
        // 使用普通数据集  

		
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

        // 默认数据类型  
        DefaultPieDataset dataType = new DefaultPieDataset();  
        // 数据参数 内容，数量  
        dataType.setValue("hard", hardnum);  
        dataType.setValue("medium", mediumnum);  
        dataType.setValue("easy", easynum);  
        try {  
            DefaultPieDataset data = dataType;  
            // 生成普通饼状图除掉 3D 即可  
            // 生产3D饼状图  
            PiePlot3D plot = new PiePlot3D(data);  
            JFreeChart chart = new JFreeChart(  
                    "",            // 图形标题  
                    JFreeChart.DEFAULT_TITLE_FONT, // 标题字体  
                    plot,                          // 图标标题对象  
                    true                           // 是否显示图例  
            );  
            // 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例                  
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getInstance(), new DecimalFormat("0.00%")));
             plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));  
            // 设置整个图片的背景色  
            chart.setBackgroundPaint(Color.WHITE);  
            // 设置图片有边框  
            chart.setBorderVisible(true);  
            // 配置字体  
            Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部  
            Font titleFont = new Font("宋体", Font.BOLD, 25); // 图片标题  
            // 图片标题  
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
            // 底部  
            chart.getLegend().setItemFont(kfont);  
            ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,  
                    chart, 500, 300, null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  