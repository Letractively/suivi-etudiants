package note;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Etape
 *
 */
@Entity

public class Etape implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_etape")
	private int idEtape;
	@Column(name = "type_etape")	
	private String typeEtape;
	@Column(name = "libelle_etape")
	private String libelleEtape;
	private static final long serialVersionUID = 1L;

	public Etape() {
		super();
	}   
	public Integer getIdEtape() {
		return this.idEtape;
	}

	public void setIdEtape(Integer idEtape) {
		this.idEtape = idEtape;
	}   
	public String getTypeEtape() {
		return this.typeEtape;
	}

	public void setTypeEtape(String typeEtape) {
		this.typeEtape = typeEtape;
	}   
	public String getLibelleEtape() {
		return this.libelleEtape;
	}

	public void setLibelleEtape(String libelleEtape) {
		this.libelleEtape = libelleEtape;
	}
   
}
