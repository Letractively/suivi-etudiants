package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.absence.Absence;
import fr.ua.iutlens.suivi.model.personne.Etudiant;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Inscription
 * 
 */
@Entity
public class Inscription extends BaseEntity implements Serializable {

	@Column(name = "dateDeb_inscription")
	@Temporal(TemporalType.DATE)
	private Date dateDebInscription;
	@Column(name = "dateFin_inscription")
	@Temporal(TemporalType.DATE)
	private Date dateFinInscription;
	private String anneeUniv;
	//champs indiquant l'obtention ou pas du diplome associé à l'inscription
	private String resultat;
	// clé etrangere : un etudiant aura plusieurs inscription
	@ManyToOne
	private Etudiant etudiant;
	//une etapes pourra avoir plusieur inscription
	@ManyToOne
	private Etape Etape;
	//une inscription aura une liste de notes
	@OneToMany(mappedBy = "inscription")
	private List<Note> notes;
	//relation N-N : une inscription aura une liste de groupe 
	@ManyToMany(mappedBy="inscriptions")
	private List<Groupe> groupes;
	// une inscription aura une liste d'absence
	@OneToMany(mappedBy = "inscription")
	private List<Absence> absences;
	
	private static final long serialVersionUID = 1L;

	public Inscription() {
		super();
	}

	public Date getDateDebInscription() {
		return this.dateDebInscription;
	}

	public void setDateDebInscription(Date dateDebInscription) {
		this.dateDebInscription = dateDebInscription;
	}

	public Date getDateFinInscription() {
		return this.dateFinInscription;
	}

	public void setDateFinInscription(Date dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}

	public String getResultat() {
		return this.resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public void setEtudiant(Etudiant idEtudiant) {
		this.etudiant = idEtudiant;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public String getAnneeUniv() {
		return anneeUniv;
	}

	public void setAnneeUniv(String anneeUniv) {
		this.anneeUniv = anneeUniv;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	@Override
	public String getDisplayText() {
		return etudiant.getNumero()+ "_" + anneeUniv;
	}

	public void setEtape(Etape etape) {
		Etape = etape;
	}

	public Etape getEtape() {
		return Etape;
	}
}
