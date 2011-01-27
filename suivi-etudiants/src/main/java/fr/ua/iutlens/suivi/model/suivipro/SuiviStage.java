package fr.ua.iutlens.suivi.model.suivipro;

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

	// champs date_commentaire, commentaire_suivi_stage
	
	@Temporal(TemporalType.TIMESTAMP)
	// timestamp (on récupère le jour/mois/année ainsi que l'heure)
	@Column(name = "date_commentaire")
	private Date dateCommentaire;
	@Column(name = "commentaire_suivi_stage", length = 2000)
	private String commentaireSuiviStage;

	// clé étrangère
	// un suivi est pour un seul stage.
	@ManyToOne
	private Stage stage;

	// clé étrangère
	// un commentaire de suivi est rédigé par une personne
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