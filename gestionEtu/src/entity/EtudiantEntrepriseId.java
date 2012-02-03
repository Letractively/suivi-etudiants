package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
public class EtudiantEntrepriseId implements Serializable {
  private static final long serialVersionUID = 1L;
  private Date datedebut;
  private Long etudiantId;
  private Long entrepriseId;

  public EtudiantEntrepriseId() {
  }

  public EtudiantEntrepriseId(Date datedebut, Long etudiantId, Long entrepriseId) {
    this.datedebut = datedebut;
    this.etudiantId = etudiantId;
    this.entrepriseId = entrepriseId;
  }

  public boolean equals(Object o) {
    if (o != null && o instanceof EtudiantEntrepriseId) {
      EtudiantEntrepriseId that = (EtudiantEntrepriseId) o;
      return this.datedebut.equals(that.datedebut) && this.etudiantId.equals(that.etudiantId) && this.entrepriseId.equals(that.entrepriseId);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return (int)datedebut.getTime() + etudiantId.hashCode() + entrepriseId.hashCode();
  }
  
  
  @Temporal(TemporalType.DATE)
  @Column(nullable = false, updatable = false)
  public Date getDatedebut() {
    return datedebut;
  }

  public void setDatedebut(Date datedebut) {
    this.datedebut = datedebut;
  }



  @Column(name = "ETUDIANT_ID", nullable = false, updatable = false)
  public Long getEtudiantId() {
    return etudiantId;
  }

  public void setEtudiantId(Long etudiantId) {
    this.etudiantId = etudiantId;
  }

  @Column(name = "ENTREPRISE_ID", nullable = false, updatable = false)
  public Long getEntrepriseId() {
    return entrepriseId;
  }

  public void setEntrepriseId(Long entrepriseId) {
    this.entrepriseId = entrepriseId;
  }
  
  
}
