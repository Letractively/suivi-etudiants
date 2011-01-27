package fr.ua.iutlens.suivi.model.suivipro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.note.Inscription;
import fr.ua.iutlens.suivi.model.personne.Enseignant;
import fr.ua.iutlens.suivi.model.personne.Etudiant;
import fr.ua.iutlens.suivi.model.personne.Professionnel;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Stage
 * 
 */
@Entity
public class Stage extends BaseEntity implements Serializable {

	// champs date_deb_stage, date_fin_stage (on r�cup�re uniquement jour/mois/ann�e)
	@Column(name = "date_deb_stage")
	@Temporal(TemporalType.DATE)
	private Date DateDebStage;
	@Column(name = "date_fin_stage")
	@Temporal(TemporalType.DATE)
	private Date DateFinStage;

	// champs url du rapport de stage
	@Column(name = "url_rapport")
	private String urlRapport;

	// cl� �trang�re de la table entreprise
	private Entreprise entreprise;

	private static final long serialVersionUID = 1L;

	// cl� �trang�re
	// un �tudiant poss�de un ou plusieurs stages et un stage appartient � un seul �tudiant
	@ManyToOne
	private Etudiant etudiant;

	// cl� �trang�re
	// un enseignant encadre un ou plusieurs stages et un stage est encadr� par un seul enseignant
	@ManyToOne
	private Enseignant enseignant;

	// cl� �trang�re
	// un professionnel s'occupe d'un ou plusieurs stages et un stage a un seul ma�tre de stage(pro)
	@ManyToOne
	private Professionnel professionnel;

	// un stage peut avoir une liste de suivi (commentaire de stage)
	@OneToMany(mappedBy = "stage")
	private List<SuiviStage> suivisStage;

	// cl� �trang�re
	// un stage est reli� � une inscription
	@ManyToOne
	private Inscription inscription;
	
	public Stage() {
		super();
	}

	public Date getDateDebStage() {
		return this.DateDebStage;
	}

	public void setDateDebStage(Date DateDebStage) {
		this.DateDebStage = DateDebStage;
	}

	public Date getDateFinStage() {
		return this.DateFinStage;
	}

	public String getUrlRapport() {
		return urlRapport;
	}

	public void setUrlRapport(String urlRapport) {
		this.urlRapport = urlRapport;
	}

	public void setDateFinStage(Date DateFinStage) {
		this.DateFinStage = DateFinStage;
	}

	public void setEtudiant(Etudiant idEtudiant) {
		this.etudiant = idEtudiant;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEnseignant(Enseignant idEnseignant) {
		this.enseignant = idEnseignant;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setProfessionnel(Professionnel idProfessionnel) {
		this.professionnel = idProfessionnel;
	}

	public Professionnel getProfessionnel() {
		return professionnel;
	}

	public List<SuiviStage> getSuivisStage() {
		return suivisStage;
	}

	public void setSuivisStage(List<SuiviStage> suivisStage) {
		this.suivisStage = suivisStage;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public void setInscription(Inscription inscription) {
		this.inscription = inscription;
	}

	public Inscription getInscription() {
		return inscription;
	}

	@Override
	public String getDisplayText() {
		return etudiant.getNomPersonne() + "_" + enseignant.getNomPersonne()
				+ "_" + professionnel.getNomPersonne();
	}

}