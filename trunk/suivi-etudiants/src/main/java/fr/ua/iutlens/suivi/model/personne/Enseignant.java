package fr.ua.iutlens.suivi.model.personne;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Enseignant
 *
 */
@Entity

public class Enseignant extends Personne implements Serializable {

	   
	private String status;
	private static final long serialVersionUID = 1L;

	public Enseignant() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 
	
	
   
}