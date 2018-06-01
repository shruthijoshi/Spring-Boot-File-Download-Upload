package com.webservice.filedownload.filestoragemodel;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * Model POJO
 * @author sjoshi
 */

@ConfigurationProperties(prefix = "file")
public class FileStorage {
  
	private String uploadFile;
	
	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	
	
	
}
