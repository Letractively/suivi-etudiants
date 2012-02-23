package entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Contact {
  private String mail;
  private String mailAutre;
  private String tel;
  private String telAutre;
  
   
  
  
  
  
  @Column(length = 70)
  public String getMail() {
    return mail;
  }
  public void setMail(String mail) {
    this.mail = mail;
  }
  @Column(length = 70)
  public String getMailAutre() {
    return mailAutre;
  }
  public void setMailAutre(String mailAutre) {
    this.mailAutre = mailAutre;
  }
  @Column(length = 70)
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  @Column(length = 70)
  public String getTelAutre() {
    return telAutre;
  }
  public void setTelAutre(String telAutre) {
    this.telAutre = telAutre;
  }

  
}
