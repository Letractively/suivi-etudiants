package note;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: Formation
 *
 */
@Entity

public class Formation implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_formation")
	private int idFormation;
	private String type;
	@Column(name = "libelle_formation")
	private String libelleFormation;
	private static final long serialVersionUID = 1L;
	@ManyToMany
	@JoinTable(name = "formation_etudiant",
		joinColumns = @JoinColumn(name = "id_formation"),
		inverseJoinColumns = @JoinColumn(name = "id_etablissement"))
	private List<Etablissement> idEtablissement;

	public Formation() {
		super();
	}   
	public Integer getIdFormation() {
		return this.idFormation;
	}

	public void setIdFormation(Integer idFormation) {
		this.idFormation = idFormation;
	}   
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}   
	public String getLibelleFormation() {
		return this.libelleFormation;
	}

	public void setLibelleFormation(String libelleFormation) {
		this.libelleFormation = libelleFormation;
	}
	public void setIdEtablissement(List<Etablissement> idEtablissement) {
		this.idEtablissement = idEtablissement;
	}
	public List<Etablissement> getIdEtablissement() {
		return idEtablissement;
	}
   
}
