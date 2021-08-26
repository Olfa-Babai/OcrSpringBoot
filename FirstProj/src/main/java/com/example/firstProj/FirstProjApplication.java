package com.example.firstProj;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstProjApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure(); // log4j warning
		SpringApplication.run(FirstProjApplication.class, args);
	}

	@Bean
	   Tesseract getTesseract(){
	       Tesseract tesseract = new Tesseract();
	       tesseract.setDatapath("./tessdata");
	       return tesseract;
	   }
}