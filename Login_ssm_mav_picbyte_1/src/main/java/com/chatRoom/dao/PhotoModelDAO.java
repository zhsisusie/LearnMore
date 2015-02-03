package com.chatRoom.dao;

import com.chatRoom.model.PhotoModel;
import java.math.BigDecimal;

public interface PhotoModelDAO {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(PhotoModel record);

    int insertSelective(PhotoModel record);

    PhotoModel selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(PhotoModel record);

    int updateByPrimaryKey(PhotoModel record);
}