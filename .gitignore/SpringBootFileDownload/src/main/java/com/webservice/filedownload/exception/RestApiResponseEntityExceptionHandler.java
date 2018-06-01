package com.webservice.filedownload.exception;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Custom Exceptions and Http Requests
 * @author sjoshi
 */

@RestController
@ControllerAdvice
public class RestApiResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException
	(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionRespone = new ExceptionResponse(ex.getMessage().toString(), new Date(), ex.getBindingResult().toString());
		return new ResponseEntity<>(exceptionRespone, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MultipartException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {

		HttpStatus status = getStaus(request);
		return new ResponseEntity<Object>(ex.getMessage(),status);
		
	}

	private HttpStatus getStaus(HttpServletRequest request) {
		
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if(statusCode == null)
			return HttpStatus.INTERNAL_SERVER_ERROR;
		
		return HttpStatus.valueOf(statusCode);
	}
	
	
	@ExceptionHandler(FileUploadSuccessException.class)
    ResponseEntity<?> handleFileUploadException( UploadFileResponse fileResponse,Exception ex,WebRequest request) {

		UploadFileResponse fileResponses = new UploadFileResponse
				(fileResponse.getFileName(),fileResponse.getFileDownloadUri(),fileResponse.getFileType(),fileResponse.getFileSize());
		return new ResponseEntity<Object>((Object) fileResponses,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(FileTypeNotFoundException.class)
	public final ResponseEntity<Object> handleFileNotFoundException(Exception ex, WebRequest request)
	{
		 ExceptionResponse exceptionRespone = new ExceptionResponse(ex.getMessage(), new Date(), request.getDescription(false));
		 
	   return new ResponseEntity<Object>(exceptionRespone,HttpStatus.NOT_FOUND);
	}
	
	
}
