package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  private String typeEtab;
  private Adresse adresse=new Adresse();
  private Contact contact=new Contact();
  
  private Set<Formation> lesFormations = new HashSet<Formation>();

  public Etablissement() {
    super();
  }
  
  
  @OneToMany(mappedBy = "etablissement",cascade =  CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public Set<Formation> getLesFormations() {
	return lesFormations;
  }



public void setLesFormations(Set<Formation> lesFormations) {
	this.lesFormations = lesFormations;
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
  
  @Column(length = 70)
  public String getTypeEtab() {
	return typeEtab;
  }

  public void setTypeEtab(String typeEtab) {
	this.typeEtab = typeEtab;
  }
  public int nbFormations()
  {
	  return lesFormations.size();
  }

}
