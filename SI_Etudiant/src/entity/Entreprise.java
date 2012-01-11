package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ENTREPRISE database table.
 * 
 */
@Entity
public class Entreprise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long identreprise;

	private String mail;

	private String nom;

	private String raisonsociale;

	private String secteuractivite;

	private String tel;

	//bi-directional many-to-one association to ActiviteProfessionnelle
	@OneToMany(mappedBy="entreprise")
	private Set<ActiviteProfessionnelle> activiteProfessionnelles;

    public Entreprise() {
    }

	public long getIdentreprise() {
		return this.identreprise;
	}

	public void setIdentreprise(long identreprise) {
		this.identreprise = identreprise;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRaisonsociale() {
		return this.raisonsociale;
	}

	public void setRaisonsociale(String raisonsociale) {
		this.raisonsociale = raisonsociale;
	}

	public String getSecteuractivite() {
		return this.secteuractivite;
	}

	public void setSecteuractivite(String secteuractivite) {
		this.secteuractivite = secteuractivite;
	}

	public String getTelentreprise() {
		return this.tel;
	}

	public void setTelentreprise(String telentreprise) {
		this.tel = telentreprise;
	}

	public Set<ActiviteProfessionnelle> getActiviteProfessionnelles() {
		return this.activiteProfessionnelles;
	}

	public void setActiviteProfessionnelles(Set<ActiviteProfessionnelle> activiteProfessionnelles) {
		this.activiteProfessionnelles = activiteProfessionnelles;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}