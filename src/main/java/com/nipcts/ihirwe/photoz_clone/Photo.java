package com.nipcts.ihirwe.photoz_clone;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;

public class Photo {
    private String id;


    @NotEmpty
    private String fileName;
    private byte[] data;
    private String contentType;
    public Photo() {}

    public Photo(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }
    @JsonIgnore
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}

