package suiviPro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import personne.Etudiant;

/**
 * Entity implementation class for Entity: ActivitePro
 *
 */
@Entity

public class ActivitePro implements Serializable {

	//les clés primaires/étrangères
	
	@Id
	private int id;
	
//	@ManyToOne
//	@JoinColumn(name="id_entreprise", referencedColumnName = "id_entreprise")
//	@Column(name="id_entreprise")
//	private Entreprise idEntreprise;
	
	
//	@ManyToOne
//	@JoinColumn(name="id_etudiant", referencedColumnName = "id_etudiant")
//	@Column(name="id_etudiant")
//	private Etudiant idEtudiant;
	
	//les champs renseignés
	@Column(name="date_deb_activite")
	@Temporal(TemporalType.DATE)
	private Date dateDebActivite;
	
	@Column(name="date_fin_activite")
	@Temporal(TemporalType.DATE)
	private Date dateFinActivite;
	
	@Column(name="type_contrat")
	private String typeContrat;
	
	private String poste;
	
	@Column(name="description_activite")
	private String descriptionActivite;
	
	@Column(name="commentaire_activite")
	private String commentaireActivite;
	
	
	private static final long serialVersionUID = 1L;

	public ActivitePro() {
		super();
	}   
	public Date getDateDebActivite() {
		return this.dateDebActivite;
	}

	public void setDateDebActivite(Date dateDebActivite) {
		this.dateDebActivite = dateDebActivite;
	}   
	public Date getDateFinActivite() {
		return this.dateFinActivite;
	}

	public void setDateFinActivite(Date dateFinActivite) {
		this.dateFinActivite = dateFinActivite;
	}   
	public String getTypeContrat() {
		return this.typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}   
	public String getPoste() {
		return this.poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}   
	public String getDescriptionActivite() {
		return this.descriptionActivite;
	}

	public void setDescriptionActivite(String descriptionActivite) {
		this.descriptionActivite = descriptionActivite;
	}   
	public String getCommentaireActivite() {
		return this.commentaireActivite;
	}

	public void setCommentaireActivite(String commentaire_activite) {
		this.commentaireActivite = commentaire_activite;
	}
//	public void setIdEntreprise(Entreprise idEntreprise) {
//		this.idEntreprise = idEntreprise;
//	}
//	public Entreprise getIdEntreprise() {
//		return idEntreprise;
//	}
//	public void setIdEtudiant(Etudiant idEtudiant) {
//		this.idEtudiant = idEtudiant;
//	}
//	public Etudiant getIdEtudiant() {
//		return idEtudiant;
//	}
   
}