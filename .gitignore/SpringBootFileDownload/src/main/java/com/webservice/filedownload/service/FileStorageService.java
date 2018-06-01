package com.webservice.filedownload.service;

import java.io.IOException;
/**
 * A Service to store file in physical location and load specifed file from resource 
 * @author sjoshi
 */
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.webservice.filedownload.exception.FileStorageException;
import com.webservice.filedownload.exception.MyFileNotFoundException;
import com.webservice.filedownload.filestoragemodel.FileStorage;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	//File directory to load file//
	
	@Autowired
	public FileStorageService(FileStorage fileStorage)
	{
		this.fileStorageLocation = Paths.get(fileStorage.getUploadFile()).toAbsolutePath().normalize();
	
	try 
	   	{
			Files.createDirectories(this.fileStorageLocation);
	    } catch(Exception ex) 
	  	{
	    	throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	  	}
	
	}	
	
	//store file//
	public String storeFile(MultipartFile file) 
	{
		//normalize file name//
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		//check if file name contains invalid characters//
		try
		{
			if(fileName.contains(".."))
			{
				throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
			}
			
			//copy file to target location(Replace existing file with same name)//
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		 }catch(IOException ex)
		    {
				throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
			}	
	}
	
	//load file from resource//
	
	public Resource loadFile(String fileName) throws MyFileNotFoundException
	{
		try
		{
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists())
			{
				return resource;
			}
			else
			{
				throw new MyFileNotFoundException("File Not Found:"+ fileName);
			}
		}catch(MalformedURLException ex){
			throw new MyFileNotFoundException("File Not Found:"+ fileName, ex);
		}
	}

}
