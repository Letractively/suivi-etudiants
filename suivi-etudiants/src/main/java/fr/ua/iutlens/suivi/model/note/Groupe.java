package note;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Groupe
 *
 */
@Entity

public class Groupe implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_groupe")
	private int idGroupe;
	@Column(name = "nom_groupe")
	private String nomGroupe;
	private static final long serialVersionUID = 1L;

	public Groupe() {
		super();
	}   
	public int getIdGroupe() {
		return this.idGroupe;
	}

	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}   
	public String getNomGroupe() {
		return this.nomGroupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
   
}
