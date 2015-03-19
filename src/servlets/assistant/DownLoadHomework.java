package servlets.assistant;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadHomework extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String path = "D://apache-tomcat-7.0.56//webapps//data//student";
		String fileName = new String(request.getParameter("filename").getBytes("ISO8859-1"),"UTF-8");
		
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("utf-8"), "ISO-8859-1"));

		  File file = new File(path + "/" + fileName);
		  //如果文件存在
		  if (file.exists()) {
		   //设置响应类型及响应头
		   response.setContentType("application/x-msdownload");
		   //读取文件
		   InputStream inputStream = new FileInputStream(file);
		   BufferedInputStream bis = new BufferedInputStream(inputStream);
		   byte[] bytes = new byte[1024];

		   ServletOutputStream outStream = response.getOutputStream();
		   BufferedOutputStream bos = new BufferedOutputStream(outStream);
		   int readLength = 0;
		   while ((readLength = bis.read(bytes)) != -1) {
		    bos.write(bytes, 0, readLength);
		   }
		   //释放资源
		   inputStream.close();
		   bis.close();
		   bos.flush();
		   outStream.close();
		   bos.close();
		  }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
