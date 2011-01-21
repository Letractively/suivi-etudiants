package absence;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import personne.Etudiant;

/**
 * Entity implementation class for Entity: Absence
 *
 */
@Entity

public class Absence implements Serializable {

	   
	@Id
	@Column(name = "id_absence")
	private Integer idAbsence;
	private String nature;
	private String justifie;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_coupon", referencedColumnName = "id_coupon")
	private Coupon idCoupon;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_etudiant", referencedColumnName = "id_etudiant")
	private Etudiant idEtudiant;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_seance", referencedColumnName = "id_seance")
	private Seance idSeance;
	 
	
	
	public Absence() {
		super();
	}   
	public Integer getIdAbsence() {
		return this.idAbsence;
	}

	public void setIdAbsence(Integer idAbsence) {
		this.idAbsence = idAbsence;
	}   
	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}   
	public String getJustifie() {
		return this.justifie;
	}

	public void setJustifie(String justifie) {
		this.justifie = justifie;
	}
	public void setIdCoupon(Coupon idCoupon) {
		this.idCoupon = idCoupon;
	}
	public Coupon getIdCoupon() {
		return idCoupon;
	}
	public void setIdEtudiant(Etudiant idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public Etudiant getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdSeance(Seance idSeance) {
		this.idSeance = idSeance;
	}
	public Seance getIdSeance() {
		return idSeance;
	}
   
}