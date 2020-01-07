package com.cldiaz.myResume.springboot.model.education;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Course {

	private String name;
	private String url;
	private String duration;
	private String description;
	private boolean completed;
	
}
