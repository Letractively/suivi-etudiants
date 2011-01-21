package personne;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Etudiant
 *
 */
@Entity

public class Etudiant extends Personne implements Serializable {

	   
	@Column(name="id_etudiant")
	private Integer idEtudiant;
	
	@Temporal(TemporalType.DATE)
	private Date datedenaissance;
	private String nationnalite;
	private Byte photoEtudiant;
	private static final long serialVersionUID = 1L;

	public Etudiant() {
		super();
	}   
	public Integer getIdEtudiant() {
		return this.idEtudiant;
	}

	public void setIdEtudiant(Integer idEtudiant) {
		this.idEtudiant = idEtudiant;
	}   
	public Date getDatedenaissance() {
		return this.datedenaissance;
	}

	public void setDatedenaissance(Date datedenaissance) {
		this.datedenaissance = datedenaissance;
	}   
	public String getNationnalite() {
		return this.nationnalite;
	}

	public void setNationnalite(String nationnalite) {
		this.nationnalite = nationnalite;
	}   
	public Byte getPhotoEtudiant() {
		return this.photoEtudiant;
	}

	public void setPhotoEtudiant(Byte photoEtudiant) {
		this.photoEtudiant = photoEtudiant;
	}
   
}