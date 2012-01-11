package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ETUDIANT database table.
 * 
 */
@Entity
public class Etudiant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idetudiant;

	private String adresse;

	private String codepostal;

	private String mail;

	private String nationalite;

	private String nom;

	private String prenom;

	private String sexe;

	private String tel;

	//bi-directional many-to-one association to ActiviteProfessionnelle
	@OneToMany(mappedBy="etudiant")
	private Set<ActiviteProfessionnelle> activiteProfessionnelles;

	//bi-directional many-to-one association to EtudiantFormation
	@OneToMany(mappedBy="etudiant")
	private Set<EtudiantFormation> etudiantFormations;

    public Etudiant() {
    }

	public long getIdetudiant() {
		return this.idetudiant;
	}

	public void setIdetudiant(long idetudiant) {
		this.idetudiant = idetudiant;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodepostal() {
		return this.codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNationalite() {
		return this.nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Set<ActiviteProfessionnelle> getActiviteProfessionnelles() {
		return this.activiteProfessionnelles;
	}

	public void setActiviteProfessionnelles(Set<ActiviteProfessionnelle> activiteProfessionnelles) {
		this.activiteProfessionnelles = activiteProfessionnelles;
	}
	
	public Set<EtudiantFormation> getEtudiantFormations() {
		return this.etudiantFormations;
	}

	public void setEtudiantFormations(Set<EtudiantFormation> etudiantFormations) {
		this.etudiantFormations = etudiantFormations;
	}
	
}