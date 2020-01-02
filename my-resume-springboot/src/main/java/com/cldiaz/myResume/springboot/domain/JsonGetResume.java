package com.cldiaz.myResume.springboot.domain;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.cldiaz.myResume.springboot.interfaces.GetResume;
import com.cldiaz.myResume.springboot.model.education.Education;
import com.cldiaz.myResume.springboot.model.experience.Experience;
import com.cldiaz.myResume.springboot.model.skill.Skill_Years;
import com.cldiaz.myResume.springboot.model.skill.Skills;
import com.cldiaz.myResume.springboot.models.BasicInfo;
import com.cldiaz.myResume.springboot.models.Interests;
import com.cldiaz.myResume.springboot.models.Resume;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("jsonGetResume")
public class JsonGetResume implements GetResume {
	
	@Override
	public Resume getResume(Boolean debug) {
		// TODO Auto-generated method stub
		try {
		
			//ClassLoader classLoader = getClass().getClassLoader();
//			File basicJson = new File(classLoader.getResource("json/basicInfo.json").getFile());
//			File skillJson = new File(classLoader.getResource("json/skills.json").getFile());
//			File educationJson = new File(classLoader.getResource("json/education.json").getFile());
//			File experienceJson = new File(classLoader.getResource("json/experience.json").getFile());
//			File yearsJson = new File(classLoader.getResource("json/skill_years.json").getFile());
//			File interestJson = new File(classLoader.getResource("json/interests.json").getFile());
			
			File basicJson = ResourceUtils.getFile("classpath:json/basicInfo.json");
			File skillJson = ResourceUtils.getFile("classpath:json/skills.json");
			File educationJson = ResourceUtils.getFile("classpath:json/education.json");
			File experienceJson = ResourceUtils.getFile("classpath:json/experience.json");
			File yearsJson = ResourceUtils.getFile("classpath:json/skill_years.json");
			File interestJson = ResourceUtils.getFile("classpath:json/interests.json");
			
			ObjectMapper mapper = new ObjectMapper();
			
			BasicInfo basic = mapper.readValue(basicJson, BasicInfo.class);
		
			List<Skills> skill = mapper.readValue(skillJson, 
					mapper.getTypeFactory().constructCollectionType(List.class, Skills.class));
			
			List<Experience> exp = mapper.readValue(experienceJson, 
					mapper.getTypeFactory().constructCollectionType(List.class, Experience.class));
			
			List<Education>edu = mapper.readValue(educationJson, 
					mapper.getTypeFactory().constructCollectionType(List.class, Education.class));
			
			List<Skill_Years> years = mapper.readValue(yearsJson, 
					mapper.getTypeFactory().constructCollectionType(List.class, Skill_Years.class));
			
			List<Interests> inter = mapper.readValue(interestJson, 
					mapper.getTypeFactory().constructCollectionType(List.class, Interests.class));
			
			
			if (debug) {
				System.out.println("***Basicinfo*****");
				System.out.println(basic.toString());
				
				System.out.println("***Skills*****");
				for(Skills temp :skill) {
					System.out.println(temp.toString());
				}
				
				System.out.println("***Experience*****");
				for(Experience temp :exp) {
					System.out.println(temp.toString());
				}
				
				System.out.println("***Education*****");
				for(Education temp :edu) {
					System.out.println(temp.toString());
				}
				
				System.out.println("***Skill Years*****");
				for(Skill_Years temp :years) {
					System.out.println(temp.toString());
				}
				
				System.out.println("***interests*****");
				for(Interests temp :inter) {
					System.out.println(temp.toString());
				}
			}
			
			return new Resume(basic, skill, exp, edu, years, inter);
		
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		} 
		
	}

}
