package com.chatRoom.dao;

import com.chatRoom.model.Photo;
import java.math.BigDecimal;

public interface PhotoDAO {
    int deleteByPrimaryKey(BigDecimal photoId);

    int insert(Photo record);

    int insertSelective(Photo record);

    Photo selectByPrimaryKey(BigDecimal photoId);

    int updateByPrimaryKeySelective(Photo record);

    int updateByPrimaryKeyWithBLOBs(Photo record);

    int updateByPrimaryKey(Photo record);
}