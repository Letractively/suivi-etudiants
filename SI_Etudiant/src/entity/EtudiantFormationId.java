package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EtudiantFormationId implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long etudiantId;
  private Long formationId;

  public EtudiantFormationId() {
  }

  public EtudiantFormationId(Long etudiantId, Long formationId) {
    this.etudiantId = etudiantId;
    this.formationId = formationId;
  }

  public boolean equals(Object o) {
    if (o != null && o instanceof EtudiantFormationId) {
      EtudiantFormationId that = (EtudiantFormationId) o;
      return this.etudiantId.equals(that.etudiantId) && this.formationId.equals(that.formationId);
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
  
  
}
