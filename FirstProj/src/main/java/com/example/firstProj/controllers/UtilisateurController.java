package com.example.firstProj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstProj.donnees.*;
import com.example.firstProj.repositories.*;
import com.example.firstProj.services.*;

@RequestMapping("/utilisateur")
@RestController
@CrossOrigin
public class UtilisateurController {
	@Autowired
	IUtilisateurService utilisateurService ; 

	@PostMapping("/ajouter")
	public Utilisateur  ajouterUtilisateur(@RequestBody Utilisateur user) {
		utilisateurService.add(user);
		return user;
	}

	@GetMapping("/afficher")
	public List<Utilisateur> findAll() {
		return utilisateurService.findALL();
	}

	@GetMapping("/{id}")
	public Utilisateur findById(@PathVariable Integer id)
	{
		return 		utilisateurService.findById(id);
	}

	@DeleteMapping("/supprimer/{id}")
	public void supprimer(@PathVariable Integer id) {
		utilisateurService.delete(id);
	}
	@PostMapping("/maj/{id}")
	public Utilisateur update(@RequestBody Utilisateur user,@PathVariable Integer id ) {
        utilisateurService.update(id,user);
		return user;		
	}
}
