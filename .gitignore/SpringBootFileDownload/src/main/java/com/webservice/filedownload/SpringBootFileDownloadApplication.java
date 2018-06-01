package com.webservice.filedownload;
import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.webservice.filedownload.filestoragemodel.FileStorage;
import com.webservice.filedownload.service.FileStorageService;

/**
 * @author sjoshi
 */


@SpringBootApplication

@EnableConfigurationProperties({
    FileStorage.class
})

public class SpringBootFileDownloadApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootFileDownloadApplication.class, args);
	}
	
}
