package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TYPEFORMATION database table.
 * 
 */
@Entity
public class Typeformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;

	private long libelle;

    public Typeformation() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLibelle() {
		return this.libelle;
	}

	public void setLibelle(long libelle) {
		this.libelle = libelle;
	}

}