package suiviPro;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Entreprise
 *
 */
@Entity

public class Entreprise implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_Entreprise")
	private int idEntreprise;
	
	@Column(name = "nom_Entreprise")
	private String nomEntreprise;
	
	private String raisonSociale;
	
	private String secteurActivite;
		
	private static final long serialVersionUID = 1L;

	
	
	public Entreprise() {
		super();
	}   
	public Integer getIdEntreprise() {
		return this.idEntreprise;
	}

	public void setIdEntreprise(Integer idEntreprise) {
		this.idEntreprise = idEntreprise;
	}   
	public String getNomEntreprise() {
		return this.nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}   
	public String getRaisonSociale() {
		return this.raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}   
	public String getSecteurActivite() {
		return this.secteurActivite;
	}

	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}
   
}