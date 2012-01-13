package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EtudiantEntrepriseId implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long etudiantId;
  private Long entrepriseId;

  public EtudiantEntrepriseId() {
  }

  public EtudiantEntrepriseId(Long etudiantId, Long entrepriseId) {
    this.etudiantId = etudiantId;
    this.entrepriseId = entrepriseId;
  }

  public boolean equals(Object o) {
    if (o != null && o instanceof EtudiantEntrepriseId) {
      EtudiantEntrepriseId that = (EtudiantEntrepriseId) o;
      return this.etudiantId.equals(that.etudiantId) && this.entrepriseId.equals(that.entrepriseId);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return etudiantId.hashCode() + entrepriseId.hashCode();
  }

  @Column(name = "ETUDIANT_ID")
  public Long getEtudiantId() {
    return etudiantId;
  }

  public void setEtudiantId(Long etudiantId) {
    this.etudiantId = etudiantId;
  }

  @Column(name = "ENTREPRISE_ID")
  public Long getEntrepriseId() {
    return entrepriseId;
  }

  public void setEntrepriseId(Long entrepriseId) {
    this.entrepriseId = entrepriseId;
  }
  
  
}
