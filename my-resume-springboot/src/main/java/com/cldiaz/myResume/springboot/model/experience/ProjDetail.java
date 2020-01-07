package com.cldiaz.myResume.springboot.model.experience;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProjDetail {
	
		private String name;
		
		@JsonInclude(Include.NON_NULL)
		private String url;
		private String description;
		private String duration;
		
		@JsonInclude(Include.NON_NULL)
		private String achievement;
		
		@JsonInclude(Include.NON_NULL)
		private List<String> tech;
}
