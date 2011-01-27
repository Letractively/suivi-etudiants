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

import fr.ua.iutlens.suivi.model.suivipro.ActivitePro;
import fr.ua.iutlens.suivi.model.suivipro.Stage;

/**
 * Entity implementation class for Entity: Etudiant
 * 
 */
@Entity
public class Etudiant extends Personne implements Serializable {

	// champs date de naissance (jour/mois/année)
	@Temporal(TemporalType.DATE)
	@Column(name = "date_naissance")
	private Date datedenaissance;
	
	// champs nationalité
	@Column(length = 50)
	//	@ BASIC : Le type le plus simple de mappage à une colonne de base de données. 
	//	L'annotation de base peut être appliquée à une propriété persistante ou variable 
	//	d'instance de l'un des types suivants(string, date,...) 
	@Basic
	private String nationalite;

	// un étudiant possède une liste d'activités pro. (0 à n)
	@OneToMany(mappedBy = "etudiant")
	private List<ActivitePro> activitesPro;

	// un étudiant possède une liste de stage. (0à n)
	@OneToMany(mappedBy = "etudiant")
	private List<Stage> stages;

	// champs numéro_étudiant (unique)
	@Column(unique=true)
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

	public List<Stage> getStages() {
		return stages;
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
	}

}