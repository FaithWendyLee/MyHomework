package servlets.teacher;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.PlanDaoImpl;
import model.Plan;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class importhp {
    public void createExcel(OutputStream os,String cid) throws WriteException,IOException{
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label hnumber = new Label(0,0,"作业编号");
        sheet.addCell(hnumber);
        Label studuetime = new Label(1,0,"学生提交截止日期");
        sheet.addCell(studuetime);
        Label assduetime = new Label(2,0,"助教批改截止日期");
        sheet.addCell(assduetime);
        Label format = new Label(3,0,"作业文件格式");
        sheet.addCell(format);
        Label score = new Label(4,0,"分数");
        sheet.addCell(score);
        Label difficulty = new Label(5,0,"难度");
        sheet.addCell(difficulty);
        Label content = new Label(6,0,"内容");
        sheet.addCell(content);
        int j=1;
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        PlanDaoImpl planDaoImpl = (PlanDaoImpl) ac.getBean("planDaoImpl");
        for(int i = 0; i < planDaoImpl.getPlanList().size(); i++){
        	Plan p=planDaoImpl.getPlanList().get(i);
            if((p.getCid()+"").equals(cid)){
            	Label hnumberp = new Label(0,j,p.getPlannumber());
                sheet.addCell(hnumberp);
                Label studuetimep = new Label(1,j,p.getStuduetime());
                sheet.addCell(studuetimep);
                Label assduetimep = new Label(2,j,p.getAssduetime());
                sheet.addCell(assduetimep);
                Label formatp = new Label(3,j,p.getFormat());
                sheet.addCell(formatp);
                Label scorep = new Label(4,j,p.getScore());
                sheet.addCell(scorep);
                Label difficultyp = new Label(5,j,p.getDifficulty());
                sheet.addCell(difficultyp);
                Label contentp = new Label(6,j,p.getContent());
                sheet.addCell(contentp);
                j++;
            } 
        }
        
              
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
    
}
