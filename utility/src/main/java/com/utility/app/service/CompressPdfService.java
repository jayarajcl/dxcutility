package com.utility.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface CompressPdfService {

	public boolean compress(MultipartFile file);
}
