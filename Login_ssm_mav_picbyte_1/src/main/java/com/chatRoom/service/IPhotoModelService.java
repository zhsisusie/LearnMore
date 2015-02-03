package com.chatRoom.service;

import java.math.BigDecimal;

import com.chatRoom.model.Photo;
import com.chatRoom.model.PhotoModel;

public interface IPhotoModelService {
	int insert(String pName);
	public PhotoModel getPhotoById(BigDecimal photoId);
}
