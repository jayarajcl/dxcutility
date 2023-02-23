package com.utility.app.serviceimpl;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spire.pdf.PdfCompressionLevel;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.exporting.PdfImageInfo;
import com.spire.pdf.graphics.PdfBitmap;
import com.utility.app.service.CompressPdfService;

@Service
public class CompressPdfServiceImpl implements CompressPdfService {

//	public final String UPLOAD_DIR = new ClassPathResource("Files_Upload/").getFile().getAbsolutePath();
//
//	public CompressPdfServiceImpl() throws IOException {
//
//	}
	
	File resourceDirectory = new File("src/main/resources/Files_Upload");
	
	public final String UPLOAD_DIR = resourceDirectory.getAbsolutePath();
	

	@Override
	public boolean compress(MultipartFile file) {
		boolean compress = false;

		PdfDocument doc = new PdfDocument();

		try {
			doc.loadFromStream(file.getInputStream());

			// Disable incremental update
			doc.getFileInfo().setIncrementalUpdate(false);

			// Set the compression level to best
			doc.setCompressionLevel(PdfCompressionLevel.Best);

			for (int i = 0; i < doc.getPages().getCount(); i++) {
				// Get a specific page
				PdfPageBase page = doc.getPages().get(i);

				// Get image information collection from the page
				PdfImageInfo[] images = page.getImagesInfo();

				// Traverse the items in the collection
				if (images != null && images.length > 0)
					for (int j = 0; j < images.length; j++) {
						// Get a specific image
						PdfImageInfo image = images[j];
						PdfBitmap bp = new PdfBitmap(image.getImage());

						// Set the compression quality
						bp.setQuality(20);

						// Replace the original image with the compressed one
						page.replaceImage(j, bp);
					}
				System.out.println(UPLOAD_DIR + File.separator + file.getOriginalFilename());
				// Save the document to a PDF file
				doc.saveToFile(UPLOAD_DIR + File.separator + file.getOriginalFilename());
				doc.close();
			}

			compress = true;
			return compress;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return compress;
	}

}
