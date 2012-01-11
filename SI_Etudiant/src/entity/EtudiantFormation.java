package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ETUDIANT_FORMATION database table.
 * 
 */
@Entity
@Table(name="ETUDIANT_FORMATION")
public class EtudiantFormation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	
	private EtudiantFormationPK id;

	//bi-directional many-to-one association to Etudiant
    @ManyToOne
	@JoinColumn(name="IDETUDIANT",nullable = false, insertable = false, updatable = false)
	private Etudiant etudiant;

	//bi-directional many-to-one association to Formation
    @ManyToOne
	@JoinColumn(name="IDFORMATION",nullable = false, insertable = false, updatable = false)
	private Formation formation;

    public EtudiantFormation() {
    }

	public EtudiantFormationPK getId() {
		return this.id;
	}

	public void setId(EtudiantFormationPK id) {
		this.id = id;
	}
	
	public Etudiant getEtudiant() {
		return this.etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	
	public Formation getFormation() {
		return this.formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
}