package com.utility.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface PdfToWordService {

	public boolean uploadFile(MultipartFile file);

}
