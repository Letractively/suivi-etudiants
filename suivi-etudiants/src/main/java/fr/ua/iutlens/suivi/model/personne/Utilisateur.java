package personne;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Utilisateur
 *
 */
@Entity

public class Utilisateur implements Serializable {

	   
	@Id
	private String login;
	private String mdp;
	private Integer idPersUti;
	private String role;
	private static final long serialVersionUID = 1L;

	public Utilisateur() {
		super();
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}   
	public Integer getIdPersUti() {
		return this.idPersUti;
	}

	public void setIdPersUti(Integer idPersUti) {
		this.idPersUti = idPersUti;
	}
   
	public String getrole() {
		return this.role;
	}

	public void setrole(String role) {
		this.role = role;
	}   
}