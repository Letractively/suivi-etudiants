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
	private long id;

	private String mail;

	private String nom;

	private String raisonsociale;

	private String secteuractivite;

	private String tel;

	//bi-directional many-to-one association to EtudiantEntreprise
	@OneToMany(mappedBy="entreprise")
	private Set<EtudiantEntreprise> etudiantEntreprises;

    public Entreprise() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<EtudiantEntreprise> getEtudiantEntreprises() {
		return this.etudiantEntreprises;
	}

	public void setEtudiantEntreprises(Set<EtudiantEntreprise> etudiantEntreprises) {
		this.etudiantEntreprises = etudiantEntreprises;
	}
	
}