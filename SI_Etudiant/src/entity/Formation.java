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
	private long idformation;

	private String etablissement;

	private String nomformation;

	//bi-directional many-to-one association to EtudiantFormation
	@OneToMany(mappedBy="formation")
	private Set<EtudiantFormation> etudiantFormations;

	//bi-directional many-to-one association to Typeformation
    @ManyToOne
	@JoinColumn(name="IDTYPEFORMATION")
	private Typeformation typeformation;

    public Formation() {
    }

	public long getIdformation() {
		return this.idformation;
	}

	public void setIdformation(long idformation) {
		this.idformation = idformation;
	}

	public String getEtablissement() {
		return this.etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getNomformation() {
		return this.nomformation;
	}

	public void setNomformation(String nomformation) {
		this.nomformation = nomformation;
	}

	public Set<EtudiantFormation> getEtudiantFormations() {
		return this.etudiantFormations;
	}

	public void setEtudiantFormations(Set<EtudiantFormation> etudiantFormations) {
		this.etudiantFormations = etudiantFormations;
	}
	
	public Typeformation getTypeformation() {
		return this.typeformation;
	}

	public void setTypeformation(Typeformation typeformation) {
		this.typeformation = typeformation;
	}
	
}