package com.example.firstProj.services;

import java.util.Collection;
import java.util.List;

import com.example.firstProj.donnees.*;

public interface IOrdonnanceService {
	public void add(Ordonnance user);
	public void delete(Integer id);
	public void update(Integer id, Ordonnance user);
	public List<Ordonnance> findALL();
	public Ordonnance findById(Integer id );
	public Collection<? extends Ordonnance> findById(List<Integer> invites);
}
