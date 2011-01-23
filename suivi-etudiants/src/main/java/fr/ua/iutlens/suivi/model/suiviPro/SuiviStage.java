package fr.ua.iutlens.suivi.model.suiviPro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.ua.iutlens.suivi.model.BaseEntity;
import fr.ua.iutlens.suivi.model.personne.Personne;

/**
 * Entity implementation class for Entity: SuiviStage
 * 
 */
@Entity
public class SuiviStage extends BaseEntity implements Serializable {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_commentaire")
	private Date dateCommentaire;
	@Column(name = "commentaire_suivi_stage", length = 2000)
	private String commentaireSuiviStage;

	@ManyToOne
	private Stage stage;

	@ManyToOne
	private Personne personne;

	private static final long serialVersionUID = 1L;

	public SuiviStage() {
		super();
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

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setIdPersonne(Personne personne) {
		this.personne = personne;
	}

	public Personne getPersonne() {
		return personne;
	}

	@Override
	public String getDisplayText() {
		// TODO Auto-generated method stub
		return null;
	}

}