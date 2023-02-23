package com.utility.app.serviceimpl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.utility.app.service.PdfToExcelService;

@Service
public class PdfToExcelServiceImpl implements PdfToExcelService {

	File resourceDirectory = new File("src/main/resources/Files_Upload");
	
	public final String UPLOAD_DIR = resourceDirectory.getAbsolutePath();
	
	@Override
	public boolean excelconverter(MultipartFile file) {
		
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
			doc.saveToFile(UPLOAD_DIR + File.separator + fileName + ".xlsx", FileFormat.XLSX);

			uploaded = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploaded;
	}

}
