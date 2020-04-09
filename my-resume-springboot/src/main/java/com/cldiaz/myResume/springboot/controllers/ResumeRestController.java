package com.cldiaz.myResume.springboot.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cldiaz.myResume.springboot.config.ConfigProperties;
import com.cldiaz.myResume.springboot.interfaces.GetResume;
import com.cldiaz.myResume.springboot.interfaces.PdfResumeGenerator;
import com.cldiaz.myResume.springboot.model.education.Education;
import com.cldiaz.myResume.springboot.model.experience.Experience;
import com.cldiaz.myResume.springboot.models.BasicInfo;
import com.cldiaz.myResume.springboot.models.Email;
import com.cldiaz.myResume.springboot.models.Resume;
import com.cldiaz.myResume.springboot.services.EmailNotifiyService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/rest")
public class ResumeRestController {

	private GetResume getResume;
	private Resume res;

	private PdfResumeGenerator pdfResumeGenerator;

	@Autowired
	private ConfigProperties config;
	
	@Autowired
	private EmailNotifiyService emailService;
	
	@Autowired
	public void setGetResume(ApplicationContext context) {
		if(config.getFileType().equals("xml")) {
			getResume = (GetResume) context.getBean("xmlGetResume");
		} else {
			getResume = (GetResume) context.getBean("jsonGetResume");
		}
	}
	
	@Autowired
	public void setPdfResumeGenerator(ApplicationContext context) {
		if(config.getTemplate().equals("revised")) {
			pdfResumeGenerator = (PdfResumeGenerator) context.getBean("revised");
		} else {
			pdfResumeGenerator = (PdfResumeGenerator) context.getBean("standard");
		}
	}
	
	@ModelAttribute("resume")
	public void setResume(Resume res) {
		this.res = getResume.getResume(false);
	}
	
	@GetMapping(value="/basicInfo")
	public BasicInfo getBasicInfo() {
		return res.getBasicInfo();
	}
	
	@GetMapping(value="/edu")
	public ArrayList<Education> getEdu() {
		return res.getEducation();
	}
	
	@GetMapping(value="/exp")
	public ArrayList<Experience> getExp() {
		return res.getExperience();
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value="/")
	public Resume getResumeDate() {
		return res;
	}
	
	@GetMapping(value ="/getResumePdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getResumePdf() throws IOException, DocumentException {
		
		ByteArrayInputStream bis = new ByteArrayInputStream(new byte[0]);
		
		bis = pdfResumeGenerator.buildResumePdfRest(res);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=resume.pdf");
			
		return ResponseEntity
					.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));

	}
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail (@Valid @RequestBody Email mail, Errors errors) throws MessagingException, IOException {
		if(errors.hasErrors()) {
			return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		
		return emailService.sendMail(mail);
	}
	
	
	
}
