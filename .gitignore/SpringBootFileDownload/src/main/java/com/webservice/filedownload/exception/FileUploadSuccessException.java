package com.webservice.filedownload.exception;

/**
 * 
 * @author sjoshi
 */
public class FileUploadSuccessException  extends RuntimeException {
	
	public FileUploadSuccessException(String fileName, String fileDownloadUri, String fileType, long fileSize) {
		super();
		
	}

}
