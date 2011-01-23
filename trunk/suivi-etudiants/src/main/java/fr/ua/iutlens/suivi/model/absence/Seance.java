package fr.ua.iutlens.suivi.model.absence;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.note.Groupe;
import fr.ua.iutlens.suivi.model.note.Matiere;
import fr.ua.iutlens.suivi.model.personne.Enseignant;

/**
 * Entity implementation class for Entity: Seance
 *
 */
@Entity

public class Seance extends BaseEntity implements Serializable {

	   
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int duree;
	private String type;
	private String lieu;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false)
	private Matiere matiere;
		
	@ManyToOne(optional=false)
	private Groupe groupe;
	
	@ManyToOne(optional=false)
	private Enseignant enseignant;

	public Seance() {
		super();
	}   
 
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date Date) {
		this.date = Date;
	}   
	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int Duree) {
		this.duree = Duree;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public void setMatiere(Matiere idMatiere) {
		this.matiere = idMatiere;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setGroupe(Groupe idGroupe) {
		this.groupe = idGroupe;
	}
	public Groupe getGroupe() {
		return groupe;
	}
	public void setEnseignant(Enseignant idEnseignant) {
		this.enseignant = idEnseignant;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	@Override
	public String getDisplayText() {
		return groupe.getId() + "_" + enseignant.getId() + "_" + matiere.getId() + "_" + date;
	}
   
}