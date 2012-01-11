package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the TYPEFORMATION database table.
 * 
 */
@Entity
public class Typeformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idtypeformation;

	private String libelle;

	//bi-directional many-to-one association to Formation
	@OneToMany(mappedBy="typeformation")
	private Set<Formation> formations;

    public Typeformation() {
    }

	public long getIdtypeformation() {
		return this.idtypeformation;
	}

	public void setIdtypeformation(long idtypeformation) {
		this.idtypeformation = idtypeformation;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Formation> getFormations() {
		return this.formations;
	}

	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}
	
}