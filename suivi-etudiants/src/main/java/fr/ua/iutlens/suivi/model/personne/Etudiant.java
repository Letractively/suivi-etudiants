package fr.ua.iutlens.suivi.model.personne;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.suiviPro.ActivitePro;

/**
 * Entity implementation class for Entity: Etudiant
 *
 */
@Entity

public class Etudiant extends Personne implements Serializable {

	   
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_naissance")
	private Date datedenaissance;
	@Column(length = 50)
	@Basic
	private String nationalite;
	
	@OneToMany(mappedBy = "etudiant")
	private List<ActivitePro> activitesPro;
	
	private String numero;
	
	private static final long serialVersionUID = 1L;

	public Etudiant() {
		super();
	}   
	public Date getDatedenaissance() {
		return this.datedenaissance;
	}

	public void setDatedenaissance(Date datedenaissance) {
		this.datedenaissance = datedenaissance;
	}   
	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationnalite) {
		this.nationalite = nationnalite;
	}
	public List<ActivitePro> getActivitesPro() {
		return activitesPro;
	}
	public void setActivitesPro(List<ActivitePro> activitesPro) {
		this.activitesPro = activitesPro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
   
}