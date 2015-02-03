package com.chatRoom.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chatRoom.dao.PhotoDAO;
import com.chatRoom.model.Photo;
import com.chatRoom.service.IPhotoService;
@Service("photoSerivce")
public class PhotoServiceImpl implements IPhotoService{

	@Resource
	PhotoDAO photoDAO;
	public int insert(String pName,byte[]photoData) {
		Photo p=new Photo();
		p.setPhotoName(pName);
		p.setPhotoData(photoData);
		return photoDAO.insert(p);
	}
	public Photo getPhotoById(BigDecimal photoId) {
		return photoDAO.selectByPrimaryKey(photoId);
	}
}
