package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import static javax.persistence.InheritanceType.JOINED;

@Entity

//Inherite
//Définit la stratégie d'héritage à utiliser pour une hiérarchie de classe d'entité.
//Il est précisé sur la classe d'entité qui est la racine de la hiérarchie des classes d'entité.
//Si l'annotation héritage n'est pas spécifié ou si aucun type d'héritage est spécifié pour 
//une hiérarchie de classe d'entité, la stratégie de mapping SINGLE_TABLE est utilisé.
@Inheritance(strategy=JOINED)
@Table(name="USERS")
public class Users implements Serializable {

     // champs nom, prénom de la personne et sa civilité(non null)
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