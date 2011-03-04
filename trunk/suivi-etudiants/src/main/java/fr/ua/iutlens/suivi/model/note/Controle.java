package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.personne.Enseignant;
import fr.ua.iutlens.suivi.model.personne.Etudiant;

/**
 * Entity implementation class for Entity: Controle
 * 
 */
@Entity
public class Controle extends BaseEntity implements Serializable {
	
	@Column(name = "date_controle")
	@Temporal(TemporalType.DATE)
	private Date dateControle;
	//champs servant à définir s'il s'agit d'un controle pour le controle continu ou du partiel
	private String indicatif;
	//clé etrangere : un groupe aura plusieurs controle
	@ManyToOne
	private Groupe groupe;
	//clé étrangere : une matiere aura plusieurs controle
	@ManyToOne
	private Matiere matiere;
	//clé etrangere : un enseignant aura plusieurs controle
	@ManyToOne
	private Enseignant enseignant;
	//un controle possedera plusieurs notes
	@OneToMany(mappedBy = "controle")
	private List<Note> notes;
	private static final long serialVersionUID = 1L;

	public Controle() {
		super();
	}
	
	public double noteEtudiant(Etudiant etudiant){
		for (Note note : notes){
			if(note.noteEtudiant(etudiant)!=-1){
				return note.noteEtudiant(etudiant);
			}
		}
		return -1;
	}

	public Date getDateControle() {
		return this.dateControle;
	}

	public void setDateControle(Date dateControle) {
		this.dateControle = dateControle;
	}

	public String getIndicatif() {
		return this.indicatif;
	}

	public void setIndicatif(String indicatif) {
		this.indicatif = indicatif;
	}

	public Groupe getGroupe() {
		return this.groupe;
	}

	public void setGroupe(Groupe idGroupe) {
		this.groupe = idGroupe;
	}

	public Matiere getMatiere() {
		return this.matiere;
	}

	public void setMatiere(Matiere idMatiere) {
		this.matiere = idMatiere;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant idEnseignant) {
		this.enseignant = idEnseignant;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String getDisplayText() {
		return indicatif;
	}
}
