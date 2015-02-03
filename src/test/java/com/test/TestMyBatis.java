package com.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chatRoom.model.Photo;
import com.chatRoom.model.User;
import com.chatRoom.service.IPhotoService;
import com.chatRoom.service.IUserService;
import com.chatRoom.util.CreTableUtil;
import com.chatRoom.util.ImgHelper;
import com.chatRoom.util.JsonUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring.xml"})
public class TestMyBatis{
	//这是对应配置文件中的变量，给变量注入值
	@Value("${picUrl}")
	private String picUrl;
	
	private JdbcTemplate jdbcTemplate;
	private static Logger logger=Logger.getLogger(TestMyBatis.class);
	@Resource
	private IPhotoService photoService;
	@Autowired
	public void setPhotoService(IPhotoService photoService) {
		this.photoService = photoService;
	}
	@Resource
	private IUserService userService;
	@Resource
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@org.junit.Test
	public void test1() throws IllegalArgumentException, IllegalAccessException{
		
//		PropertyConfigurator
//		.configure(".//WebContent/WEB-INF/cfg/log4j.properties");
		User user=userService.getUserById(new BigDecimal(105));
		//logger.info(JSON.toJSONString(user));
		
		Photo photo=photoService.getPhotoById(new BigDecimal(4));
		
		//根据数据库中存储的图片的二进制信息流还原成图片
		byte[]pp=photo.getPhotoData();
		/*InputStream in=new ByteArrayInputStream(pp);
		File file=new File("E:\\picture","cw.jpg");
		try {
			FileOutputStream fos=new FileOutputStream(file);
			byte[]b=new byte[1024];
			int nRead=0;
			while((nRead=in.read(b))!=-1){
				fos.write(b, 0, nRead);
			}
			fos.flush();
			fos.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(photo.getPhotoData().toString());*/
		
		
		
		/*LinkedList<Photo>list=new LinkedList<Photo>();
		list.add(photo);
		list.add(photo);
		System.out.println("\n张思\n");
		System.out.println(JsonUtil.toJson(user));
		System.out.println(JsonUtil.toJson(photo));
		System.out.println(JsonUtil.listToJson(list));*/
		
		/**
		 * 创建id自增的数据库的执行语句块
		 */
		/*jdbcTemplate=new JdbcTemplate(dataSource);
		CreTableUtil create=new CreTableUtil();
		create.create("photo_tbl", jdbcTemplate);
		create.createSequence("zhii", jdbcTemplate);
		create.idIncrement("photo_tbl","zhii", jdbcTemplate);
		create.create_trigger("photo_tbl", "zhii","trss", jdbcTemplate);*/
		
		
	}
}
