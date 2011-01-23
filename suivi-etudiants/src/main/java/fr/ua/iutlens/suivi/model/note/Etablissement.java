package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Etablissement
 *
 */
@Entity

public class Etablissement extends BaseEntity implements Serializable {


	private String type;
	@Column(name = "nom_etablissement")
	private String nomEtablissement;
	@ManyToMany(mappedBy = "etablissement")
	private List<Formation> formations;
	private static final long serialVersionUID = 1L;

	public Etablissement() {
		super();
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getNomEtablissement() {
		return this.nomEtablissement;
	}

	public void setNomEtablissement(String nomEtablissement) {
		this.nomEtablissement = nomEtablissement;
	}
	public void setFormations(List<Formation> idFormation) {
		this.formations = idFormation;
	}
	public List<Formation> getFormations() {
		return formations;
	}
	@Override
	public String getDisplayText() {
		return nomEtablissement;
	}
   
}
