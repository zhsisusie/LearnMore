package com.chatRoom.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 * @author zhsi
 *测试从本地获取图片显示在JSP页面上，跟之前的将图片存在数据库中不同，现在是将图片存在我的E盘中，数据库中存图片的名字
 */

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/showPic")
	public String testDis(){
		return "test";
	}
	
	@RequestMapping("/tt")
	public void testWriter(HttpServletRequest request,HttpServletResponse response){
		FileInputStream is;
		
		//设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setContentType("application/octet-stream;charset=UTF-8");
		try {
			//读取图片
			is=new FileInputStream("E:\\picture\\2.jpg");
			int i=is.available();//得到文件大小
			byte data[]=new byte[i];
			is.read(data);
			is.close();
			
			//写图片
			OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
