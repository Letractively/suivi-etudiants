package note;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: UE
 *
 */
@Entity

public class UE implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name ="id_UE")
	private int idUE;
	@Column(name ="libelle_UE")
	private String libelleUE;
	@Column(name ="coefUE_UE")
	private int coefUE;
	@ManyToOne
	@JoinColumn(name="id_etape",  referencedColumnName = "id_etape")
	private Etape idEtape;
	private static final long serialVersionUID = 1L;

	public UE() {
		super();
	}   
	public int getIdUE() {
		return this.idUE;
	}

	public void setIdUE(int idUE) {
		this.idUE = idUE;
	}   
	public String getLibelleUE() {
		return this.libelleUE;
	}

	public void setLibelleUE(String libelleUE) {
		this.libelleUE = libelleUE;
	}   
	public int getCoef() {
		return this.coefUE;
	}

	public void setCoef(int coefUE) {
		this.coefUE = coefUE;
	}
	public void setIdEtape(Etape idEtape) {
		this.idEtape = idEtape;
	}
	public Etape getIdEtape() {
		return idEtape;
	}
   
}
