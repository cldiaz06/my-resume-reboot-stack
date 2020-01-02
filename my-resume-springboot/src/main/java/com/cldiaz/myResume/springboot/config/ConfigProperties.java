package com.cldiaz.myResume.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:global.properties")
public class ConfigProperties {
	
	@Value("${document.template}")
	private String template;
	
	@Value("${document.format}")
	private String format;
	
	@Value("${document.name}")
	private String name;
	
	@Value("${document.filetype}")
	private String fileType;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	
	
	
	
	
	

}
