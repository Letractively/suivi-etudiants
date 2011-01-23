package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Groupe
 * 
 */
@Entity
public class Groupe extends BaseEntity implements Serializable {


	@Column(name = "nom_groupe")
	private String nomGroupe;

	private String anneeUniv;

	@ManyToMany(mappedBy = "groupes")
	private List<Inscription> inscriptions;

	private static final long serialVersionUID = 1L;

	public Groupe() {
		super();
	}


	public String getNomGroupe() {
		return this.nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}

	public String getAnneeUniv() {
		return anneeUniv;
	}

	public void setAnneeUniv(String anneeUniv) {
		this.anneeUniv = anneeUniv;
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@Override
	public String getDisplayText() {
		return nomGroupe;
	}

}
