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

import personne.Personne;

/**
 * Entity implementation class for Entity: SuiviStage
 *
 */
@Entity

public class SuiviStage implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id_suivi")
	private int idSuivi;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCommentaire;
	@Column(name= "commentaire_suivi_stage")
	private String commentaireSuiviStage;
	
	//clé étrangère id_stage
	@ManyToOne
	@JoinColumn(name="id_stage",referencedColumnName ="id_stage")
	private Stage idStage;
	
	// clé étrangère id_personne
	@ManyToOne
	@JoinColumn(name="id_personne",referencedColumnName ="id_personne")
	private Personne idPersonne;
	
	private static final long serialVersionUID = 1L;

	public SuiviStage() {
		super();
	}   
	public Integer getIdSuivi() {
		return this.idSuivi;
	}

	public void setIdSuivi(Integer idSuivi) {
		this.idSuivi = idSuivi;
	}   
	public Date getDateCommentaire() {
		return this.dateCommentaire;
	}

	public void setDateCommentaire(Date dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}   
	public String getCommentaireSuiviStage() {
		return this.commentaireSuiviStage;
	}

	public void setCommentaireSuiviStage(String commentaire_suivi_stage) {
		this.commentaireSuiviStage = commentaire_suivi_stage;
	}
	public void setIdStage(Stage idStage) {
		this.idStage = idStage;
	}
	public Stage getIdStage() {
		return idStage;
	}
	public void setIdPersonne(Personne idPersonne) {
		this.idPersonne = idPersonne;
	}
	public Personne getIdPersonne() {
		return idPersonne;
	}
   
}