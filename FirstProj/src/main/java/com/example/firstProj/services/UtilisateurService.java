package com.example.firstProj.services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Utilisateur findById(Integer id) {
		// TODO Auto-generated method stub
		return utilisateurRepository.getById(id);
	}

	@Override
	public Collection<? extends Utilisateur> findById(List<Integer> invites) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAllById(invites);

	}

}
