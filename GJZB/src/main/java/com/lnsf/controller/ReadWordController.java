package com.lnsf.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lnsf.entity.Project;
import com.lnsf.entity.WordText;
import com.lnsf.service.ProjectService;
import com.lnsf.service.ReadWordService;

/**
 * @author 黄卉
 * @version 创建时间：2017年7月31日 下午8:26:27
 * @introduction （1）下载文件接口：/Resourcesdownload.do?path=文件名称（自动去D盘的homework下边）
 *               （2）读取文件接口：/ReadWord/readWord 接受post参数：filePath （3）文件上传
 */
@Controller
@RequestMapping("/ReadWord")
public class ReadWordController {
	@Autowired
	ReadWordService readWordService;

	@Autowired
	ProjectService projectService;

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月31日 下午8:26:27
	 * @introduction 读取文档到jsp中 f://1.2 论文.doc
	 */
	@ResponseBody
	// @RequestMapping(value="/readWord",produces="text/html;
	// charset=UTF-8")//f://1.2 论文.doc
	@RequestMapping(value = "/readWord", method = RequestMethod.POST) // f://1.2
																		// 论文.doc
	public WordText readWord(@RequestParam("filePath") String filePath) {
		// 默认是D盘下image的homework文档
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("fileconfig.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String basePath = prop.getProperty("basePath").trim();

		System.out.println("filePath：" + basePath + filePath);
		WordText wordText = new WordText();
		wordText.setWordName(filePath);
		// 添加默认路径d盘下的image下的homework
		wordText.setText(readWordService.readWord(basePath + filePath));
		System.out.println(wordText);
		return wordText;
	}

	/**
	 * 文件下载
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年8月1日09:49:36
	 * @introduction 下载文件
	 */
	// -------------------------------------------------------
	@RequestMapping(value = "/Resourcesdownload.do")
	// ------------------单文件,作业区下载--------------------------
	public void download2(@RequestParam("path") String path, HttpServletResponse response) throws IOException {
		System.out.println("Resourcesdownloa");
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("fileconfig.properties");
		prop.load(in);
		String basePath = prop.getProperty("basePath").trim();
		String fileName = null;
		try {
			fileName = new String(path.getBytes("GBK"), "iso-8859-1");// 为了解决中文名称乱码问题
		} catch (Exception e) {
			System.out.println("new String(path.getBytes异常");
		}
		System.out.println("path:" + path);
		System.out.println("fileName:" + fileName);
		path = basePath + path;

		File file = new File(path); // file.getName与上获取一致
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			System.out.println("file.getName():" + file.getName());
			String theFileName = new String(file.getName().getBytes("GBK"), "iso-8859-1");// 为了解决中文名称乱码问题
			// --------------------------------
			response.reset(); // 去除空白航
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + theFileName);// 关系到下载时候弹出在文件的名称
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");

			// ------------------------------
			byte[] buffer = new byte[1024 * 1024]; // 乘以3
			int i = -1;
			while ((i = fis.read(buffer)) != -1) {
				toClient.write(buffer, 0, i); // 分块读取
			}
			fis.close();
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			PrintWriter out = null;

			try {
				out = response.getWriter();
			} catch (IOException e) {
				System.out.println("异常");
				// e.printStackTrace();
			}
			out.print("<script>");
			out.print("alert(\"not find the file\")");
			out.print("</script>");
		}
	}

	/**
	 * 文件上传
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年8月1日09:49:36
	 * @introduction 上传文件
	 */
	@ResponseBody
	@RequestMapping(value = "/addProjectResource")
	public int addProjectResource(@ModelAttribute Project project,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
		String newFileName = null;
		System.out.println(project);
		System.out.println("project.getUser().getUserId():" + project.getUser().getUserId());
		System.out.println("project.getType().getTypeId():" + project.getType().getTypeId());
		try {
			String fileName = file.getOriginalFilename();// 获得文件名字
			// fileName = java.net.URLEncoder.encode(fileName, "GBK");
			// fileName = new String(fileName.getBytes("ISO-8859-1"),"GBK");
			System.out.println("fileName：" + fileName);
			Properties prop = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("fileconfig.properties");
			prop.load(in);
			String basePath = prop.getProperty("basePath").trim();

			if (!("").equals(fileName)) {
				System.out.println("开始上传");
				String path = "D:/image/homework"; // 在当前项目下的image盘下边
				// ------------------上传图片到image---------------------
				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String date = fmt.format(new Date());
				newFileName = project.getUser().getUserId() + date + "_" + fileName;
				System.out.println(newFileName);
				File targetFile = new File(basePath, newFileName);
				if (!targetFile.exists()) {
					System.out.println("保存文件");
					targetFile.mkdirs();
				}

				// 保存
				try {

					// 1.保存项目
					file.transferTo(targetFile);
					// 2.更新项目名称
					project.setProjectcontent(newFileName);
					System.out.println("存储进去数据库信息：" + project);

					int rows = projectService.addProject(project);
					System.out.println("rows:" + rows);
					// 4.修改session里面的用户信息
					request.getSession().setAttribute("project", project);

					// 5.把数据返回给前台
					if (rows > 0) {
						return 1;// 成功上传图片
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("上传文件为空");
			}
		} catch (Exception e) {
			System.out.println("上传文件出现错误！");
			e.printStackTrace();
		}
		return 0;
	}
}
