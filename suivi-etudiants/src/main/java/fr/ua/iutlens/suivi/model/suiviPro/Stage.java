package suiviPro;

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
import personne.Etudiant;
import personne.Professionnel;

/**
 * Entity implementation class for Entity: Stage
 *
 */
@Entity

public class Stage implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_stage")
	private int idStage;
	
	@Column(name = "Date_Deb_Stage")
	@Temporal(TemporalType.DATE)
	private Date DateDebStage;
	
	@Column(name = "Date_Fin_Stage")
	@Temporal(TemporalType.DATE)
	private Date DateFinStage;
	
	private static final long serialVersionUID = 1L;

	//clé étrangère id_etudiant
	@ManyToOne
	@JoinColumn(name="id_etudiant",referencedColumnName ="id_etudiant")
	private Etudiant idEtudiant;
	
	//clé étrangère id_enseignant
	@ManyToOne
	@JoinColumn(name="id_enseignant",referencedColumnName ="id_enseignant")
	private Enseignant idEnseignant;

	//clé étrangère id_professionnel
	@ManyToOne
	@JoinColumn(name="id_professionnel",referencedColumnName ="id_professionnel")
	private Professionnel idProfessionnel;
	
	public Stage() {
		super();
	}   
	public Integer getIdStage() {
		return this.idStage;
	}

	public void setIdStage(Integer idStage) {
		this.idStage = idStage;
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

	public void setDateFinStage(Date DateFinStage) {
		this.DateFinStage = DateFinStage;
	}
	public void setIdEtudiant(Etudiant idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public Etudiant getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEnseignant(Enseignant idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
	public Enseignant getIdEnseignant() {
		return idEnseignant;
	}
	public void setIdProfessionnel(Professionnel idProfessionnel) {
		this.idProfessionnel = idProfessionnel;
	}
	public Professionnel getIdProfessionnel() {
		return idProfessionnel;
	}
   
}