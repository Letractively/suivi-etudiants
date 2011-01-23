package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Matiere
 *
 */
@Entity

public class Matiere extends BaseEntity implements Serializable {

	@Column(name = "libelle_matiere")
	private String libelleMatiere;
	@Column(name = "coef_matiere")
	private int coefMatiere;
	@ManyToOne
	private UE ue;
	private static final long serialVersionUID = 1L;

	public Matiere() {
		super();
	}   
	public String getLibelleMatiere() {
		return this.libelleMatiere;
	}

	public void setLibelleMatiere(String libelleMatiere) {
		this.libelleMatiere = libelleMatiere;
	}   
	public int getCoefMatiere() {
		return this.coefMatiere;
	}

	public void setCoefMatiere(int coefMatiere) {
		this.coefMatiere = coefMatiere;
	}   
	public UE getUe() {
		return this.ue;
	}

	public void setUe(UE idUE) {
		this.ue = idUE;
	}
	@Override
	public String getDisplayText() {
		return libelleMatiere;
	}
   
}
