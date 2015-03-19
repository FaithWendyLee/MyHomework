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
        //����������
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //�����µ�һҳ
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //����Ҫ��ʾ������,����һ����Ԫ�񣬵�һ������Ϊ�����꣬�ڶ�������Ϊ�����꣬����������Ϊ����
        Label hnumber = new Label(0,0,"��ҵ���");
        sheet.addCell(hnumber);
        Label studuetime = new Label(1,0,"ѧ���ύ��ֹ����");
        sheet.addCell(studuetime);
        Label assduetime = new Label(2,0,"�������Ľ�ֹ����");
        sheet.addCell(assduetime);
        Label format = new Label(3,0,"��ҵ�ļ���ʽ");
        sheet.addCell(format);
        Label score = new Label(4,0,"����");
        sheet.addCell(score);
        Label difficulty = new Label(5,0,"�Ѷ�");
        sheet.addCell(difficulty);
        Label content = new Label(6,0,"����");
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
        
              
        //�Ѵ���������д�뵽������У����ر������
        workbook.write();
        workbook.close();
        os.close();
    }
    
}
