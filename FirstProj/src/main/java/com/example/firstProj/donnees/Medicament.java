package com.example.firstProj.donnees;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="medicament")
public class Medicament {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String modeEmploi;
	
	public Medicament() {
		
	}
	
	public Medicament(String n, String modeE) {
		this.nom=n;
		this.modeEmploi=modeE;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getModeEmploi() {
		return modeEmploi;
	}
	public void setModeEmploi(String modeEmploi) {
		this.modeEmploi = modeEmploi;
	}

	@Override
	public String toString() {
		return "Medicament [nom=" + nom + ", modeEmploi=" + modeEmploi + "]";
	}	
}
