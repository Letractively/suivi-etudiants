package absence;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import note.Groupe;
import note.Matiere;
import personne.Enseignant;

/**
 * Entity implementation class for Entity: Seance
 *
 */
@Entity

public class Seance implements Serializable {

	   
	@Id
	@Column(name = "id_seance")
	private String idSeance;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private int duree;
	private String type;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_matiere", referencedColumnName = "id_matiere")
	private Matiere idMatiere;
		
	@ManyToOne(optional=false)
	@JoinColumn(name="id_groupe", referencedColumnName = "id_groupe")
	private Groupe idGroupe;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_enseignant", referencedColumnName = "id_enseignant")
	private Enseignant idEnseignant;

	public Seance() {
		super();
	}   
	public String getIdSeance() {
		return this.idSeance;
	}

	public void setIdSeance(String idSeance) {
		this.idSeance = idSeance;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date Date) {
		this.date = Date;
	}   
	public int getDuree() {
		return this.duree;
	}

	public void setDuree(int Duree) {
		this.duree = Duree;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public void setIdMatiere(Matiere idMatiere) {
		this.idMatiere = idMatiere;
	}
	public Matiere getIdMatiere() {
		return idMatiere;
	}
	public void setIdGroupe(Groupe idGroupe) {
		this.idGroupe = idGroupe;
	}
	public Groupe getIdGroupe() {
		return idGroupe;
	}
	public void setIdEnseignant(Enseignant idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
	public Enseignant getIdEnseignant() {
		return idEnseignant;
	}
   
}