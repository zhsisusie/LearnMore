package com.chatRoom.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatRoom.model.Photo;
import com.chatRoom.service.IPhotoService;
import com.chatRoom.service.IUserService;


/**
 * 
 * @author zhsi
 *从数据库库中读取图片显示JSP页面上用到的路径为：
 *@RequestMapping(value="/toImageDisplay")
 * @RequestMapping(value="/toLookImage",method = RequestMethod.GET)
 * @RequestMapping("/picToJsonReturn.do")
 * 
 * 通过将图片信息以而二进制存到数据库中读出来的方式实现
 */
@Controller
@RequestMapping("/user")
public class UserController extends HttpServlet{
     private static final long serialVersionUID=95163763702302820L;
     @Resource
	 private IUserService userService;
     @Resource
 	private IPhotoService photoService;
     //获取session
     HttpSession session;
     ServletContext application;
     
     /**
      * 控制跳转到登录界面
      */
     //跳转到图片显示的页面
     @RequestMapping(value="/toImageDisplay")
     public String toImageDisplay(){
    	 return "waterfullPic";
     }
     
     //将图片输出到页面进行显示
     @RequestMapping(value="/toLookImage",method = RequestMethod.GET)
     public void lookImage(@PathParam("id")int id,HttpServletRequest request,HttpServletResponse response,Model model){
    	 HttpSession seesion = request.getSession();
    	Photo photo=photoService.getPhotoById(new BigDecimal(id));
    	byte[] data=photo.getPhotoData();
    	response.setContentType("img/jpeg");
    	response.setCharacterEncoding("utf-8");
    	try {
    		System.out.println("zhangsi");
			OutputStream outputStream=response.getOutputStream();
			InputStream in=new ByteArrayInputStream(data);
			System.out.println("sssssss");
			int len=0;
			byte[]buf=new byte[1024];
			while((len=in.read(buf,0,1024))!=-1){
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
		} catch (IOException e) {
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
 		for(int i=13;i<35;i++){
 			Photo p=photoService.getPhotoById(new BigDecimal(i));
 			jsonArray.add(p);
 		}
 		return jsonArray.toString();
     }
     @RequestMapping(value="/toLogin.html")
     public String toLogin(HttpServletRequest request,Model model){
    	 //System.out.println("张思");
    	 return "login";
     }
     /**
      * 执行登录操作
      */
     @RequestMapping(value="/login.html",method=RequestMethod.GET)
     public void login(HttpServletRequest request,Model model,PrintWriter out){
    	 /**login.js中有name和pawword参数信息，发送到http请求中
    	  * data:{"name":name,"password":password},
    	  * 下面的name和password是Request parameters are extra information sent with the request
    	  */
    	 String name=request.getParameter("name");
    	 String password=request.getParameter("password");
    	 String s=userService.login(name, password);
    	 session=request.getSession();
    	 if(s=="success"){
    		 session.setAttribute("name", name);//保存当前登录的用户名
    		 /**
        	  * Returns the ServletContext to which this session belongs.
        	  * The ServletContext object for the web application
        	  */
    		 application=request.getSession().getServletContext();
    		 if(application.getAttribute("onLine")==null){
    			 application.setAttribute("onLine", "");
    		 }
    		 String onLine=application.getAttribute("onLine").toString();
    		 onLine+=name+"<br/>";
    		 application.setAttribute("onLine", onLine);
    	 }
    	 //写入返回结果
    	 out.write(s);//这里便是把数据写到服务器端，传递到前台
     }
     /**
      * 控制跳转到聊天主界面
      */
     @RequestMapping("/toChatMain.html")
     public String toChatMain(HttpServletRequest request,Model model){
    	 return "chatMain";
     }
     
     //登录之后控制跳转到瀑布墙的界面
     @RequestMapping("/toWaterfullPic.html")
     public String toWaterfullPic(HttpServletRequest request,Model model){
    	 System.out.println("zhangsisi");
    	 return "waterfullPic";
     }
     
     
     /**
      * 前端发送聊天内容
      */
     @RequestMapping("/sentContent.html")
     public void sentContent(HttpServletRequest request,Model model,PrintWriter out){
    	 //存储信息的全局变量
    	 application=request.getSession().getServletContext();
    	 session=request.getSession();
    	 if(application.getAttribute("message")==null){
    		 application.setAttribute("message", "");
    	 }
    	 //获取application中存储的聊天内容
    	 String sourceMessage=application.getAttribute("message").toString();
    	 //获取前端发送的聊天内容
    	 String content=request.getParameter("content");
    	 content=content.replace("<","<img src='/Login_ssm_mav/static/pic");
    	 content=content.replace(":>", ".jpg'/>");
    	 //获取session中的登录者
    	 String name=session.getAttribute("name").toString();
    	 sourceMessage+=this.getTime()+"<font color='blue'><strong>"+name+"</strong></font>:"+content+"</br>";
    	 application.setAttribute("message", sourceMessage);
    	 //写入返回结果
    	 out.write("success");
     }
     
     /**
      * 前端定时获取聊天信息
      */
     @RequestMapping("/getMessageList.html")
     public void getMessageList(HttpServletRequest request,Model model,PrintWriter out){
    	 application=request.getSession().getServletContext();
    	 if(application.getAttribute("message")==null){
    		 application.setAttribute("message", "");
    	 }
    	 //获取application中存储的聊天内容
    	 String sourceMessage=application.getAttribute("message").toString();
    	 String str=null;
    	 try {
			str=new String(sourceMessage.getBytes(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	 out.write(str);
     }
     
     /**
      * 前端定时获取在线人员
      */
     @RequestMapping("/getOnlineList.html")
     public void getOnlineList(HttpServletRequest request,Model model,PrintWriter out){
    	 application=request.getSession().getServletContext();
    	 if(application.getAttribute("onLine")==null){
    		 application.setAttribute("onLine", "");
    	 }
    	 //获取application中存储的在线人员
    	 String sourceOnline=application.getAttribute("onLine").toString();
    	 System.out.println("ssss"+sourceOnline);
    	 out.write(sourceOnline);
     }
     /**
      * 自定义日期格式
      */
     private String getTime(){
    	 Date date=new Date();
    	 SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	 return df.format(date);
     }
}

