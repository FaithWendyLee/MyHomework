package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Course {
	private int cid;
	private String cnumber;
	private String cname;
	private String term;
	private Date begintime;
	private Date endtime;
	private String teacherlist;
	private String assistantlist;
	private String studentlist;

	@Id
	public int getCid(){
		return cid;
	}
	public void setCid(int id){
		this.cid=id;
	}
	
	public String getCnumber(){
		return cnumber;
	}
	public void setCnumber(String number){
		this.cnumber=number;
	}
	
	public String getCname(){
		return cname;
	}
	public void setCname(String name){
		this.cname=name;
	}
	
	public String getTerm(){
		return term;
	}
	public void setTerm(String term){
		this.term=term;
	}
	
	public Date getBegintime(){
		return begintime;
	}
	public void setBegintime(Date begintime){
		this.begintime=begintime;
	}
	public Date getEndtime(){
		return endtime;
	}
	public void setEndtime(Date endtime){
		this.endtime=endtime;
	}
	public String getTeacherlist(){
		return teacherlist;
	}
	public void setTeacherlist(String teacherlist){
		this.teacherlist=teacherlist;
	}
	
	public String getAssistantlist(){
		return assistantlist;
	}
	public void setAssistantlist(String assistantlist){
		this.assistantlist=assistantlist;
	}

	
	public String getStudentlist(){
		return studentlist;
	}
	public void setStudentlist(String studentlist){
		this.studentlist=studentlist;
	}
	

	public boolean Isbetween(Date d) throws ParseException{
		return (d.after(begintime)&&d.before(endtime));
	}
	
	public boolean isOverdue(String time) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Date date = sdf.parse(time);
	    Date nowDate = new Date();
		return nowDate.after(date);
	}
	
}
