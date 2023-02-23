package com.utility.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface PdfToExcelService {
	public boolean excelconverter(MultipartFile file);
}
