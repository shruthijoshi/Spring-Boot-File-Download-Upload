package com.webservice.filedownload.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.webservice.filedownload.exception.UploadFileResponse;
import com.webservice.filedownload.service.FileStorageService;

/**
 * A Program to upload a file/files using  REST API 
 * @author sjoshi
 */

@RestController
public class UploadFileController 
{

	public static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);
	
	@Autowired
	private FileStorageService fileStorageService; 
	
	
	// Upload single file //
	
	@PostMapping("/uploadFile")  
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile uploadFile)
	{
		String fileName = fileStorageService.storeFile(uploadFile);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				                 .path("/downloadFile/")
				                 .path(fileName).toUriString();
		logger.debug("Single file Uploaded");
		
		//if file is empty//
		
		if(uploadFile.isEmpty()) 
			{
			//return new ResponseEntity("please select a file!", HttpStatus.OK);
			return new UploadFileResponse("Please select a file",HttpStatus.OK);
			}
		
		/* try {
	            saveUploadedFiles(Arrays.asList(uploadFile));

	        } catch (IOException e) {
	            return new UploadFileResponse("File is not uploaded",HttpStatus.BAD_REQUEST);
	        }*/

	       /*return new ResponseEntity("Successfully uploaded - " +
	                uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);*/
		
		return new UploadFileResponse(fileName, fileDownloadUri, uploadFile.getContentType(), uploadFile.getSize());
		
	}
	
	//upload multiple files //
	
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files)
	{
		logger.debug("Multiple  files Uploaded");
		return Arrays.asList(files)
				.stream()
				.map(file ->uploadFile(file))
				.collect(Collectors.toList());
		
	}
	
	 //save file//
    /*private void saveUploadedFiles(List<MultipartFile> files) throws IOException 
    {

        for (MultipartFile file : files) 
        {
            if (file.isEmpty()) 
            {
                continue; 
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }*/
	
}
	
	