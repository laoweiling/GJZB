package com.lnsf.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * @author肖梦雅 
 * @version 创建时间：2017年7月26日 晚21:26
 * @introduction 创建验证码的生成类 ，用于生成登录时的验证码
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeUtil extends HttpServlet{
	public static Random r = new Random();
	public static String[] fontNames  = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
	/*
	 * 验证码
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	public static void getVerifyCodeDbutil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int w = 70;
		int h = 35;
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		//随机产生的数据和字幕
		String codes = "23456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String text;
		StringBuilder sb = new StringBuilder();
		//随机输出数字加字母
		for (int i = 0; i < 4; i++) {
			int index = r.nextInt(codes.length());
			char code = codes.charAt(index);
			sb.append(code + "");
			float x = i * 1.0F * w / 4;
			g.setFont(randomFont());
			g.setColor(randomColor());
			g.drawString(code + "", x, h-5);
		}
		text = sb.toString();
		//把验证码保存到session中
		request.getSession().setAttribute("verifyCode", text);
		
		//验证码加上干扰线
		for(int i=0; i < 3; i++ ){
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g.setStroke(new BasicStroke(1.5F)); 
			g.setColor(Color.blue);
			g.drawLine(x1, y1, x2, y2);
		}
		
		ImageIO.write(image, "JPG", response.getOutputStream());
		
	}
	
	public static Color randomColor () {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red, green, blue);
	}
	public static Font randomFont () {
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(5) + 24; 
		return new Font(fontName, style, size);
	}

}
