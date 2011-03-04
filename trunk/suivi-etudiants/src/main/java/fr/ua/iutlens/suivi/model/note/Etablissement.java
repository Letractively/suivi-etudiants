package fr.ua.iutlens.suivi.model.note;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Etablissement
 *
 */
@Entity

public class Etablissement extends BaseEntity implements Serializable {

	//champs definissant si il s'agit d'un IUT, lycee ...
	private String type;
	@Column(name = "nom_etablissement")
	private String nomEtablissement;
	//les champs de l'adresse
	private String numero;
	private String adresse;
	private String codePostal;
	private String ville;
	private String mail;
	private String tel;
	
	//relation N-N : chaque etablissement aura une liste de formation
	@ManyToMany
	private List<Formation> formations;
	private static final long serialVersionUID = 1L;

	public Etablissement() {
		super();
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getNomEtablissement() {
		return this.nomEtablissement;
	}

	public void setNomEtablissement(String nomEtablissement) {
		this.nomEtablissement = nomEtablissement;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
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
	public void setFormations(List<Formation> idFormation) {
		this.formations = idFormation;
	}
	public List<Formation> getFormations() {
		return formations;
	}
	
	public String getAdresseComplete(){
		return numero+ " " + adresse + " " + codePostal + " " + ville;
	}
	
	@Override
	public String getDisplayText() {
		return nomEtablissement;
	}
   
}
