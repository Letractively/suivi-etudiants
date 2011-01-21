package personne;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Personne
 *
 */
@MappedSuperclass
@Entity
public class Personne implements Serializable {

	   
	@Id 	
	@Column(name="id_personne")
	private Integer idPersonne;
	private String nomPersonne;
	private String prenomPersonne;
	private String civilite;
	private static final long serialVersionUID = 1L;

	public Personne() {
		super();
	}   
	public Integer getIdPersonne() {
		return this.idPersonne;
	}

	public void setIdPersonne(Integer idPersonne) {
		this.idPersonne = idPersonne;
	}   
	public String getNomPersonne() {
		return this.nomPersonne;
	}

	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}   
	public String getPrenomPersonne() {
		return this.prenomPersonne;
	}

	public void setPrenomPersonne(String prenomPersonne) {
		this.prenomPersonne = prenomPersonne;
	}   
	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
   
}