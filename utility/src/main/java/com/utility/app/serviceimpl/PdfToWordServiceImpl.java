package com.utility.app.serviceimpl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.utility.app.service.PdfToWordService;

@Service
public class PdfToWordServiceImpl implements PdfToWordService {

//	public final String UPLOAD_DIR = new ClassPathResource("Files_Upload/").getFile().getAbsolutePath();
//
//	public PdfToWordServiceImpl() throws IOException {
//
//	}
	
	File resourceDirectory = new File("src/main/resources/Files_Upload");
	
	public final String UPLOAD_DIR = resourceDirectory.getAbsolutePath();

	@Override
	public boolean uploadFile(MultipartFile file) {
		boolean uploaded = false;
		
		
		
		try {
			// Create a PdfDocument object
			PdfDocument doc = new PdfDocument();

			// Load a sample PDF document
			doc.loadFromBytes(file.getBytes());
			
			String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.'));
			
			System.out.println(fileName);
			System.out.println(UPLOAD_DIR + File.separator + file.getOriginalFilename());
			// convert file to docx
			doc.saveToFile(UPLOAD_DIR + File.separator + fileName + ".docx", FileFormat.DOCX);

			uploaded = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploaded;
	}

}
