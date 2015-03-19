package servlets.student;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Homework;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.HomeworkDaoImpl;

/**
 * Servlet implementation class uploadhomework
 */
public class uploadhomework extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final long fileSizeMax = 10 * 1024 * 1024; // 文件最大大小

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadhomework() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();

		
		response.setContentType("text/html");
		String pids = (String) session.getAttribute("pid");
		int planid = Integer.parseInt(pids);
		int stuid = (Integer)session.getAttribute("stuid");
		String cid=(String)session.getAttribute("cid");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		HomeworkDaoImpl homeworkDaoImpl = (HomeworkDaoImpl) ac.getBean("homeworkDaoImpl");
		ArrayList<Homework> ah=homeworkDaoImpl.getHomework();
		ArrayList<String> as=new ArrayList<String>();
		for(int i=0;i<ah.size();i++){
			if(ah.get(i).getSid()==stuid){
			as.add(ah.get(i).getPid()+"");
		}}
		if(as.contains(pids)){
			String filePath = "D://apache-tomcat-7.0.56//webapps//data//student";
			Homework h=homeworkDaoImpl.getHomeworkBypid(planid);
			File deleteFile = new File(filePath + "/" + h.getHname());
		    if (deleteFile.isFile() && deleteFile.exists()) {  
		    	deleteFile.delete();  }
		      
		    homeworkDaoImpl.remove(planid);
		}
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 1M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository 贮藏室，即临时文件目录

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// 设置允许上传的最大文件大小 4M
			// upload.setSizeMax(fileSizeMax);
			// 解析HTTP请求消息头
			List fileItems = upload.parseRequest(request);
			Iterator iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
				} else {
					String filename = item.getName();
					int index = filename.lastIndexOf("\\");
					filename = filename.substring(index + 1, filename.length());

					long fileSize = item.getSize();

					if ("".equals(filename) && fileSize == 0) {
						return;
					}

					if (fileSize > fileSizeMax) {
						return;
					}

					String filePath = "D://apache-tomcat-7.0.56//webapps//data//student";
					File uploadFile = new File(filePath + "/" + filename);
					item.write(uploadFile);

					Homework hw = new Homework();

					hw.setPid(planid);
					hw.setSid(stuid);
					hw.setHname(filename);
					Date nowDate = new Date();
					String sdate=(new SimpleDateFormat("yyyy-MM-dd")).format(nowDate); 
					hw.setHtime(sdate);
					homeworkDaoImpl.uploadHw(hw);
					response.sendRedirect(request.getContextPath()
							+ "/student/uploadhomework.jsp?param=" + cid);
				}
			}// end while()
		} catch (Exception e) {
			e.printStackTrace();
		}// end try ... catch ...
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

}
