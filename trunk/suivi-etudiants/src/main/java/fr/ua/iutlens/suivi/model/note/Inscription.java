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
	private String resultat;
	@ManyToOne
	private Etape etape;
	@ManyToOne
	private Etudiant etudiant;
	
	@OneToMany(mappedBy = "inscription")
	private List<Note> notes;
	@ManyToMany
	private List<Groupe> groupes;
	
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

	public void setEtape(Etape idEtape) {
		this.etape = idEtape;
	}

	public Etape getEtape() {
		return etape;
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
}
