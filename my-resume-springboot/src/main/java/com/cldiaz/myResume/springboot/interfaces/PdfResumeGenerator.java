package com.cldiaz.myResume.springboot.interfaces;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.cldiaz.myResume.springboot.model.education.Education;
import com.cldiaz.myResume.springboot.model.experience.Experience;
import com.cldiaz.myResume.springboot.model.skill.Skills;
import com.cldiaz.myResume.springboot.models.BasicInfo;
import com.cldiaz.myResume.springboot.models.Resume;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public interface PdfResumeGenerator {
	
	public ByteArrayInputStream buildResumePdfRest(Resume resume) throws DocumentException;
	
	public void addBasicInfo(Document document, BasicInfo basicInfo) throws DocumentException;
	
	public void addSkills(Document document, ArrayList<Skills> skills) throws DocumentException;
	
	public void addExperience(Document document, ArrayList<Experience> experience) throws DocumentException;
	
	public void addEducation(Document document, ArrayList<Education> education) throws DocumentException;
	
}
