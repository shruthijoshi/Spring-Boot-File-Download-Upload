package com.webservice.filedownload.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.webservice.filedownload.exception.FileTypeNotFoundException;
import com.webservice.filedownload.exception.MyFileNotFoundException;
import com.webservice.filedownload.service.FileStorageService;

/**
 * A Program to download a file using  REST API 
 * @author sjoshi
 */


@RestController
public class DownloadFileController {
	
	public static final Logger logger = LoggerFactory.getLogger(DownloadFileController.class);
	
	@Autowired
	private FileStorageService fileStorageService;
	
	//download single file//
		@GetMapping("/downloadFile/{fileName:.+}")
		public ResponseEntity<Resource> downloadfile(@PathVariable String fileName, HttpServletRequest request,HttpServletResponse response) throws FileTypeNotFoundException, MyFileNotFoundException
		{
			String contentType = " ";
			
			//Load file as resource
			Resource resource =  fileStorageService.loadFile(fileName);
			
			//Determine file content type
			
			try
				{
					contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
					response.setContentType(contentType);
					logger.debug("contentType:"+contentType);
					System.out.println("contentType:"+contentType);
				
				 } catch(IOException ex) {
					 logger.info("could not find file type");
					 throw new FileTypeNotFoundException("Could not find file type:"+ fileName, ex);
				 
				 }
			
			//default content type//
			if(contentType == null || contentType.equals(""))
			{
				contentType = "application/xls";
			}
			
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName =\"" + resource.getFilename())
					.body(resource);
		}

}
