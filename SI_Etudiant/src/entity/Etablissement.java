package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
/**
 * Entity implementation class for Entity: Etablissement
 * 
 */
@Entity
@Table(name = "ETABLISSEMENT")
public class Etablissement implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String nom;
  private String type;
  private Adresse adresse;
  private Contact contact;

  public Etablissement() {
    super();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="ETABLISSEMENT_ID")
  public Long getId() {
    return this.id;
  }

  @Column(length = 70)
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Column(length = 70)
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Embedded
  public Adresse getAdresse() {
    return adresse;
  }
  
  public void setAdresse(Adresse adresse) {
    this.adresse = adresse;
  }

  @Embedded
  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
