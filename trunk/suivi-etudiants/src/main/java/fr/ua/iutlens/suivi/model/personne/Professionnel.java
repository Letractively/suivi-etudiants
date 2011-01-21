package personne;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import suiviPro.Entreprise;

/**
 * Entity implementation class for Entity: Professionnel
 *
 */
@Entity

public class Professionnel extends Personne implements Serializable {

	   
	@Column(name="id_professionnel")
	private Integer idProfessionnel;
	private String poste;
	private String service;
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false)
	@JoinColumn(name="id_entreprise",referencedColumnName="id_entreprise")
	private Entreprise idEntreprise;
	
	
	public Professionnel() {
		super();
	}   
	public Integer getIdProfessionnel() {
		return this.idProfessionnel;
	}

	public void setIdProfessionnel(Integer idProfessionnel) {
		this.idProfessionnel = idProfessionnel;
	}   
	public String getPoste() {
		return this.poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}   
	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}
	public void setIdEntreprise(Entreprise idEntreprise) {
		this.idEntreprise = idEntreprise;
	}
	public Entreprise getIdEntreprise() {
		return idEntreprise;
	}
   
}