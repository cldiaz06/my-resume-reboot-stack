package com.cldiaz.myResume.springboot.models;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Email {
	
	private String name;
	private String email;
	private String subject;
	private String body;
	
}
