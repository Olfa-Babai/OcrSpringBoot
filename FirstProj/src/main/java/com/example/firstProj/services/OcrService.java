package com.example.firstProj.services;

import com.example.firstProj.donnees.*;
import com.example.firstProj.donnees.Ordonnance;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
@Service
public class OcrService {
   @Autowired
   private Tesseract tesseract;
   
   @Autowired
   private OrdonnanceService ordonnanceService;
 
   public String ocr(MultipartFile file) throws IOException, TesseractException, DocumentException {
       File convFile = convert(file);
       String text = tesseract.doOCR(convFile).trim();
       OcrResult ocrResult = new OcrResult();
       ocrResult.setResult(text);
       //pdf + bdd
       Ordonnance o=savePdf(convFile,text);
       ordonnanceService.add(o);
       return ocrResult.toString();
   }
 
   public static File convert(MultipartFile file) throws IOException {
       File convFile = new File(file.getOriginalFilename());
       convFile.createNewFile();
       FileOutputStream fos = new FileOutputStream(convFile);
       fos.write(file.getBytes());
       fos.close();
       return convFile;
   }
   
   public Ordonnance savePdf(File file,String text) throws FileNotFoundException, DocumentException {
	   Ordonnance ordonnance=new Ordonnance();
	   //data :
	   String dateConsultation=text.substring(text.indexOf("Date")+6, text.indexOf("Date")+17).trim(); // Date de la consultation
       String nomEtPrenom=text.substring(text.indexOf("Pr??nom")+8, text.indexOf("N??")-1); // Nom et pr??nom
       String dateNaissance=text.substring(text.indexOf("N??(e)")+10, text.indexOf("N??(e)")+21).trim(); // Date de naissance 
      
       String exText=text.substring(text.indexOf("Ordonnance")+12);
       
       String nomMed=text.substring(0, text.indexOf("Date")-1);
       // convert date formats : from String to Date
       ZoneId defaultZoneId = ZoneId.systemDefault();
       
       LocalDate d1= LocalDate.of(Integer.parseInt(dateConsultation.substring(6, 10)),Integer.parseInt(dateConsultation.substring(3,5)),Integer.parseInt(dateConsultation.substring(0, 2)));
       Date dateC= Date.from(d1.atStartOfDay(defaultZoneId).toInstant());
       
       LocalDate d2= LocalDate.of(Integer.parseInt(dateNaissance.substring(6, 10)),Integer.parseInt(dateNaissance.substring(3,5)),Integer.parseInt(dateNaissance.substring(0, 2)));
       Date dateN= Date.from(d2.atStartOfDay(defaultZoneId).toInstant());
       
       System.out.println(dateN);

       //les m??dicaments :
       HashMap<String,String> meds = new HashMap<String,String>(); 
       BufferedReader buff = new BufferedReader(new StringReader(exText));
	   try {
		   String line;
		   while ((line = buff.readLine()) != null) {
			   if(line.indexOf(".")!=-1) {
				meds.put(line.substring(2, line.indexOf(".")),line.substring(line.indexOf(".")+1,line.length()-1));
		        }
			   }
		   buff.close();
	   		}
	   catch (IOException e)
	   	  {
		   System.out.println(e.getMessage());
		   System.exit(1);
		   }
	   // export as pdf
	  String file_name=System.getProperty("user.home")+"\\Desktop\\ordonnanceOcr.pdf";
	  Document document=new Document();
      PdfWriter.getInstance(document,new FileOutputStream(file_name));
      
      document.open();
      Paragraph title=new Paragraph(" Ordonnance ");
      document.add(title);
      document.add(new Paragraph(" "));
      
      PdfPTable donnee=new PdfPTable(2);
      donnee.setWidthPercentage(100);
      
      PdfPCell c1=new PdfPCell(new Phrase("Date de la consultation"));
      donnee.addCell(c1);
      PdfPCell c2=new PdfPCell(new Phrase(dateConsultation));
      donnee.addCell(c2);
      
      PdfPCell a1=new PdfPCell(new Phrase("Nom du m??decin"));
      donnee.addCell(a1);
      PdfPCell a2=new PdfPCell(new Phrase(nomMed));
      donnee.addCell(a2);
      
      document.add(donnee);
      document.add(new Paragraph(" "));
      
      Paragraph patient=new Paragraph("Les donn??es du patient");
      document.add(patient);
      document.add(new Paragraph(" "));
      PdfPTable donnees=new PdfPTable(2);
      donnees.setWidthPercentage(100);
      
      PdfPCell c3=new PdfPCell(new Phrase("Nom & Pr??nom"));
      donnees.addCell(c3);
      PdfPCell c4=new PdfPCell(new Phrase(nomEtPrenom));
      donnees.addCell(c4);
      
      PdfPCell c5=new PdfPCell(new Phrase("Date de naissance"));
      donnees.addCell(c5);
      PdfPCell c6=new PdfPCell(new Phrase(dateNaissance));
      donnees.addCell(c6);
      
      document.add(donnees);
      document.add(new Paragraph(" "));
      Paragraph title2=new Paragraph(" Les m??dicaments ");
      document.add(title2);
      document.add(new Paragraph(" "));
      
      PdfPTable donnees2=new PdfPTable(2);
      donnees2.setWidthPercentage(100);
      
      PdfPCell b5=new PdfPCell(new Phrase("Nom"));
      donnees2.addCell(b5);
      PdfPCell b6=new PdfPCell(new Phrase("Mode d'emploi"));
      donnees2.addCell(b6);
      Iterator iterator = meds.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry mapentry = (Map.Entry) iterator.next();
        PdfPCell b1=new PdfPCell(new Phrase(mapentry.getKey()+""));
        donnees2.addCell(b1);
        PdfPCell b2=new PdfPCell(new Phrase(mapentry.getValue()+""));
        donnees2.addCell(b2);      
      }
      document.add(donnees2);
           
      document.close();
      
      // ordonnance implementation
      ordonnance.setDateConsultation(dateC);
      ordonnance.setDateNaissance(dateN);
      ordonnance.setNomMedecin(nomMed);
      ordonnance.setNomPatient(nomEtPrenom);
      
      List<Medicament> m=new ArrayList<>();
      Iterator i = meds.entrySet().iterator();
      while (i.hasNext()) {
      Map.Entry mapentry = (Map.Entry) i.next();
        Medicament med=new Medicament();
        med.setNom(mapentry.getKey()+"");
        med.setModeEmploi(mapentry.getValue()+"");
        m.add(med);
      }
        ordonnance.setMedicaments(m);
        System.out.println(ordonnance.getMedicaments());
        return ordonnance;
   }
   
}

