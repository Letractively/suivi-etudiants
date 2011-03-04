package fr.ua.iutlens.suivi.model.absence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.ua.iutlens.suivi.model.BaseEntity;
import org.eclipse.persistence.annotations.JoinFetch;
import static org.eclipse.persistence.annotations.JoinFetchType.OUTER;
import javax.persistence.Column;

/**
 * Entity implementation class for Entity: Coupon
 * 
 */
@Entity
public class Coupon extends BaseEntity implements Serializable {

	@Column(length=500)
	private String motif;
	@Column(length=500)
	private String justificatif;
	@OneToMany(mappedBy = "coupon") //clé étrangère pointant vers "absence"
	@JoinFetch(OUTER)
	private List<Absence> absences;
	private static final long serialVersionUID = 1L;

	public Coupon() {
		super();
	}

	//on récupère le motif de l'absence
	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	//on récupère le justificatif
	public String getJustificatif() {
		return this.justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	//On récupère la liste d'absences
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