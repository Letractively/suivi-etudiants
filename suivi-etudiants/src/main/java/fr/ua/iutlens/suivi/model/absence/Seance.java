package fr.ua.iutlens.suivi.model.absence;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
	
	@Column(length=2)
	private int duree; //la dur�e du cours
	
	@Column(length=3)
	private String type; //on a soit CM, soit TD, soit TP, soit DS ...
	
	@Column(length=20)
	private String lieu; //la salle o� se d�roule le cours
	
	private static final long serialVersionUID = 1L;
	
	//la table seance prend la cl� �trang�re de la table mati�re
	@ManyToOne(optional=false)
	private Matiere matiere;
		
	//la table seance prend la cl� �trang�re de la table groupe
	@ManyToOne(optional=false)
	private Groupe groupe;
	
	//la table seance prend la cl� �trang�re de la table enseignent
	@ManyToOne(optional=false)
	private Enseignant enseignant;

	//il n'y a aucune table qui reprend la cl� primaire de seance en cl� �trang�re
	public Seance() {
		super();
	}   
 
	//on r�cup�re la date du cours
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date Date) {
		this.date = Date;
	}   
	
	//on r�cup�re la dur�e du cours
	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int Duree) {
		this.duree = Duree;
	}   
	
	//on r�cup�re le type du cours (TD, TP, CM ...)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setMatiere(Matiere idMatiere) {
		this.matiere = idMatiere;
	}
	
	//on r�cup�re l'id de matiere
	public Matiere getMatiere() {
		return matiere;
	}
	
	public void setGroupe(Groupe idGroupe) {
		this.groupe = idGroupe;
	}
	
	//on r�cup�re l'id du groupe pour une s�ance donn�e
	public Groupe getGroupe() {
		return groupe;
	}
	
	public void setEnseignant(Enseignant idEnseignant) {
		this.enseignant = idEnseignant;
	}
	
	//on r�cup�re l'id de l'enseignent qui fait cours pour une s�ance donn�e
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