package com.example.firstProj.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.firstProj.donnees.*;

public interface IUtilisateurService {
	public void add(Utilisateur user);
	public void delete(Integer id);
	public void update(Integer id, Utilisateur user);
	public List<Utilisateur> triUtilisateur(Integer i);
	public Utilisateur verifUtilisateur(String AdresseEmail,String mdp);
	public List<Utilisateur> findALL();
	public Optional<Utilisateur> findById(Integer id );
	public Collection<? extends Utilisateur> findById(List<Integer> invites);
	public List<Utilisateur> researchUser(String mot);

}
