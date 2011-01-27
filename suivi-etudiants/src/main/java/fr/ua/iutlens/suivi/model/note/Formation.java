package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Formation
 *
 */
@Entity

public class Formation extends BaseEntity implements Serializable {


	private String type;
	@Column(name = "libelle_formation")
	private String libelleFormation;

	//Relation N-N : chaque formation poura se faire de plusieur etablissement
	@ManyToMany
	private List<Etablissement> etablissement;
	
	//une formation aura une liste d'etapes
	@OneToMany(mappedBy = "formation")
	private List<Etape> etapes;

	private static final long serialVersionUID = 1L;
	
	public Formation() {
		super();
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getLibelleFormation() {
		return this.libelleFormation;
	}

	public void setLibelleFormation(String libelleFormation) {
		this.libelleFormation = libelleFormation;
	}
	public void setEtablissement(List<Etablissement> idEtablissement) {
		this.etablissement = idEtablissement;
	}
	public List<Etablissement> getEtablissement() {
		return etablissement;
	}
	
	public List<Etape> getEtapes() {
		return etapes;
	}
	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}
	@Override
	public String getDisplayText() {
		return libelleFormation;
	}
   
}
