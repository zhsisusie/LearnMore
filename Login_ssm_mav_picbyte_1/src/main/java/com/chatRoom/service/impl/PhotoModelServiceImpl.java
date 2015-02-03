package com.chatRoom.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chatRoom.dao.PhotoModelDAO;
import com.chatRoom.model.PhotoModel;
import com.chatRoom.service.IPhotoModelService;

@Service("photoModelService")
public class PhotoModelServiceImpl implements IPhotoModelService{

	@Resource
	PhotoModelDAO photoModelDAO;
	public int insert(String pName) {
		PhotoModel pm=new PhotoModel();
		pm.setName(pName);
		return photoModelDAO.insert(pm);
	}

	public PhotoModel getPhotoById(BigDecimal photoId) {
		return photoModelDAO.selectByPrimaryKey(photoId);
	}
}
