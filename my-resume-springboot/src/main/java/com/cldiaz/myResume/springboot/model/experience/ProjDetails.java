package com.cldiaz.myResume.springboot.model.experience;

import java.util.List;

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
public class ProjDetails {

	@JsonProperty("projDetail")
	private List<ProjDetail> projDetail;
	
	
}
