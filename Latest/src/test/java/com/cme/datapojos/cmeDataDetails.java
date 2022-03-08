package com.cme.datapojos;

import java.util.Date;

public class cmeDataDetails {
    private String fileName;
    private Date uploadDateTime;
    private Date searchedDateTime;
    private String contentGroup;
    private String contentType;
    private String documentType;
    private String entityName;
    private String documentDate;
    private String note;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public Date getSearchedDateTime() {
        return searchedDateTime;
    }

    public void setSearchedDateTime(Date searchedDateTime) {
        this.searchedDateTime = searchedDateTime;
    }

    public String getContentGroup() {
        return contentGroup;
    }

    public void setContentGroup(String contentGroup) {
        this.contentGroup = contentGroup;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MetaDataDetails{");
        sb.append("fileName='").append(fileName).append('\'');
        sb.append(", uploadDateTime=").append(uploadDateTime);
        sb.append(", searchedDateTime=").append(searchedDateTime);
        sb.append(", contentGroup='").append(contentGroup).append('\'');
        sb.append(", contentType='").append(contentType).append('\'');
        sb.append(", documentType='").append(documentType).append('\'');
        sb.append(", entityName='").append(entityName).append('\'');
        sb.append(", documentDate='").append(documentDate).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
