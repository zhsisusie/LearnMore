package com.chatRoom.model;

import java.math.BigDecimal;

public class Photo {
    private BigDecimal photoId;

    private String photoName;

    private byte[] photoData;

    public BigDecimal getPhotoId() {
        return photoId;
    }

    public void setPhotoId(BigDecimal photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}