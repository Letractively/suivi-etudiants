package note;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Matiere
 *
 */
@Entity

public class Matiere implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_matiere")
	private int idMatiere;
	@Column(name = "libelle_matiere")
	private String libelleMatiere;
	@Column(name = "coef_matiere")
	private int coefMatiere;
	@ManyToOne
	@JoinColumn(name="id_UE",  referencedColumnName = "id_UE")
	private UE idUE;
	private static final long serialVersionUID = 1L;

	public Matiere() {
		super();
	}   
	public int getIdMatiere() {
		return this.idMatiere;
	}

	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}   
	public String getLibelleMatiere() {
		return this.libelleMatiere;
	}

	public void setLibelleMatiere(String libelleMatiere) {
		this.libelleMatiere = libelleMatiere;
	}   
	public int getCoefMatiere() {
		return this.coefMatiere;
	}

	public void setCoefMatiere(int coefMatiere) {
		this.coefMatiere = coefMatiere;
	}   
	public UE getIdUE() {
		return this.idUE;
	}

	public void setIdUE(UE idUE) {
		this.idUE = idUE;
	}
   
}
