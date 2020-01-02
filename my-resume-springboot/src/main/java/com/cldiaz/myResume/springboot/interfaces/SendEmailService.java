package com.cldiaz.myResume.springboot.interfaces;

import com.cldiaz.myResume.springboot.models.Email;

public interface SendEmailService {
	
	public void sendEmail() throws Exception;
	
	public String sendEmail(Email email) throws Exception;
}
