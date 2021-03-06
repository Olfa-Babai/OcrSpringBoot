package com.example.firstProj.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstProj.services.OrdonnanceService;

import com.example.firstProj.donnees.*;

@RequestMapping("/ordonnance")
@RestController
@CrossOrigin
public class OrdonnanceController {
	
	 @Autowired
	   private OrdonnanceService ordonnanceService; 
	// @CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/getmeds/{id}")
	    public List<Medicament> geMeds(@PathVariable int id){
	        return ordonnanceService.getMeds(id);
	    }
	// @CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/afficher")
	    public List<Ordonnance> findAll(){
	        return ordonnanceService.findALL();
	    }
	// @CrossOrigin(origins = "http://localhost:4200")
	 @GetMapping("/{id}")
	    public Optional<Ordonnance> findById(@PathVariable int id){
	        return ordonnanceService.findById(id);
	    }
	// @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/trio/{pos}")
    public List<Ordonnance> triOrdonnance(@PathVariable int pos){
    	return ordonnanceService.triOrdonnance(pos);
    }
	// @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/researcho/{mot}")
	public List<Ordonnance> researchOrdonnance(@PathVariable String mot) {
         return ordonnanceService.researchOrdonnance(mot);
	}	
	// @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable int id){
        ordonnanceService.delete(id);
    }

}
