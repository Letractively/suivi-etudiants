package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ETUDIANT_ENTREPRISE database table.
 * 
 */
@Embeddable
public class EtudiantEntreprisePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long id;

	private long idetudiant;

	private long identreprise;

    public EtudiantEntreprisePK() {
    }
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdetudiant() {
		return this.idetudiant;
	}
	public void setIdetudiant(long idetudiant) {
		this.idetudiant = idetudiant;
	}
	public long getIdentreprise() {
		return this.identreprise;
	}
	public void setIdentreprise(long identreprise) {
		this.identreprise = identreprise;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EtudiantEntreprisePK)) {
			return false;
		}
		EtudiantEntreprisePK castOther = (EtudiantEntreprisePK)other;
		return 
			(this.id == castOther.id)
			&& (this.idetudiant == castOther.idetudiant)
			&& (this.identreprise == castOther.identreprise);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.id ^ (this.id >>> 32)));
		hash = hash * prime + ((int) (this.idetudiant ^ (this.idetudiant >>> 32)));
		hash = hash * prime + ((int) (this.identreprise ^ (this.identreprise >>> 32)));
		
		return hash;
    }
}