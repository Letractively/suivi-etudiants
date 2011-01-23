package fr.ua.iutlens.suivi.model.absence;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.note.Inscription;

/**
 * Entity implementation class for Entity: Absence
 *
 */
@Entity

public class Absence extends BaseEntity implements Serializable {


	private String nature;
	private String justifie;
	private Boolean valide;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Coupon coupon;
	
	@ManyToOne(optional = false)
	private Inscription inscription;
	
	@ManyToOne(optional=false)
	private Seance seance;
	 
	
	
	public Absence() {
		super();
	}   

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