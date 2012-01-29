package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Formation
 * 
 */
@Entity
@Table(name = "FORMATION")
public class Formation implements Serializable {

  private long id;
  private static final long serialVersionUID = 1L;
  private String libelle;
  private String libelleCourt;
  private Etablissement etablissement;
  private Set<EtudiantFormation> lesEtudiants = new HashSet<EtudiantFormation>();

  @OneToMany(mappedBy = "formation",cascade =  CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public Set<EtudiantFormation> getLesEtudiants() {
    return lesEtudiants;
  }

  public void setLesEtudiants(Set<EtudiantFormation> lesEtudiants) {
    this.lesEtudiants = lesEtudiants;
  }

  public Formation() {
    super();  
    
  }

  @GeneratedValue
  @Id
  @Column(name="FORMATION_ID")
  public long getId() {
    return this.id;
  }

  @Column(length = 70)
  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  @Column(length = 70)
  public String getLibelleCourt() {
    return libelleCourt;
  }

  public void setLibelleCourt(String libelleCourt) {
    this.libelleCourt = libelleCourt;
  }

  @ManyToOne
  @JoinColumn(name = "ETABLISSEMENT_ETABLISSEMENT_ID", insertable = true, updatable = true)
  public Etablissement getEtablissement() {
    return etablissement;
  }

  public void setEtablissement(Etablissement etablissement) {
    this.etablissement = etablissement;
  }

  public void setId(long id) {
    this.id = id;
  }

}
