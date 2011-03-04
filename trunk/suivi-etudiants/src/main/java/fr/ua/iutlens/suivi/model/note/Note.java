package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.personne.Etudiant;

/**
 * Entity implementation class for Entity: Note
 *
 */
@Entity

public class Note extends BaseEntity implements Serializable {
	
	private double note;
	//clé etrangere : une inscription aura plusieurs notes
	@ManyToOne
	private Inscription inscription;
	//clé etrangere : un controle aura plusieurs notes
	@ManyToOne
	private Controle controle;
	
	private static final long serialVersionUID = 1L;

	public Note() {
		super();
	}

	public double noteEtudiant(Etudiant etudiant){
		if (inscription.getEtudiant().equals(etudiant)){
			return note;
		}
		return -1;
	}
	
	public Inscription getInscription() {
		return inscription;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public double getNote() {
		return note;
	}

	@Override
	public String getDisplayText() {
		return inscription.getId() + "_" + controle.getId();
	}
   
}
