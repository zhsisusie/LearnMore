package com.chatRoom.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chatRoom.model.Photo;
import com.chatRoom.service.IPhotoService;
import com.chatRoom.util.JsonUtil;

@Controller
@Configuration
@ImportResource("classpath:spring.xml")
@RequestMapping("/photo")
public class FileUploadController {
	
	@Resource
	private IPhotoService photoService;
	@RequestMapping(value="/tofile")
   public String toFileUpLoad(HttpServletRequest request,Model model){
	   return "fileUpLoad";
   }
	//@Value("#{settings['picPath.picUrl']}")
	@Value("${picUrl}")
	private  String picUrl;
	
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@RequestMapping("/addAction.do")
	@ResponseBody//将返回结果写到response中
	public String save(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam(value="photo",required=false)MultipartFile filedata) throws UnsupportedEncodingException{
		 /*response.setCharacterEncoding("UTF-8");
		 response.setHeader("Cache-Control", "no-cache");  
		 response.setContentType("application/json");*/
		
		 //PrintWriter out=null;
		if(filedata!=null&&!filedata.isEmpty()){
			//获取图片的文件名
			String fileName=filedata.getOriginalFilename();
			//获取图片的扩展名
			String extensionName=fileName.substring(fileName.lastIndexOf(".")+1);
			//新的图片名=获取时间戳+"."图片扩展名
			String newFileName=String.valueOf(System.currentTimeMillis())+"."+extensionName;
			System.out.println(picUrl);
			//将图片上传到服务器
			//saveFile(newFileName,filedata);
			saveFile(request,fileName,filedata);
			//将图片名称保存至数据库
			//photoService.insert(fileName,filedata.getBytes());
			
			
			//图片路径
			String realPath=request.getSession().getServletContext().getRealPath(picUrl+"\\"+newFileName);
			System.out.println("cddd:"+realPath);
			
			
			/*FileInputStream is;
			fileName=URLEncoder.encode(fileName, "UTF-8");
			response.reset();
			//设置content-disposition响应头控制浏览器以下载的形式打开文件
			response.setHeader("content-disposition","attachment;filename="+fileName);
			response.setContentType("application/octet-stream;charset=UTF-8");
			try {
				//读取图片
				is=new FileInputStream("E:\\picture\\1.jpg");
				int i=is.available();//得到文件大小
				byte data[]=new byte[i];
				is.read(data);
				is.close();
				
				//写图片
				OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
				System.out.println(response.getOutputStream());
				System.out.println("zhsi_1");
				outputStream.write(data);
				System.out.println("zhsi_2");
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		
		JSONArray jsonArray=new JSONArray();
		for(int i=3;i<4;i++){
			Photo p=photoService.getPhotoById(new BigDecimal(i));
			jsonArray.add(p);
		}
		
		//获取单个json对象
		/*JSONObject json=null;
		Photo p1=photoService.getPhotoById(new BigDecimal(3));
		json=JSONObject.fromObject(p1);*/
		
		
		//利用下面这种方式一直不成功
		/*try {
			out=response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		return jsonArray.toString();
	}
	private void saveFile(HttpServletRequest request,String newFileName, MultipartFile filedata) {
		//根据配置文件获取服务器图片存放路径
				String saveFilePath=picUrl;
				//String realPath=request.getSession().getServletContext().getRealPath(saveFilePath+"\\"+newFileName);
				//System.out.println("cddd:"+realPath);
				//输出为E:\workspace\Login_ssm_mav_waterfull\src\main\webapp\E:\picture\6.jpg
		//构建文件目录
		File tempFile=new File(saveFilePath);
		if(!tempFile.exists()){
			tempFile.mkdirs();
		}
		//保存文件到服务器
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(saveFilePath+"\\"+newFileName);
				fos.write(filedata.getBytes());
				//fos.flush();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}

