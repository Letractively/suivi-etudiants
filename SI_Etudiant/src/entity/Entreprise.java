package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Entity implementation class for Entity: Entreprise
 * 
 */
@Entity
@Table(name = "ENTREPRISE")
public class Entreprise implements Serializable {

  private static final long serialVersionUID = 1L;
  private long id;
  private String nom;
  private String raisonsociale;
  private String secteuractivite;
  private String siret;
  private Adresse adresse = new Adresse();
  private Contact contact=new Contact();
  private Set<EtudiantEntreprise> lesEtudiants = new HashSet<EtudiantEntreprise>();

  
  @OneToMany(mappedBy="entreprise",cascade =  CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  public Set<EtudiantEntreprise> getLesEtudiants() {
    return lesEtudiants;
  }

  public void setLesEtudiants(Set<EtudiantEntreprise> lesEtudiants) {
    this.lesEtudiants = lesEtudiants;
  }

  @Column(length = 70)
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Column(length = 70)
  public String getRaisonsociale() {
    return raisonsociale;
  }

  public void setRaisonsociale(String raisonsociale) {
    this.raisonsociale = raisonsociale;
  }

  @Column(length = 70)
  public String getSecteuractivite() {
    return secteuractivite;
  }

  public void setSecteuractivite(String secteuractivite) {
    this.secteuractivite = secteuractivite;
  }

  @Column(length = 70)
  public String getSiret() {
    return siret;
  }

  public void setSiret(String siret) {
    this.siret = siret;
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

  public Entreprise() {
    super();
  }

  @GeneratedValue
  @Id
  @Column(name="ENTREPRISE_ID")
  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }
  
  
  public int nbEtudiant()
  {	  
	  List<Etudiant> lEtu= new ArrayList<Etudiant>();
	  
	  for (EtudiantEntreprise etu: lesEtudiants)
	  {
		  lEtu.add(etu.getEtudiant());
	  }
	  Set set = new HashSet() ;
	  set.addAll(lEtu) ;
	  ArrayList distinctList = new ArrayList(set) ;

	  return distinctList.size();  
  }

}
