package com.example.firstProj.controllers;

import com.example.firstProj.donnees.OcrResult;
import com.example.firstProj.services.OcrService;
import com.example.firstProj.services.OrdonnanceService;
import com.itextpdf.text.DocumentException;

import net.sourceforge.tess4j.TesseractException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OcrController {
   @Autowired
   private OcrService ocrService;
   @Autowired
   private OrdonnanceService ordonnanceService;
 /*
   @PostMapping("/upload")
   public String upload(@RequestParam("file") String file) throws IOException, TesseractException {
       return "ok";
   }*/
   
   
    @PostMapping("/upload")
    public OcrResult upload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException, DocumentException {
        return ocrService.ocr(file);
    }
   
   @PostMapping("/test")
   public String testMethod() {
	  return System.getProperty("sun.arch.data.model");
   }
}