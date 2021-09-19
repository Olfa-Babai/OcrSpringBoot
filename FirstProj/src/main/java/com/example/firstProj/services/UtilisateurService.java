package com.example.firstProj.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.firstProj.donnees.*;
import com.example.firstProj.repositories.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurService implements IUtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Override
	public void add(Utilisateur user) {
		// TODO Auto-generated method stub
		utilisateurRepository.save(user);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		utilisateurRepository.deleteById(id);
	}

	@Override
	public void update(Integer id, Utilisateur user) {
		// TODO Auto-generated method stub
		utilisateurRepository.save(user);
	}

	@Override
	public List<Utilisateur> findALL() {
		// TODO Auto-generated method stub
		return 		utilisateurRepository.findAll();	
	}

	@Override
	public Optional<Utilisateur> findById(Integer id) {
		Utilisateur ut=new Utilisateur();
		// TODO Auto-generated method stub
		List<Utilisateur> us=this.findALL();
		for (Utilisateur u : us) {
			if (u.getId()==id)
				ut=u;
		}
		return Optional.ofNullable(ut);
	}

	@Override
	public Collection<? extends Utilisateur> findById(List<Integer> invites) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAllById(invites);

	}
	
	@Override
	public List<Utilisateur> triUtilisateur(Integer pos){
		if (pos==0) {
			return utilisateurRepository.findAll(Sort.by(Sort.Direction.DESC, "prenom"));
					}
		else if (pos==1) {
			return utilisateurRepository.findAll(Sort.by(Sort.Direction.DESC, "nom"));
		}
		else if (pos==2) {
			return utilisateurRepository.findAll(Sort.by(Sort.Direction.ASC, "prenom"));
		}
		else {
			return utilisateurRepository.findAll(Sort.by(Sort.Direction.ASC, "nom"));
		}
	}

	@Override
	public Utilisateur verifUtilisateur(String AdresseEmail,String mdp) {
		Utilisateur ut=new Utilisateur();
		List<Utilisateur> us=this.findALL();	
		for (Utilisateur u : us){
			if(u.getAdresseEmail().equalsIgnoreCase(AdresseEmail) && u.getMdp().equalsIgnoreCase(mdp)){
				ut=u;
			}
		}
		return ut;
	}

	
	@Override
	public List<Utilisateur> researchUser(String mot) {
		List<Utilisateur> us=this.findALL();
		List<Utilisateur> usUpdated = new ArrayList<>();
		for (Utilisateur u : us) {
			if (u.getPrenom().contains(mot) || u.getNom().contains(mot) || u.getAdresseEmail().contains(mot)) {
				usUpdated.add(u);
			}
		}
		return usUpdated;
	}
	

}
