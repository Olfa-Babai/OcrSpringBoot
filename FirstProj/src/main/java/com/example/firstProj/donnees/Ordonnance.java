package com.example.firstProj.donnees;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordonnance")
public class Ordonnance {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String nomMedecin;
	    private Date dateConsultation;
   //  @Column(unique = true)
	    private String nomPatient;
	    private Date dateNaissance;
	    
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public String getNomMedecin() {
			return nomMedecin;
		}

		public void setNomMedecin(String nomMedecin) {
			this.nomMedecin = nomMedecin;
		}
		
		public Date getDateConsultation() {
			return dateConsultation;
		}
		
		public void setDateConsultation(Date dateConsultation) {
			this.dateConsultation = dateConsultation;
		}
		
		public String getNomPatient() {
			return nomPatient;
		}
		
		public void setNomPatient(String nomPatient) {
			this.nomPatient = nomPatient;
		}
		
		public Date getDateNaissance() {
			return dateNaissance;
		}
		
		public void setDateNaissance(Date dateNaissance) {
			this.dateNaissance = dateNaissance;
		}
		
}