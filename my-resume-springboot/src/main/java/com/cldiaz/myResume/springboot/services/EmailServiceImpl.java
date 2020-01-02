package com.cldiaz.myResume.springboot.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cldiaz.myResume.springboot.interfaces.SendEmailService;
import com.cldiaz.myResume.springboot.models.Email;

@Service("prod")
public class EmailServiceImpl implements SendEmailService {

	@Autowired
	private JavaMailSender sender;
	
	@Override
	public void sendEmail() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String sendEmail(Email email) throws Exception {
		// TODO Auto-generated method stub
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo(email.getEmail());
		helper.setSubject(email.getSubject());
		helper.setText(email.getBody());
		
		sender.send(message);
		
		return "sucess";
	}
	
	

}
