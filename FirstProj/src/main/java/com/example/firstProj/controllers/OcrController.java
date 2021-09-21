package com.example.firstProj.controllers;

import com.example.firstProj.services.OcrService;
import com.example.firstProj.services.OrdonnanceService;
import com.itextpdf.text.DocumentException;

import net.sourceforge.tess4j.TesseractException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.firstProj.donnees.*;

@RestController
@RequestMapping("/ocr")
@CrossOrigin
public class OcrController {
   @Autowired
   private OcrService ocrService;  
   
    @PostMapping("/upload")
    public OcrResult upload(@RequestParam("file") MultipartFile file) throws IOException, TesseractException, DocumentException {
        return ocrService.ocr(file);
    }
    
}