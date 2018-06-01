package com.webservice.filedownload.exception;

import java.io.IOException;

public class FileTypeNotFoundException extends Exception {
	
	public FileTypeNotFoundException(String message, IOException ex) {
		super(message);
		
	}

}
