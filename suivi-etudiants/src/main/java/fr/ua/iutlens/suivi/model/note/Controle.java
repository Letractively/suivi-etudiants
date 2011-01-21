package note;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import personne.Enseignant;

/**
 * Entity implementation class for Entity: Controle
 *
 */
@Entity

public class Controle implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_controle")
	private int idControle;
	@Column(name = "date_controle")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateControle;
	private String indicatif;
	@ManyToOne
	@JoinColumn(name="id_groupe",  referencedColumnName = "id_groupe")
	private Groupe idGroupe;
	@ManyToOne
	@JoinColumn(name="id_matiere")
	private Matiere idMatiere;
	@ManyToOne
	@JoinColumn(name="id_enseignant",  referencedColumnName = "id_enseignant")
	private Enseignant idEnseignant;
	private static final long serialVersionUID = 1L;

	public Controle() {
		super();
	}   
	public int getIdControle() {
		return this.idControle;
	}

	public void setIdControle(int idControle) {
		this.idControle = idControle;
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
	public Groupe getIdGroupe() {
		return this.idGroupe;
	}

	public void setIdGroupe(Groupe idGroupe) {
		this.idGroupe = idGroupe;
	}   
	public Matiere getIdMatiere() {
		return this.idMatiere;
	}

	public void setIdMatiere(Matiere idMatiere) {
		this.idMatiere = idMatiere;
	}   
	public Enseignant getIdEnseignant() {
		return this.idEnseignant;
	}

	public void setIdEnseignant(Enseignant idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
}
