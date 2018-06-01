package com.webservice.filedownload.exception;

import java.util.Date;
/**
 * Exception response format POJO
 * @author sjoshi
 */


public class ExceptionResponse {
	
	private String message;
	private Date date;
	private String timeStamp;
	
	public ExceptionResponse(String message, Date date, String timeStamp) {
		super();
		this.message = message;
		this.date = date;
		this.timeStamp = timeStamp;
	}
	
	

	public String getMessage() {
		return message;
	}
	public Date getDate() {
		return date;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	
	

}
