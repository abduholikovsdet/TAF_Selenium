package com.cme.datapojos;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class cmeData {

    private List<File> listOfUploadedFiles;
    private Map<String, String> metaData;
    private Map<String, cmeDataDetails> metaDataMap;
    private String searchUrl;
    private String uploadUrl;
    private Date foundDateAndTime;

    public cmeData(){
        metaDataMap = new LinkedHashMap<>();
    }

    public List<File> getListOfUploadedFiles() {
        return listOfUploadedFiles;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setListOfUploadedFiles(List<File> listOfUploadedFiles) {
        this.listOfUploadedFiles = listOfUploadedFiles;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public Map<String, cmeDataDetails> getMetaDataMap() {
        return metaDataMap;
    }

    public void setMetaDataMap(Map<String, cmeDataDetails> metaDataMap) {
        this.metaDataMap = metaDataMap;
    }

    public Date getFoundDateAndTime() {
        return foundDateAndTime;
    }

    public void setFoundDateAndTime(Date foundDateAndTime) {
        this.foundDateAndTime = foundDateAndTime;
    }
}
