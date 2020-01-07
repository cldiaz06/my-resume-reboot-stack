package com.cldiaz.myResume.springboot.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cldiaz.myResume.springboot.interfaces.PdfResumeGenerator;
import com.cldiaz.myResume.springboot.model.education.Course;
import com.cldiaz.myResume.springboot.model.education.Education;
import com.cldiaz.myResume.springboot.model.experience.Experience;
import com.cldiaz.myResume.springboot.model.experience.ProjDetail;
import com.cldiaz.myResume.springboot.model.skill.Skills;
import com.cldiaz.myResume.springboot.models.BasicInfo;
import com.cldiaz.myResume.springboot.models.Resume;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

@Service("standard")
public class StandardResume implements PdfResumeGenerator {
	
	private final static Font Normal_Font = new Font(Font.FontFamily.TIMES_ROMAN,11, Font.NORMAL);
	private final static Font Name_Header = new Font(Font.FontFamily.TIMES_ROMAN,28, Font.ITALIC);
	private final static Font Title_Header = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.ITALIC);
	private final static Font Ref = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.ITALIC);
	private final static Font Normal_Italic = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.ITALIC);
	private final static Font url = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.ITALIC | Font.UNDERLINE);
	private final static Font urlList = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLDITALIC | Font.UNDERLINE);
	
	public StandardResume() {}
	
	public StandardResume (Resume resume, Document doc, PdfWriter writer) throws DocumentException {
		buildResumePdf(resume, doc, writer);
	};
	
	public ByteArrayInputStream buildResumePdfRest(Resume resume) throws DocumentException{
		Document doc = new Document(PageSize.A4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		doc.open();
		buildResumePdf(resume, doc, writer);
		doc.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}

	
	public void buildResumePdf(Resume resume, Document document, PdfWriter writer) throws DocumentException {
		
		Date today = new Date();
		
		addBasicInfo(document, resume.getBasicInfo());
		//myPdfCreator.addEmptyLine(document, 40);
		drawRedLine(document, "Skills", writer);
		addSkills(document, resume.getSkills());
		
		drawRedLine(document, "Experience", writer);
		
		addExperience(document, resume.getExperience());
		
		drawRedLine(document, "Education", writer);
		
		addEducation(document, resume.getEducation());
		
		addReference(document, writer);

		System.out.println("PDF file Created:" + today);
	}
	
	@Override
	public void addBasicInfo(Document document, BasicInfo basicInfo) throws DocumentException {
		PdfPTable mainHeader = new PdfPTable(2);
		mainHeader.setWidthPercentage(100.0f);
		
		PdfPCell leftTable = getCell(null, null);
		PdfPTable leftHeader = new PdfPTable(1);
		leftHeader.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell name = new PdfPCell(getCell(basicInfo.getFirstName() + " " + basicInfo.getLastName(), Name_Header));
		PdfPCell title1 = new PdfPCell(getCell(basicInfo.getTitle(), Title_Header));
		
		leftHeader.addCell(name);
		leftHeader.addCell(title1);
		
		leftTable.addElement(leftHeader);
		mainHeader.addCell(leftTable);
		//document.add(leftHeader);
		
		PdfPCell rightTable = getCell(null, null);
		PdfPTable rightHeader = new PdfPTable(1);
		rightHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
		PdfPCell city = new PdfPCell(getCell(basicInfo.getCity() + ", " + basicInfo.getState(), Normal_Font));
		PdfPCell postal = new PdfPCell(getCell(basicInfo.getPostalcode(), Normal_Font));
		PdfPCell phone = new PdfPCell(getCell(basicInfo.getPhone(), Normal_Font));
		PdfPCell email = new PdfPCell(getCell(basicInfo.getEmail(), Normal_Font));
		PdfPCell git_url = new PdfPCell(getCell(basicInfo.getGitUrl(), url, true));

		rightHeader.addCell(city);
		rightHeader.addCell(postal);
		rightHeader.addCell(phone);
		rightHeader.addCell(email);
		rightHeader.addCell(git_url);
		
		rightTable.addElement(rightHeader);
		mainHeader.addCell(rightTable);
		mainHeader.setSpacingAfter(30f);
		
		document.add(mainHeader);

	}

	@Override
	public void addSkills(Document document, ArrayList<Skills> skills) throws DocumentException {
		   addEmptyLine(document, 5);
		   
		   PdfPTable skillSet = new PdfPTable(2);
		   skillSet.setTotalWidth(new float[] {33,100});
		   skillSet.setHorizontalAlignment(Element.ALIGN_LEFT);
		   
		   for(Skills temp: skills) {
			   PdfPCell skillHeader = getHeader(temp.getHeader(),Normal_Font);
			   PdfPCell skillDetail = getDetail(temp.getDetail(), Normal_Font);
			   addEmptyLine(document,1);
			   skillSet.addCell(skillHeader);
			   skillSet.addCell(skillDetail);
			   
		   }
		   skillSet.setSpacingAfter(30f);
		   document.add(skillSet);

	}

	@Override
	public void addExperience(Document document, ArrayList<Experience> experience) throws DocumentException {
		addEmptyLine(document, 5);
		   
		PdfPTable experienceSet = new PdfPTable(2);
		experienceSet.setTotalWidth(new float[] {33,100});
		experienceSet.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		for(Experience temp: experience) {
			   			
			   PdfPCell skillHeader = getHeader(temp.getStartDate() + " - " + temp.getEndDate(),Normal_Font);
			   String expHeader = new String();
			   expHeader = temp.getTitle() + ',' + temp.getCompany() + "," + temp.getCity() + "," + temp.getState();
			   
			   PdfPCell skillDetail = getDetail(expHeader, Normal_Font);
			   addEmptyLine(document,1);
			   experienceSet.addCell(skillHeader);
			   experienceSet.addCell(skillDetail);
			   
			   ArrayList<String> gen = temp.getGenDutyDetails();
			   //ArrayList<String> pro = temp.getProjDetails();
			   ArrayList<String> app = temp.getAppSupDetails();
			   
			   if(gen.size() > 0) {
				   createList(experienceSet, gen, " General Duties", false);
			   }
			   //createList(experienceSet, pro, " Projects", false);  
			   if(!(temp.getProjDetails() == null)) {
				   ArrayList<Phrase> projList = new ArrayList<>();
				   ArrayList<String> projText = new ArrayList<>();
				   
				   for(ProjDetail detail: temp.getProjDetails().getProjDetail()) {
					   if(detail.getUrl() == null) {
						   String projDetailText = new String();
						   projDetailText = detail.getName() + " -" + detail.getDescription() + " -" + detail.getDuration();
						   projText.add(projDetailText);
					   } else {  
						   Phrase project = new Phrase();
						   project.add(getUrl(detail.getName(), detail.getUrl(), urlList));
						   project.add(new Chunk (" -" + detail.getDescription() + " -" + detail.getDuration(),Normal_Font));
						   projList.add(project);
					   }
				   }
				   
				   if(projList.size() > 0) {
					   createList(experienceSet, projList, " Projects ", true, true);
					   projList.clear();
				   }
				   
				   if(projText.size() > 0) {
					   createList(experienceSet, projText, " Projects", false); 
					   projText.clear();
				   }
				   
			   }
			   
			   if(app.size() > 0) {
				   createList(experienceSet, app, " App. Support:", true);
			   }

		}
		
		experienceSet.setSpacingAfter(30f);
		document.add(experienceSet); 

	}

	@Override
	public void addEducation(Document document, ArrayList<Education> education) throws DocumentException {
		addEmptyLine(document, 5);
		   
		PdfPTable eduSet = new PdfPTable(2);
		eduSet.setTotalWidth(new float[] {33,100});
		eduSet.setHorizontalAlignment(Element.ALIGN_LEFT);
		   
		for (Education temp: education) {
			  
			   Phrase detail = new Phrase();
			   detail.add(new Chunk(temp.getSchoolName(), Normal_Font));
			   detail.add(", ");
			   detail.add(new Chunk(temp.getLocation(), Normal_Italic));
			   detail.add(", ");
			   detail.add(new Chunk(temp.getAchievement(), Normal_Font));
			   
			   PdfPCell detailCell = getDetail(detail);
			   //detailCell.setBorder(0);
			   //detailCell.addElement(detail);
			   
			   String durtext = temp.getStartDate() + "-" + temp.getEndDate();
			   //String location = temp.getSchoolName() + ", " + temp.getLocation() + ", " + temp.getAchievement();
			   PdfPCell duration = getHeader(durtext, Normal_Font);
			   //PdfPCell school = getDetail(location, Normal_Font);
			   eduSet.addCell(duration);
			   //eduSet.addCell(school);
			   eduSet.addCell(detailCell);
			   
			   if(!(temp.getCourses() == null)) {
				   //System.out.println("adding course");
				   ArrayList<Phrase> courseList = new ArrayList<Phrase>();
				   
				   for(Course classtext: temp.getCourses().getCourse()) {
					   Phrase courseDesc = new Phrase();
					   Chunk urlText = new Chunk(classtext.getName(), urlList);
					   urlText.setAnchor(classtext.getUrl());
					   courseDesc.add(urlText);
					   courseDesc.add(new Chunk("- " + classtext.getDescription() + " - " + classtext.getDuration(), Normal_Font));
					   
					   //courseDesc = classtext.getName() + "- " + classtext.getDescription() + " - " + classtext.getDuration();
					   //System.out.println(courseDesc);
					   courseList.add(courseDesc);
				   }
				   
				   createList(eduSet, courseList, " Courses ", false, true);
			   }
		}
		
		eduSet.setSpacingAfter(30f);
		document.add(eduSet);
	}
	
	public static void addEmptyLine(Document document, int number) throws DocumentException {
			for(int i=0; i < number;i++) {
				document.add(Chunk.NEWLINE);
			}
	}
	   
	public static PdfPCell getCell(String text, Font font) {
		   PdfPCell cell = new PdfPCell(new Phrase(text,font));
		   cell.setBorder(0);
		   return cell;
	}
	
	public static Chunk getUrl(String text, String urlText, Font font) {
		Chunk url = new Chunk(text, font);
		url.setAnchor(urlText);
		return url;
	}
	
	public static PdfPCell getCell(String text, Font font, boolean addurl) {
		   Chunk url = new Chunk(text, font);
		   url.setAnchor(text);
		   PdfPCell cell = new PdfPCell(new Phrase(url));
		   cell.setBorder(0);
		   return cell;
	}
	
	public static PdfPCell getCell(Phrase phrase) {
		   PdfPCell cell = new PdfPCell(phrase);
		   cell.setBorder(0);
		   return cell;
	}
	
	public static void drawRedLine(Document document, String text, PdfWriter writer) throws DocumentException {
		 
		  PdfPCell cellText = getCell(text,Title_Header);
		  cellText.setHorizontalAlignment(Element.ALIGN_LEFT);
		  cellText.setVerticalAlignment(Element.ALIGN_TOP);
		  cellText.setBorder(0);

		  PdfTemplate template = writer.getDirectContent().createTemplate(100, 5);
		  template.setColorFill(BaseColor.RED);
		  template.rectangle(0,0,100,5);
		  template.fill();	  
		  try {
			writer.releaseTemplate(template);
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		  
		  PdfPCell line = new PdfPCell(Image.getInstance(template));
		  line.setBorder(0);  
		  line.setVerticalAlignment(Element.ALIGN_MIDDLE);
		  
		  PdfPTable table = new PdfPTable(2);
		  table.setWidthPercentage(40f);
		  table.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.setSpacingAfter(10f);
		  table.addCell(line);
		  table.addCell(cellText);
		  
		  document.add(table);	   
	  }
	   
	public static PdfPCell getDetail(String text, Font font) {
		   PdfPCell cell= getCell(text, font);
		   cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		   cell.setIndent(0);
		   cell.disableBorderSide(Rectangle.NO_BORDER);
		   return cell;
	}
	
	public static PdfPCell getDetail(Phrase phrase) {
		PdfPCell cell = new PdfPCell(phrase);
		cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		cell.setIndent(0);
		cell.disableBorderSide(Rectangle.NO_BORDER);
		cell.setBorder(0);
		return cell;
	}
	   
	public static PdfPCell getHeader(String text, Font font) {
		   PdfPCell cell = getCell(text, font);
		   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		   cell.setIndent(25);
		   return cell;
	}
	
	public static PdfPCell getList(String Header, ArrayList<String> details) {
		   PdfPCell newCell = new PdfPCell();
		   
		   newCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
		   newCell.setIndent(0);
		   newCell.disableBorderSide(Rectangle.NO_BORDER);
		   
		   return newCell;
	   }
	
	public static void createList(PdfPTable table, ArrayList<String> text, String Header, boolean addBlankLine) {
		   
		   //Font font = new Font(FontFamily.HELVETICA, 12);
		   PdfPCell blank = new PdfPCell();
		   Phrase listPhrase = new Phrase();
		   listPhrase.setFont(Normal_Font);
		   blank.setBorder(0);
		   blank.setFixedHeight(10f);
		   
		   table.addCell(blank);
		   
		   List headerList = new List();
		   Phrase ph = new Phrase();
		   headerList.setListSymbol("\u2022");
		   headerList.add(new ListItem(15,Header, Normal_Font));
		   ph.add(headerList);
		   
		   
		   PdfPCell listHeader = new PdfPCell();
		   listHeader.setBorder(0);
		   listHeader.addElement(ph);
		  
		   table.addCell(listHeader);
		   
		   table.addCell(blank);
		   
		   List list = new List();
		   list.setListSymbol("-");
		   list.setIndentationLeft(25);
		   
		   for(String temp: text) {
			   list.add(new ListItem(13,temp,Normal_Font));
		   }
		   
		   listPhrase.add(list);
		   PdfPCell listCell = new PdfPCell();
		   listCell.setBorder(0);
		   listCell.addElement(listPhrase);
		   table.addCell(listCell);
		   
		   if(addBlankLine	) {
			   table.addCell(blank);
			   table.addCell(blank);
			   table.addCell(blank);
			   table.addCell(blank);
		   }
	}
	
	public static void createList(PdfPTable table, ArrayList<Phrase> phrase, String Header,boolean addBlankLine, boolean addUrl) {
		   
		   //Font font = new Font(FontFamily.HELVETICA, 12);
		   PdfPCell blank = new PdfPCell();
		   Phrase listPhrase = new Phrase();
		   listPhrase.setFont(Normal_Font);
		   blank.setBorder(0);
		   blank.setFixedHeight(10f);
		   
		   table.addCell(blank);
		   
		   List headerList = new List();
		   Phrase ph = new Phrase();
		   headerList.setListSymbol("\u2022");
		   headerList.add(new ListItem(15,Header, Normal_Font));
		   ph.add(headerList);
		   
		   
		   PdfPCell listHeader = new PdfPCell();
		   listHeader.setBorder(0);
		   listHeader.addElement(ph);
		  
		   table.addCell(listHeader);
		   
		   table.addCell(blank);
		   
		   List list = new List();
		   list.setListSymbol("- ");
		   list.setIndentationLeft(25);
		   
		   for(Phrase temp: phrase) {
			   list.add(new ListItem(temp));
		   }
		   
		   listPhrase.add(list);
		   PdfPCell listCell = new PdfPCell();
		   listCell.setBorder(0);
		   listCell.addElement(listPhrase);
		   table.addCell(listCell);
		   
		   if(addBlankLine	) {
			   table.addCell(blank);
			   table.addCell(blank);
			   table.addCell(blank);
			   table.addCell(blank);
		   }
	}
	
	public static void addReference(Document document,  PdfWriter writer) throws DocumentException {
		 
		  PdfPCell cellText = getCell("References avail. upon request",Ref);
		  cellText.setHorizontalAlignment(Element.ALIGN_LEFT);
		  cellText.setVerticalAlignment(Element.ALIGN_TOP);
		  cellText.setBorder(0);
	
		  PdfTemplate template = writer.getDirectContent().createTemplate(100, 5);
		  template.setColorFill(BaseColor.RED);
		  template.rectangle(0,0,100,5);
		  template.fill();	  
		  try {
			writer.releaseTemplate(template);
		  } catch (IOException e) {
			e.printStackTrace();
		  }
		  
		  PdfPCell line = new PdfPCell(Image.getInstance(template));
		  line.setBorder(0);  
		  line.setVerticalAlignment(Element.ALIGN_MIDDLE);
		  
		  PdfPTable table = new PdfPTable(2);
		  table.setWidths(new float[] {2,4});
		  table.setWidthPercentage(60f);
		  table.setHorizontalAlignment(Element.ALIGN_LEFT);
		  table.setSpacingAfter(10f);
		  table.addCell(line);
		  table.addCell(cellText);
		  
		  document.add(table);	   
	  }

}
