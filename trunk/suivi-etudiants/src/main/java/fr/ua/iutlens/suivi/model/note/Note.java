package fr.ua.iutlens.suivi.model.note;

import fr.ua.iutlens.suivi.model.BaseEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Note
 *
 */
@Entity

public class Note extends BaseEntity implements Serializable {

	@ManyToOne
	private Inscription inscription;
	@ManyToOne
	private Controle controle;
	private Boolean valide;
	
	private static final long serialVersionUID = 1L;

	public Note() {
		super();
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

	public Boolean getValide() {
		return valide;
	}

	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	@Override
	public String getDisplayText() {
		// TODO Auto-generated method stub
		return inscription.getId() + "_" + controle.getId();
	}
   
}
