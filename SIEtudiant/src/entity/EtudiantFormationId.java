package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class EtudiantFormationId implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long etudiantId;
  private Long formationId;
  private Date datedebut;

  public EtudiantFormationId() {
  }

  public EtudiantFormationId(Long etudiantId, Long formationId, Date datedebut) {
    this.etudiantId = etudiantId;
    this.formationId = formationId;
    this.datedebut = datedebut;
  }

  public boolean equals(Object o) {
    if (o != null && o instanceof EtudiantFormationId) {
      EtudiantFormationId that = (EtudiantFormationId) o;
      return this.etudiantId.equals(that.etudiantId) && this.formationId.equals(that.formationId) && this.datedebut.equals(that.datedebut);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return etudiantId.hashCode() + formationId.hashCode();
  }

  @Column(name = "ETUDIANT_ID")
  public Long getEtudiantId() {
    return etudiantId;
  }

  public void setEtudiantId(Long etudiantId) {
    this.etudiantId = etudiantId;
  }

  @Column(name = "FORMATION_ID")
  public Long getFormationId() {
    return formationId;
  }

  public void setFormationId(Long entrepriseId) {
    this.formationId = entrepriseId;
  }
  
  @Temporal(TemporalType.DATE)
  public Date getDatedebut() {
    return datedebut;
  }

  public void setDatedebut(Date datedebut) {
    this.datedebut = datedebut;
  }
  
  
}