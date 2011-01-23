package fr.ua.iutlens.suivi.model.absence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;

/**
 * Entity implementation class for Entity: Coupon
 * 
 */
@Entity
public class Coupon extends BaseEntity implements Serializable {

	private String motif;
	private String justificatif;
	@OneToMany(mappedBy = "coupon")
	private List<Absence> absences;
	private static final long serialVersionUID = 1L;

	public Coupon() {
		super();
	}

	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getJustificatif() {
		return this.justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	@Override
	public String getDisplayText() {
		return motif;
	}

}