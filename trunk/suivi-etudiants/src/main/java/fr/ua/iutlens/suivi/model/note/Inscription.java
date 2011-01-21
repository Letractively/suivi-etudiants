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

import personne.Etudiant;

/**
 * Entity implementation class for Entity: Inscription
 *
 */
@Entity

public class Inscription implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_inscription")
	private int idInscription;
	@Column(name = "dateDeb_inscription")
	@Temporal(TemporalType.DATE)
	private Date dateDebInscription;
	@Column(name = "dateFin_inscription")
	@Temporal(TemporalType.DATE)
	private Date dateFinInscription;
	private String resultat;
	@ManyToOne
	@JoinColumn(name="id_etape",  referencedColumnName = "id_etape")
	private Etape idEtape;
	@ManyToOne
	@JoinColumn(name="id_etudiant", referencedColumnName = "id_etudiant")
	private Etudiant idEtudiant;
	private static final long serialVersionUID = 1L;

	public Inscription() {
		super();
	}   
	public Integer getIdInscription() {
		return this.idInscription;
	}

	public void setIdInscription(Integer idInscription) {
		this.idInscription = idInscription;
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
	public void setIdEtape(Etape idEtape) {
		this.idEtape = idEtape;
	}
	public Etape getIdEtape() {
		return idEtape;
	}
	public void setIdEtudiant(Etudiant idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public Etudiant getIdEtudiant() {
		return idEtudiant;
	} 
}
