package note;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: Etablissement
 *
 */
@Entity

public class Etablissement implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_Etablissement")
	private int idEtablissement;
	private String type;
	@Column(name = "nom_etablissement")
	private String nomEtablissement;
	@ManyToMany(mappedBy = "idEtablissement")
	private List<Formation> idFormation;
	private static final long serialVersionUID = 1L;

	public Etablissement() {
		super();
	}   
	public Integer getIdEtablissement() {
		return this.idEtablissement;
	}

	public void setIdEtablissement(Integer idEtablissement) {
		this.idEtablissement = idEtablissement;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getNomEtablissement() {
		return this.nomEtablissement;
	}

	public void setNomEtablissement(String nomEtablissement) {
		this.nomEtablissement = nomEtablissement;
	}
	public void setIdFormation(List<Formation> idFormation) {
		this.idFormation = idFormation;
	}
	public List<Formation> getIdFormation() {
		return idFormation;
	}
   
}
