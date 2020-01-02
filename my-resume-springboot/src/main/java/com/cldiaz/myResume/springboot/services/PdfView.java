package com.cldiaz.myResume.springboot.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.cldiaz.myResume.springboot.models.Resume;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

@Component("resumeView")
public class PdfView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, 
										   HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=Resume.pdf");
		
	    Resume res = (Resume) model.get("resume");
	    StandardResume stan = new StandardResume();
	    
	    Document doc = new Document(PageSize.A4);
	    PdfWriter writer = PdfWriter.getInstance(doc, response.getOutputStream());
		doc.open();
		stan.buildResumePdf(res, doc, writer);
		doc.close();

	}

}
