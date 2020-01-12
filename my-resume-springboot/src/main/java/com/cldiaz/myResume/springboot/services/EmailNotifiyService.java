package com.cldiaz.myResume.springboot.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.cldiaz.myResume.springboot.models.Email;

@Service("prod")
public class EmailNotifiyService {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public ResponseEntity<?> sendMail(Email email) throws MessagingException, IOException{
		send(email);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	} 
	
	public void send(Email mail) throws MessagingException, IOException{
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		//helper.addAttachment("logo.png", new ClassPathResource("myLogo.png"));
		
		Context context = new Context();
		//context.setVariables(model);
		context.setVariable("body", mail.getBody());
		context.setVariable("name", mail.getName());
		System.out.println("calling to get template");
		String html = templateEngine.process("email-template", context);		
		
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		helper.setText(html, true);
		
		emailSender.send(message);
		//return "Email Send Successfully";
	}
}
