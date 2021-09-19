package com.example.firstProj.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.firstProj.donnees.Medicament;
import com.example.firstProj.donnees.Ordonnance;
import com.example.firstProj.donnees.Utilisateur;
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
	public Optional<Ordonnance> findById(Integer id) {
		Ordonnance or=new Ordonnance();
		// TODO Auto-generated method stub
		List<Ordonnance> os=this.findALL();
		for (Ordonnance o : os) {
			if (o.getId()==id)
				or=o;
		}
		return Optional.ofNullable(or);
	}
	
	@Override
	public List<Ordonnance> triOrdonnance(int pos){
		//DESC
		if (pos==0) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.DESC, "nomMedecin"));
					}
		else if (pos==1) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.DESC, "nomPatient"));
		}
		else if (pos==2) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.DESC, "dateConsultation"));
		}
		else if (pos==3) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.DESC, "dateNaissance"));
		}
		//ASC
		else if (pos==4) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.ASC, "nomMedecin"));
		}
		else if (pos==5) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.ASC, "nomPatient"));
		}
		else if (pos==6) {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.ASC, "dateConsultation"));
		}
		else {
			return ordonnanceRepository.findAll(Sort.by(Sort.Direction.ASC, "dateNaissance"));
		}
	}

	@Override
	public Collection<? extends Ordonnance> findById(List<Integer> ids) {
		// TODO Auto-generated method stub
		return 		ordonnanceRepository.findAllById(ids);
	}
	
	@Override
	public List<Medicament> getMeds(int id){
		Optional<Ordonnance> o=this.findById(id);
		return o.get().getMedicaments();
	}
	
	@Override
	public List<Ordonnance> researchOrdonnance(String mot) {
		List<Ordonnance> os=this.findALL();
		List<Ordonnance> osUpdated = new ArrayList<>();
		for (Ordonnance o : os) {
			if (o.getNomMedecin().contains(mot) || o.getNomPatient().contains(mot)) {
				osUpdated.add(o);
			}
		}
		return osUpdated;
	}

}
