package entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.AccessType;
@Embeddable
@Access(AccessType.PROPERTY)
public class Adresse {
  private String adresse;
  private String adresseSuite;
  private String codePostal;
  private String ville;
  private String pays;
  
  @Column(length = 70)
  public String getAdresse() {
    return adresse;
  }
  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }
  @Column(length = 70)
  public String getAdresseSuite() {
    return adresseSuite;
  }
  public void setAdresseSuite(String adresseSuite) {
    this.adresseSuite = adresseSuite;
  }
  @Column(length = 70)
  public String getCodePostal() {
    return codePostal;
  }
  public void setCodePostal(String codePostal) {
    this.codePostal = codePostal;
  }
  @Column(length = 70)
  public String getVille() {
    return ville;
  }
  public void setVille(String ville) {
    this.ville = ville;
  }
  @Column(length = 70)
  public String getPays() {
    return pays;
  }
  public void setPays(String pays) {
    this.pays = pays;
  }
  
  

}
