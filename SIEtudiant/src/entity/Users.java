package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import static javax.persistence.InheritanceType.JOINED;

@Entity

//Inherite
//D�finit la strat�gie d'h�ritage � utiliser pour une hi�rarchie de classe d'entit�.
//Il est pr�cis� sur la classe d'entit� qui est la racine de la hi�rarchie des classes d'entit�.
//Si l'annotation h�ritage n'est pas sp�cifi� ou si aucun type d'h�ritage est sp�cifi� pour 
//une hi�rarchie de classe d'entit�, la strat�gie de mapping SINGLE_TABLE est utilis�.
@Inheritance(strategy=JOINED)
@Table(name="USERS")
public class Users implements Serializable {

     // champs nom, pr�nom de la personne et sa civilit�(non null)
    @Id
     private Long id;
    
     private String login;
     
     
     private String mdp;
     
    

     public Users() {
             super();
     }



	

 

	public Long getId() {
		return id;
	}







	public void setId(Long id) {
		this.id = id;
	}







	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getMdp() {
		return mdp;
	}



	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public void type() 
	{
	
	
	}
     
     

    
}