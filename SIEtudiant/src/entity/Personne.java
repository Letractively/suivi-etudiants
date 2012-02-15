package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;



import javax.persistence.OneToMany;

@Entity
//Inherite
//Définit la stratégie d'héritage à utiliser pour une hiérarchie de classe d'entité.
//Il est précisé sur la classe d'entité qui est la racine de la hiérarchie des classes d'entité.
//Si l'annotation héritage n'est pas spécifié ou si aucun type d'héritage est spécifié pour 
//une hiérarchie de classe d'entité, la stratégie de mapping SINGLE_TABLE est utilisé.
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {

     // champs nom, prénom de la personne et sa civilité(non null)
	
	private static final long serialVersionUID = 1L;
    
	 private Long id;
	
     private String nom;
    
     private String prenom;
    
         

     // une personne peut être plusieurs utilisateurs différents
 

     // une personne est rédacteur de 0 à n suivi de stages
     

     public Personne() {
             super();
     }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	 @Id
	public Long getId() {
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}
	
	
     
     

     

}