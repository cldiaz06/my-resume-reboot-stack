package com.cldiaz.myResume.springboot.model.experience;

import java.util.ArrayList;

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
public class Experience {

	private String startDate;
	private String endDate;
	private String title;
	private String city;
	private String company;
	private String state;
	private String summary;

	//private ArrayList<String> projDetails;
	
	private ProjDetails projDetails;
	private ArrayList<String> appSupDetails;
	private ArrayList<String> genDutyDetails;
//	
//	public Experience() {}
//	
//	public Experience(String startDate, String endDate, String title, String city, String company, String state,
//			String summary, ArrayList<String> projDetails, ArrayList<String> appSupDetails,
//			ArrayList<String> genDutyDetails) {
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.title = title;
//		this.city = city;
//		this.company = company;
//		this.state = state;
//		this.summary = summary;
//		this.projDetails = projDetails;
//		this.appSupDetails = appSupDetails;
//		this.genDutyDetails = genDutyDetails;
//	}
//	public String getStartDate() {
//		return startDate;
//	}
//	public String getEndDate() {
//		return endDate;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public String getCity() {
//		return city;
//	}
//	public String getCompany() {
//		return company;
//	}
//	public String getState() {
//		return state;
//	}
//	public String getSummary() {
//		return summary;
//	}
//	
//	public ArrayList<String> getProjDetails() {
//		return projDetails;
//	}
//	public void setProjDetails(ArrayList<String> projDetails) {
//		this.projDetails = projDetails;
//	}
//	public ArrayList<String> getAppSupDetails() {
//		return appSupDetails;
//	}
//	public void setAppSupDetails(ArrayList<String> appSupDetails) {
//		this.appSupDetails = appSupDetails;
//	}
//	public ArrayList<String> getGenDutyDetails() {
//		return genDutyDetails;
//	}
//	public void setGenDutyDetails(ArrayList<String> genDutyDetails) {
//		this.genDutyDetails = genDutyDetails;
//	}
//
//	@Override
//	public String toString() {
//		return "Experience [startDate=" + startDate + ", endDate=" + endDate + ", title=" + title + ", city=" + city
//				+ ", company=" + company + ", state=" + state + ", summary=" + summary + ", projDetails=" + projDetails
//				+ ", appSupDetails=" + appSupDetails + ", genDutyDetails=" + genDutyDetails + "]";
//	}
	
	
	
}
