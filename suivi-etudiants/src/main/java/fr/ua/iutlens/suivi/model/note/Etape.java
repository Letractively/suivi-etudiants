package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Etape
 * 
 */
@Entity
public class Etape extends BaseEntity implements Serializable {

	//champs definissant si il s'agit d'un semestre ou d'un trimestre
	@Column(name = "type_etape")
	private String typeEtape;
	@Column(name = "libelle_etape")
	private String libelleEtape;
	//clé etrangere : une formation poura servir pour plusieurs etapes
	@ManyToOne
	private Formation formation;
	private static final long serialVersionUID = 1L;

	public Etape() {
		super();
	}

	public String getTypeEtape() {
		return this.typeEtape;
	}

	public void setTypeEtape(String typeEtape) {
		this.typeEtape = typeEtape;
	}

	public String getLibelleEtape() {
		return this.libelleEtape;
	}

	public void setLibelleEtape(String libelleEtape) {
		this.libelleEtape = libelleEtape;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	@Override
	public String getDisplayText() {
		return libelleEtape;
	}

}
