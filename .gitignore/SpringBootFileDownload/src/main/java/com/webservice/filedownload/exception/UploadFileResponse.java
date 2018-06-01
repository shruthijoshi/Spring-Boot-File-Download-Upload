package com.webservice.filedownload.exception;

import org.springframework.http.HttpStatus;
/**
 * File response details POJO 
 * @author sjoshi
 */

public class UploadFileResponse{
	
   private String fileName;
   private String fileDownloadUri;
   private String fileType;
   private long fileSize;
 
   

public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long fileSize) {
	super();
	this.fileName = fileName;
	this.fileDownloadUri = fileDownloadUri;
	this.fileType = fileType;
	this.fileSize = fileSize;
}

public UploadFileResponse(String message, HttpStatus status) {
super();
}

public String getFileName() {
	return fileName;
}


public void setFileName(String fileName) {
	this.fileName = fileName;
}


public String getFileDownloadUri() {
	return fileDownloadUri;
}


public void setFileDownloadUri(String fileDownloadUri) {
	this.fileDownloadUri = fileDownloadUri;
}


public String getFileType() {
	return fileType;
}


public void setFileType(String fileType) {
	this.fileType = fileType;
}


public long getFileSize() {
	return fileSize;
}


public void setFileSize(long fileSize) {
	this.fileSize = fileSize;
}
   
   
   
	
}
