package com.example.firstProj.services;

import com.example.firstProj.donnees.OcrResult;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
@Service
public class OcrService {
   @Autowired
   private Tesseract tesseract;
 
   public OcrResult ocr(MultipartFile file) throws IOException, TesseractException, DocumentException {
       File convFile = convert(file);
       String text = tesseract.doOCR(convFile).trim();
       OcrResult ocrResult = new OcrResult();
       ocrResult.setResult(text);
       savePdf(convFile,text);
       return ocrResult;
   }
 
   public static File convert(MultipartFile file) throws IOException {
       File convFile = new File(file.getOriginalFilename());
       convFile.createNewFile();
       FileOutputStream fos = new FileOutputStream(convFile);
       fos.write(file.getBytes());
       fos.close();
       return convFile;
   }
   
   public void savePdf(File file,String text) throws FileNotFoundException, DocumentException {
	   //extraction des données : 
	   String dateConsultation=text.substring(text.indexOf("Date")+6, text.indexOf("Date")+17); // Date de la consultation
       String nomEtPrenom=text.substring(text.indexOf("Prénom")+8, text.indexOf("Né")-1); // Nom et prénom
       String dateNaissance=text.substring(text.indexOf("Né(e)")+10, text.indexOf("Né(e)")+19); // Date de naissance 
      
       String exText=text.substring(text.indexOf("Ordonnance")+12);
       //les médicaments :
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
	   //enregistrement en pdf :
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
      
      PdfPCell a1=new PdfPCell(new Phrase("Nom du médecin"));
      donnee.addCell(a1);
      PdfPCell a2=new PdfPCell(new Phrase(text.substring(0, text.indexOf("Date")-1)));
      donnee.addCell(a2);
      
      document.add(donnee);
      document.add(new Paragraph(" "));
      
      Paragraph patient=new Paragraph("Les données du patient");
      document.add(patient);
      document.add(new Paragraph(" "));
      PdfPTable donnees=new PdfPTable(2);
      donnees.setWidthPercentage(100);
      
      PdfPCell c3=new PdfPCell(new Phrase("Nom & Prénom"));
      donnees.addCell(c3);
      PdfPCell c4=new PdfPCell(new Phrase(nomEtPrenom));
      donnees.addCell(c4);
      
      PdfPCell c5=new PdfPCell(new Phrase("Date de naissance"));
      donnees.addCell(c5);
      PdfPCell c6=new PdfPCell(new Phrase(dateNaissance));
      donnees.addCell(c6);
      
      document.add(donnees);
      document.add(new Paragraph(" "));
      Paragraph title2=new Paragraph(" Les médicaments ");
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
      
   }
   
}

