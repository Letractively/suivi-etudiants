package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Etudiant
 * 
 */
@Entity
@Table(name = "ETUDIANT")
public class Etudiant implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id;
  private String nom;
  private String prenom;
  private Adresse adresse;
  private Contact contact;
  private Set<EtudiantFormation> lesFormations = new HashSet<EtudiantFormation>();
  private Set<EtudiantEntreprise> lesEntreprises = new HashSet<EtudiantEntreprise>();

  // @OneToMany
  // public List<Formation> getFormations() {
  // return formations;
  // }
  //
  // public void setFormations(List<Formation> formations) {
  // this.formations = formations;
  // }

  public Etudiant() {
    super();
  }

  @OneToMany(mappedBy = "etudiant")
  public Set<EtudiantEntreprise> getLesEntreprises() {
    return lesEntreprises;
  }

  public void setLesEntreprises(Set<EtudiantEntreprise> lesEntreprises) {
    this.lesEntreprises = lesEntreprises;
  }

  @OneToMany(mappedBy = "etudiant")
  public Set<EtudiantFormation> getLesFormations() {
    return lesFormations;
  }

  public void setLesFormations(Set<EtudiantFormation> lesFormations) {
    this.lesFormations = lesFormations;
  }

  @Id
  @GeneratedValue
  @Column(name="ETUDIANT_ID")
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(length = 70)
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Column(length = 70)
  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  @Embedded
  public Adresse getAdresse() {
    return adresse;
  }

  @Embedded
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

}
