package com.example.firstProj.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.example.firstProj.donnees.*;

public interface IOrdonnanceService {
	public void add(Ordonnance user);
	public void delete(Integer id);
	public void update(Integer id, Ordonnance user);
	public List<Ordonnance> findALL();
	public Optional<Ordonnance> findById(Integer id );
	public Collection<? extends Ordonnance> findById(List<Integer> invites);
	public List<Medicament> getMeds(int id);
	List<Ordonnance> triOrdonnance(int pos);
	List<Ordonnance> researchOrdonnance(String mot);
}
