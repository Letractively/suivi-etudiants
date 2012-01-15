package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: EtudiantEntreprise
 * 
 */
@Entity
@Table(name="ETUDIANT_ENTREPRISE")

public class EtudiantEntreprise implements Serializable {

  private static final long serialVersionUID = 1L;
  private Date datefin;
  private String description;
  private String posteoccupe;
  private String typecontrat;
  private Etudiant etudiant;
  private Entreprise entreprise;
  private EtudiantEntrepriseId id = new EtudiantEntrepriseId();


  @EmbeddedId
  public EtudiantEntrepriseId getId() {
    return id;
  }

  public void setId(EtudiantEntrepriseId id) {
    this.id = id;
  }

  public EtudiantEntreprise() {
    super();
  }

  public EtudiantEntreprise(Etudiant etudiant, Entreprise entreprise) {
    this.etudiant = etudiant;
    this.entreprise = entreprise;

    this.id.setEtudiantId(etudiant.getId());
    this.id.setEntrepriseId(entreprise.getId());

    etudiant.getLesEntreprises().add(this);
    entreprise.getLesEtudiants().add(this);
  }

  @ManyToOne
  @JoinColumn(name = "ETUDIANT_ID", insertable = false, updatable = false)
  public Etudiant getEtudiant() {
    return etudiant;
  }

  public void setEtudiant(Etudiant etudiant) {
    this.etudiant = etudiant;
  }

  @ManyToOne
  @JoinColumn(name = "ENTREPRISE_ID", insertable = false, updatable = false)
  public Entreprise getEntreprise() {
    return entreprise;
  }

  public void setEntreprise(Entreprise entreprise) {
    this.entreprise = entreprise;
  }

  @Temporal(TemporalType.DATE)
  public Date getDatefin() {
    return datefin;
  }

  public void setDatefin(Date datefin) {
    this.datefin = datefin;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(length = 70)
  public String getPosteoccupe() {
    return posteoccupe;
  }

  public void setPosteoccupe(String posteoccupe) {
    this.posteoccupe = posteoccupe;
  }

  @Column(length = 70)
  public String getTypecontrat() {
    return typecontrat;
  }

  public void setTypecontrat(String typecontrat) {
    this.typecontrat = typecontrat;
  }

}
