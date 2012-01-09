package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the FORMATION database table.
 * 
 */
@Entity
public class Formation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private String etablissement;

	private String nom;

	private long typeformation;

	//bi-directional many-to-one association to EtudiantFormation
	@OneToMany(mappedBy="formation")
	private Set<EtudiantFormation> etudiantFormations;

    public Formation() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public long getTypeformation() {
		return this.typeformation;
	}

	public void setTypeformation(long typeformation) {
		this.typeformation = typeformation;
	}

	public Set<EtudiantFormation> getEtudiantFormations() {
		return this.etudiantFormations;
	}

	public void setEtudiantFormations(Set<EtudiantFormation> etudiantFormations) {
		this.etudiantFormations = etudiantFormations;
	}
	
}