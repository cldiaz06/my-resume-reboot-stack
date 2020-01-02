package com.cldiaz.myResume.springboot.models;

import java.util.ArrayList;
import java.util.List;

import com.cldiaz.myResume.springboot.model.education.Education;
import com.cldiaz.myResume.springboot.model.experience.Experience;
import com.cldiaz.myResume.springboot.model.skill.Skill_Years;
import com.cldiaz.myResume.springboot.model.skill.Skills;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Resume {

	private BasicInfo basicInfo = new BasicInfo();
	private ArrayList<Skills> skills = new ArrayList<Skills>();
	private ArrayList<Experience> experience = new ArrayList<Experience>();
	private ArrayList<Education> education = new ArrayList<Education>();
	private ArrayList<Skill_Years> years = new ArrayList<Skill_Years>();
	private ArrayList<Interests> inter = new ArrayList<Interests>();
	
	public Resume() {}

	public Resume(BasicInfo basicInfo, ArrayList<Skills> skills, ArrayList<Experience> experience,
			ArrayList<Education> education, ArrayList<Skill_Years> years, ArrayList<Interests> inter) {
		super();
		this.basicInfo = basicInfo;
		this.skills = skills;
		this.experience = experience;
		this.education = education;
		this.years = years;
		this.inter = inter;
	}

	public Resume(BasicInfo basic, List<Skills> skill, List<Experience> exp, 
			List<Education> edu, List<Skill_Years> years,List<Interests> inter) {
		this.basicInfo = basic;
		this.skills = (ArrayList<Skills>) skill;
		this.experience = (ArrayList<Experience>) exp;
		this.education = (ArrayList<Education>) edu;
		this.years = (ArrayList<Skill_Years>) years;
		this.inter = (ArrayList<Interests>) inter;
	}
//
//	public BasicInfo getBasicInfo() {
//		return basicInfo;
//	}
//
//	public void setBasicInfo(BasicInfo basicInfo) {
//		this.basicInfo = basicInfo;
//	}
//
//	public ArrayList<Skills> getSkills() {
//		return skills;
//	}
//
//	public void setSkills(ArrayList<Skills> skills) {
//		this.skills = skills;
//	}
//
//	public ArrayList<Experience> getExperience() {
//		return experience;
//	}
//
//	public void setExperience(ArrayList<Experience> experience) {
//		this.experience = experience;
//	}
//
//	public ArrayList<Education> getEducation() {
//		return education;
//	}
//
//	public void setEducation(ArrayList<Education> education) {
//		this.education = education;
//	}
//
//	public ArrayList<Skill_Years> getYears() {
//		return years;
//	}
//
//	public void setYears(ArrayList<Skill_Years> years) {
//		this.years = years;
//	}
//
//	public ArrayList<Interests> getInter() {
//		return inter;
//	}
//
//	public void setInter(ArrayList<Interests> inter) {
//		this.inter = inter;
//	}
//
//	@Override
//	public String toString() {
//		return "Resume [basicInfo=" + basicInfo + ", skills=" + skills + ", experience=" + experience + ", education="
//				+ education + ", years=" + years + ", inter=" + inter + "]";
//	}

	
	
	
	
}
