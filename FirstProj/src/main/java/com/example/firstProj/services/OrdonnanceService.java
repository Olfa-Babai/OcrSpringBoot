package com.example.firstProj.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.firstProj.donnees.Ordonnance;
import com.example.firstProj.repositories.*;
@Service
@Transactional
public class OrdonnanceService implements IOrdonnanceService {

	@Autowired
    private OrdonnanceRepository ordonnanceRepository;
	
	@Override
	public void add(Ordonnance ordonnance) {
		// TODO Auto-generated method stub
		ordonnanceRepository.save(ordonnance);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		ordonnanceRepository.deleteById(id);
	}

	@Override
	public void update(Integer id, Ordonnance ordonnance) {
		// TODO Auto-generated method stub
		ordonnanceRepository.save(ordonnance);
	}

	@Override
	public List<Ordonnance> findALL() {
		// TODO Auto-generated method stub
		return 		ordonnanceRepository.findAll();
	}

	@Override
	public Ordonnance findById(Integer id) {
		// TODO Auto-generated method stub
		return 		ordonnanceRepository.getById(id);
	}

	@Override
	public Collection<? extends Ordonnance> findById(List<Integer> ids) {
		// TODO Auto-generated method stub
		return 		ordonnanceRepository.findAllById(ids);
	}

}
