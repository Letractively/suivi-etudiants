package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: UE
 *
 */
@Entity

public class UE extends BaseEntity implements Serializable {


	@Column(name ="libelle_UE")
	private String libelleUE;
	@Column(name ="coef_UE")
	private int coefUE;
	//une etape aura une liste d'etape
	@ManyToOne
	private Etape etape;
	private static final long serialVersionUID = 1L;

	public UE() {
		super();
	}
	
	public String getLibelleUE() {
		return this.libelleUE;
	}

	public void setLibelleUE(String libelleUE) {
		this.libelleUE = libelleUE;
	}   
	public int getCoef() {
		return this.coefUE;
	}

	public void setCoef(int coefUE) {
		this.coefUE = coefUE;
	}
	public void setEtape(Etape idEtape) {
		this.etape = idEtape;
	}
	public Etape getEtape() {
		return etape;
	}
	@Override
	public String getDisplayText() {
		return libelleUE;
	}
   
}
