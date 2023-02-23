package com.utility.app.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.utility.app.service.CompressPdfService;
import com.utility.app.service.PdfToExcelService;
import com.utility.app.service.PdfToWordService;

@CrossOrigin("*")
@RestController
public class Controller {
	
	@Autowired
	private PdfToWordService pdfToWordService;
	
	@Autowired
	private CompressPdfService compressPdfService;
	
	@Autowired
	private PdfToExcelService pdfToExcelService;
	
	@PostMapping("/pdftowordconverter")
	public ResponseEntity<?> uploadPDF(@RequestParam("file") MultipartFile file)
	{
		System.out.println("recieved");
		try {
			if(file.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Must contain file");
			}
			
			if(file.getContentType().equals("application/pdf"))
			{
				boolean f = pdfToWordService.uploadFile(file);
				
				if(f)
				{
					
					File resourceDirectory = new File("src/main/resources/Files_Upload");
					
					final String UPLOAD_DIR = resourceDirectory.getAbsolutePath();
					
					String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'));
					
					Resource resource1 = new ClassPathResource("Files_Upload/"+fileName+".docx");
					
					System.out.println(resource1.getFilename());
					System.out.println(resource1.getDescription());
					
					File wordfile = new File(UPLOAD_DIR + File.separator + fileName + ".docx");
					
					HttpHeaders header = new HttpHeaders();
			        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fileName.docx");
			        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			        header.add("Pragma", "no-cache");
			        header.add("Expires", "0");
			        
			        Path path = Paths.get(wordfile.getAbsolutePath());
			        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			        
			        System.out.println("Path: "+UPLOAD_DIR + File.separator + fileName + ".docx");
			        System.out.println("Absolute: "+path);
			        System.out.println(resource);

			        return ResponseEntity.ok()
			                .headers(header)
			                .contentLength(wordfile.length())
			                .contentType(MediaType.parseMediaType("application/octet-stream"))
			                .body(resource);
					
				}
			}
			else
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only pdf files allowed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
	}
	
	@PostMapping("/compressPdf")
	public ResponseEntity<String> compressPdf(@RequestParam("file") MultipartFile file)
	{
		System.out.println("Content-type: "+file.getContentType());
		System.out.println("Filename: "+file.getOriginalFilename());
		System.out.println("Resource: "+file.getResource());
		
		try {
			if(file.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No file found to compress.");
			}
			
			if(file.getContentType().equals("application/pdf"))
			{
				boolean f = compressPdfService.compress(file);
				
				if(f)
				{
					return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/Files_Upload/").path(file.getOriginalFilename()).toUriString());
				}
			}
			else
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only pdf files allowed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
	}
	
	@PostMapping("/pdftoexcelconverter")
	public ResponseEntity<?> pdfToExcel(@RequestParam("file") MultipartFile file)
	{
		System.out.println("recieved");
		try {
			if(file.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Must contain file");
			}
			
			System.out.println(file.getContentType());
			
			if(file.getContentType().equals("application/pdf"))
			{
				boolean f = pdfToExcelService.excelconverter(file);
				
				if(f)
				{
					
					File resourceDirectory = new File("src/main/resources/Files_Upload");
					
					final String UPLOAD_DIR = resourceDirectory.getAbsolutePath();
					
					String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'));
					
					Resource resource1 = new ClassPathResource("Files_Upload/"+fileName+".xlsx");
					
					System.out.println(resource1.getFilename());
					System.out.println(resource1.getDescription());
					
					File excelfile = new File(UPLOAD_DIR + File.separator + fileName + ".xlsx");
					
					HttpHeaders header = new HttpHeaders();
			        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fileName.docx");
			        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			        header.add("Pragma", "no-cache");
			        header.add("Expires", "0");
			        
			        Path path = Paths.get(excelfile.getAbsolutePath());
			        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
			        
			        System.out.println("Path: "+UPLOAD_DIR + File.separator + fileName + ".xlsx");
			        System.out.println("Absolute: "+path);
			        System.out.println(resource);

			        return ResponseEntity.ok()
			                .headers(header)
			                .contentLength(excelfile.length())
			                .contentType(MediaType.parseMediaType("application/octet-stream"))
			                .body(resource);
					
				}
			}
			else
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only pdf files allowed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
	}
	
	@PostMapping("/fileCompare")
	public ResponseEntity<String> fileCompareUpload(@RequestParam("file") MultipartFile[] files)
	{
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
	}


}
