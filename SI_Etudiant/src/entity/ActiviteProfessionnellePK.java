package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ACTIVITE_PROFESSIONNELLE database table.
 * 
 */
@Embeddable
public class ActiviteProfessionnellePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private long idactivitepro;

	private long idetudiant;

	private long identreprise;

    public ActiviteProfessionnellePK() {
    }
	public long getIdactivitepro() {
		return this.idactivitepro;
	}
	public void setIdactivitepro(long idactivitepro) {
		this.idactivitepro = idactivitepro;
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
		if (!(other instanceof ActiviteProfessionnellePK)) {
			return false;
		}
		ActiviteProfessionnellePK castOther = (ActiviteProfessionnellePK)other;
		return 
			(this.idactivitepro == castOther.idactivitepro)
			&& (this.idetudiant == castOther.idetudiant)
			&& (this.identreprise == castOther.identreprise);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idactivitepro ^ (this.idactivitepro >>> 32)));
		hash = hash * prime + ((int) (this.idetudiant ^ (this.idetudiant >>> 32)));
		hash = hash * prime + ((int) (this.identreprise ^ (this.identreprise >>> 32)));
		
		return hash;
    }
}