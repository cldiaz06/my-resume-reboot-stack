package com.cldiaz.myResume.springboot.model.education;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class Education {

	private String startDate;
	private String endDate;
	private String schoolName;
	private String location;
	private String achievement;
	
	@JsonProperty("courses")
	private Courses courses;
	
//	public Education() {}
//	
//	public Education(String startDate, String endDate, String schoolName, String location, String achievement) {
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.schoolName = schoolName;
//		this.location = location;
//		this.achievement = achievement;
//	}
//
//	public String getStartDate() {
//		return startDate;
//	}
//
//	public String getEndDate() {
//		return endDate;
//	}
//
//	public String getSchoolName() {
//		return schoolName;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public String getAchievement() {
//		return achievement;
//	}
//
//	@Override
//	public String toString() {
//		return "Education [startDate=" + startDate + ", endDate=" + endDate + ", schoolName=" + schoolName
//				+ ", location=" + location + ", achievement=" + achievement + "]";
//	}
	
	
	
}
