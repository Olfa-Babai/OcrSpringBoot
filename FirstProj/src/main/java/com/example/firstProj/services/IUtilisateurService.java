package com.example.firstProj.services;

import java.util.Collection;
import java.util.List;

import com.example.firstProj.donnees.*;

public interface IUtilisateurService {
	public void add(Utilisateur user);
	public void delete(Integer id);
	public void update(Integer id, Utilisateur user);
	public List<Utilisateur> findALL();
	public Utilisateur findById(Integer id );
	public Collection<? extends Utilisateur> findById(List<Integer> invites);

}
