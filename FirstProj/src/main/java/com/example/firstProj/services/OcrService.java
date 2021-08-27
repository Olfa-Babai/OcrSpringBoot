package com.example.firstProj.services;

import com.example.firstProj.donnees.OcrResult;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
 
@Service
public class OcrService {
   @Autowired
   private Tesseract tesseract;
 
   public OcrResult ocr(MultipartFile file) throws IOException, TesseractException {
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
   
   public void savePdf(File file,String text) {
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
		   System.out.println(meds.toString());
	   		}
	   catch (IOException e)
	   	  {
		   System.out.println(e.getMessage());
		   System.exit(1);
		   }
	   
   }
}

