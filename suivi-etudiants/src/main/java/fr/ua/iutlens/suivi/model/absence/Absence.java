package fr.ua.iutlens.suivi.model.absence;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.note.Inscription;
import javax.persistence.Column;

/**
 * Entity implementation class for Entity: Absence
 *
 */
@Entity

public class Absence extends BaseEntity implements Serializable {

	@Column(length=500)
	private String nature; //la nature de l'absence
	@Column(length=500)
	private String justifie; //commentaire de la validation par l'administration
	private Boolean valide; //validation de l'absence par l'administration
	private static final long serialVersionUID = 1L;
	
	//la table absence prend la clé primaire de la table coupon en clé étrangère
	@ManyToOne(optional = false)
	private Coupon coupon;
	
	//la table absence prend la clé primaire de la table inscription en clé étrangère
	@ManyToOne(optional = false)
	private Inscription inscription;
	
	//la table absence prend la clé primaire de la table seance en clé étrangère
	@ManyToOne(optional=false)
	private Seance seance;
	 
	public Absence() {
		super();
	} 
	
	//renvoi de toutes les absences d'un élève
	//public int NbAbs()
	//{
	//	int nbAbs=0;
		
		
	//	return nbAbs;
		
	//}  

	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}   
	public String getJustifie() {
		return this.justifie;
	}

	public void setJustifie(String justifie) {
		this.justifie = justifie;
	}
	public void setCoupon(Coupon idCoupon) {
		this.coupon = idCoupon;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public void setSeance(Seance idSeance) {
		this.seance = idSeance;
	}
	public Seance getSeance() {
		return seance;
	}
	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	@Override
	public String getDisplayText() {
		return nature;
	}
   
}