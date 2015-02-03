package com.chatRoom.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatRoom.model.Photo;
import com.chatRoom.model.PhotoModel;
import com.chatRoom.service.IPhotoModelService;
/**
 * 
 * @author zhsi
 *数据库中只保存图片名字，从本地E盘读取图片显示在JSP页面上
 */

@Controller
@Configuration
@ImportResource("classpath:spring.xml")
@RequestMapping("/showPicture")
public class PhotoModelController extends HttpServlet{
	 private static final long serialVersionUID=95163763702302821L;
	 @Value("${picUrl}")
	 private  String picUrl;
	 @Resource
	 private IPhotoModelService photoModelService;
	 
	 
	 @RequestMapping("/toPicShow")
	 public String toPicShow(){
		 return "waterfullPic";
	 }
	 
	 @RequestMapping("/tofindPic")
	 public void picToJSP(@PathParam("id")int id,HttpServletRequest request,HttpServletResponse response){
		 PhotoModel photoModel=photoModelService.getPhotoById(new BigDecimal(id));
		 String photoName=photoModel.getName();
		 FileInputStream in;
		 response.setContentType("application/octet-stream;charset=UTF-8");
		 try {
			 //图片读取路径
			//in=new FileInputStream("E:\\picture\\"+photoName);
			in=new FileInputStream(picUrl+photoName);
			int i=in.available();
			byte[]data=new byte[i];
			in.read(data);
			in.close();
			
			//写图片
			OutputStream outputStream=new BufferedOutputStream(response.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	//从数据库中读取信息转换成json格式传递到前台
     @RequestMapping("/picToJsonReturn.do")
     @ResponseBody
     public String picToJsonReturn(HttpServletRequest request,Model model){
    	 System.out.println("aaaaa");
    	 JSONArray jsonArray=new JSONArray();
 		for(int i=10;i<31;i++){
 			PhotoModel p=photoModelService.getPhotoById(new BigDecimal(i));
 			jsonArray.add(p);
 		}
 		return jsonArray.toString();
     }
	 
}
