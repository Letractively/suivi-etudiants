package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ACTIVITE_PROFESSIONNELLE database table.
 * 
 */
@Entity
@Table(name="ACTIVITE_PROFESSIONNELLE")
public class ActiviteProfessionnelle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActiviteProfessionnellePK id;

	private String commentaire;

    @Temporal( TemporalType.DATE)
	private Date datedebut;

    @Temporal( TemporalType.DATE)
	private Date datefin;

	private String description;

	private String posteoccupe;

	private String typecontrat;

	//bi-directional many-to-one association to Entreprise
    @ManyToOne
	@JoinColumn(name="IDENTREPRISE",nullable = false, insertable = false, updatable = false)
	private Entreprise entreprise;

	//bi-directional many-to-one association to Etudiant
    @ManyToOne
	@JoinColumn(name="IDETUDIANT",nullable = false, insertable = false, updatable = false)
	private Etudiant etudiant;

    public ActiviteProfessionnelle() {
    }

	public ActiviteProfessionnellePK getId() {
		return this.id;
	}

	public void setId(ActiviteProfessionnellePK id) {
		this.id = id;
	}
	
	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDatedebut() {
		return this.datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return this.datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPosteoccupe() {
		return this.posteoccupe;
	}

	public void setPosteoccupe(String posteoccupe) {
		this.posteoccupe = posteoccupe;
	}

	public String getTypecontrat() {
		return this.typecontrat;
	}

	public void setTypecontrat(String typecontrat) {
		this.typecontrat = typecontrat;
	}

	public Entreprise getEntreprise() {
		return this.entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	public Etudiant getEtudiant() {
		return this.etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
}