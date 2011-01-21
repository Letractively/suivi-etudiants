package personne;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import suiviPro.Entreprise;

/**
 * Entity implementation class for Entity: contact
 *
 */
@Entity

public class Contact implements Serializable {

	   
	@Id
	private Integer idContact;
	private String numero;
	private String adresse;
	private Integer codepostal;
	private String ville;
	private String mail;
	private String telfixe;
	private String telport;
	private Integer idPers;
	private Integer idEnt;
	private Integer idEtab;
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=true)
	@JoinColumn(name="id_personne",referencedColumnName="id_personne")
	private Personne idPersonne;
	
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_entreprise",referencedColumnName="id_entreprise")
	private Entreprise idEntreprise;
	
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_etudiant",referencedColumnName="id_etudiant")
	private Etudiant idEtudiant;
	
	
	
	
	public Contact() {
		super();
	}   
	public Integer getIdContact() {
		return this.idContact;
	}

	public void setIdContact(Integer idContact) {
		this.idContact = idContact;
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}   
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}   
	public Integer getCodepostal() {
		return this.codepostal;
	}

	public void setCodepostal(Integer codepostal) {
		this.codepostal = codepostal;
	}   
	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}   
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}   
	public String getTelfixe() {
		return this.telfixe;
	}

	public void setTelfixe(String telfixe) {
		this.telfixe = telfixe;
	}   
	public String getTelport() {
		return this.telport;
	}

	public void setTelport(String telport) {
		this.telport = telport;
	}   
	public Integer getIdPers() {
		return this.idPers;
	}

	public void setIdPers(Integer idPers) {
		this.idPers = idPers;
	}   
	public Integer getIdEnt() {
		return this.idEnt;
	}

	public void setIdEnt(Integer idEnt) {
		this.idEnt = idEnt;
	}   
	public Integer getIdEtab() {
		return this.idEtab;
	}

	public void setIdEtab(Integer idEtab) {
		this.idEtab = idEtab;
	}
	public void setIdPersonne(Personne idPersonne) {
		this.idPersonne = idPersonne;
	}
	public Personne getIdPersonne() {
		return idPersonne;
	}
	public void setIdEntreprise(Entreprise idEntreprise) {
		this.idEntreprise = idEntreprise;
	}
	public Entreprise getIdEntreprise() {
		return idEntreprise;
	}
	public void setIdEtudiant(Etudiant idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public Etudiant getIdEtudiant() {
		return idEtudiant;
	}
   
}