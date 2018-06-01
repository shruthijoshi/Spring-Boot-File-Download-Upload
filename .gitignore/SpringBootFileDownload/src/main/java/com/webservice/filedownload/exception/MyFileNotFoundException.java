package com.webservice.filedownload.exception;

import java.net.MalformedURLException;
/**
 * 
 * @author sjoshi
 */

public class MyFileNotFoundException extends Exception {

	public MyFileNotFoundException(String message) {
		super(message);
		
	}

	public MyFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	
}
