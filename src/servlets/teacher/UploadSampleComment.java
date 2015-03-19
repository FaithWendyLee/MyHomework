package servlets.teacher;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import model.SampleComment;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.impl.PlanDaoImpl;
import dao.impl.SampleCommentDaoImpl;

public class UploadSampleComment extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
		 * 
		 */

	private final long fileSizeMax = 10 * 1024 * 1024; // 文件最大大小

	public UploadSampleComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	// doPost
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		System.out.print("hello~~~");
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();

		res.setContentType("text/html");
		String courseid = (String) session.getAttribute("courseid");
		int cid = Integer.parseInt(courseid);
		try {
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			// threshold 极限、临界值，即硬盘缓存 1M
			diskFactory.setSizeThreshold(4 * 1024);
			// repository 贮藏室，即临时文件目录

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			// 设置允许上传的最大文件大小 4M
			// upload.setSizeMax(fileSizeMax);
			// 解析HTTP请求消息头
			List fileItems = upload.parseRequest(req);
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

					String filePath = "D://apache-tomcat-7.0.56//webapps//data//teacher";
					File uploadFile = new File(filePath + "/" + filename);
					item.write(uploadFile);

					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					System.out.println(df.format(new Date()));// new
																// Date()为获取当前系统时间
					String uptime = df.format(new Date());
					SampleComment mf = new SampleComment();

					mf.setCourseid(cid);
					mf.setFilename(filename);
					mf.setUptime(uptime);
					ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
					SampleCommentDaoImpl sampleCommentDaoImpl = (SampleCommentDaoImpl) ac.getBean("sampleCommentDaoImpl");
					sampleCommentDaoImpl.addSampleComment(mf);
					res.sendRedirect(req.getContextPath()
							+ "/teacher/uploadSampleComment.jsp?param=" + cid);
				}
			}// end while()
		} catch (Exception e) {
			e.printStackTrace();
		}// end try ... catch ...
	}// end doPost()

	// doGet
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		this.doPost(req, res);
	}
}