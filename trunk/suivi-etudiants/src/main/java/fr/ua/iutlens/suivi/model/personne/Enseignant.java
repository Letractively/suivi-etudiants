package personne;

import java.io.Serializable;
import java.lang.Byte;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Enseignant
 *
 */
@Entity

public class Enseignant extends Personne implements Serializable {

	   
	@Column(name="id_enseignant")
	private Integer idEnseignant;
	private Byte photoEnseignant;
	private static final long serialVersionUID = 1L;

	public Enseignant() {
		super();
	}   
	public Integer getIdEnseignant() {
		return this.idEnseignant;
	}

	public void setIdEnseignant(Integer idEnseignant) {
		this.idEnseignant = idEnseignant;
	}   
	public Byte getPhotoEnseignant() {
		return this.photoEnseignant;
	}

	public void setPhotoEnseignant(Byte photoEnseignant) {
		this.photoEnseignant = photoEnseignant;
	}
   
}