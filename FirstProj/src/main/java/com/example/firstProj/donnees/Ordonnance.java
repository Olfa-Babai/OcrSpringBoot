package com.example.firstProj.donnees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	    @OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name ="ordonnance_id", referencedColumnName ="id")
	    private List<Medicament> medicaments=new ArrayList<>();
	    
	    public Ordonnance() {
	    	
	    }
	    
		public Ordonnance(String nomMedecin, Date dateConsultation, String nomPatient, Date dateNaissance, List<Medicament> medicaments) 
		{
			this.nomMedecin = nomMedecin;
			this.dateConsultation = dateConsultation;
			this.nomPatient = nomPatient;
			this.dateNaissance = dateNaissance;
			this.medicaments = medicaments;
		}

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

		public List<Medicament> getMedicaments() {
			return medicaments;
		}

		public void setMedicaments(List<Medicament> medicaments) {
			this.medicaments = medicaments;
		}
		
		
		
}