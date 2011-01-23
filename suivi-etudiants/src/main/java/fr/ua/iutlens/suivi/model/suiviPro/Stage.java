package fr.ua.iutlens.suivi.model.suiviPro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.BaseEntity;
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

	@Column(name = "Date_Deb_Stage")
	@Temporal(TemporalType.DATE)
	private Date DateDebStage;

	@Column(name = "Date_Fin_Stage")
	@Temporal(TemporalType.DATE)
	private Date DateFinStage;

	@Column(name = "url_rapport")
	private String urlRapport;

	private Entreprise entreprise;

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Etudiant etudiant;

	@ManyToOne
	private Enseignant enseignant;

	@ManyToOne
	private Professionnel professionnel;

	@OneToMany(mappedBy = "stage")
	private List<SuiviStage> suivisStage;

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

	@Override
	public String getDisplayText() {
		return etudiant.getNomPersonne() + "_" + enseignant.getNomPersonne()
				+ "_" + professionnel.getNomPersonne();
	}

}