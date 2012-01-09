package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ETUDIANT_FORMATION database table.
 * 
 */
@Embeddable
public class EtudiantFormationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long idetudiant;

	private long idformation;

	private String annee;

    public EtudiantFormationPK() {
    }
	public long getIdetudiant() {
		return this.idetudiant;
	}
	public void setIdetudiant(long idetudiant) {
		this.idetudiant = idetudiant;
	}
	public long getIdformation() {
		return this.idformation;
	}
	public void setIdformation(long idformation) {
		this.idformation = idformation;
	}
	public String getAnnee() {
		return this.annee;
	}
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EtudiantFormationPK)) {
			return false;
		}
		EtudiantFormationPK castOther = (EtudiantFormationPK)other;
		return 
			(this.idetudiant == castOther.idetudiant)
			&& (this.idformation == castOther.idformation)
			&& this.annee.equals(castOther.annee);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idetudiant ^ (this.idetudiant >>> 32)));
		hash = hash * prime + ((int) (this.idformation ^ (this.idformation >>> 32)));
		hash = hash * prime + this.annee.hashCode();
		
		return hash;
    }
}