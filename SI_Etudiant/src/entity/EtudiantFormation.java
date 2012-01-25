package entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="ETUDIANT_FORMATION")
public class EtudiantFormation implements Serializable {

  private static final long serialVersionUID = 1L;
  private Date datefin;
  private String description;
  private String resultat;
  private Etudiant etudiant;
  private Formation formation;
  private EtudiantFormationId id = new EtudiantFormationId();


  @EmbeddedId
  public EtudiantFormationId getId() {
    return id;
  }

  public void setId(EtudiantFormationId id) {
    this.id = id;
  }

  public EtudiantFormation() {
    super();
  }

  public EtudiantFormation(Etudiant etudiant, Formation formation) {
    this.etudiant = etudiant;
    this.formation = formation;

    this.id.setEtudiantId(etudiant.getId());
    this.id.setFormationId(formation.getId());

    etudiant.getLesFormations().add(this);
    formation.getLesEtudiants().add(this);
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
  @JoinColumn(name = "FORMATION_ID", insertable = false, updatable = false)
  public Formation getFormation() {
    return formation;
  }

  public void setFormation(Formation formation) {
    this.formation = formation;
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
  public String getResultat() {
    return resultat;
  }

  public void setResultat(String resultat) {
    this.resultat = resultat;
  }

}
