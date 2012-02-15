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
//D�finit la strat�gie d'h�ritage � utiliser pour une hi�rarchie de classe d'entit�.
//Il est pr�cis� sur la classe d'entit� qui est la racine de la hi�rarchie des classes d'entit�.
//Si l'annotation h�ritage n'est pas sp�cifi� ou si aucun type d'h�ritage est sp�cifi� pour 
//une hi�rarchie de classe d'entit�, la strat�gie de mapping SINGLE_TABLE est utilis�.
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne implements Serializable {

     // champs nom, pr�nom de la personne et sa civilit�(non null)
	
	private static final long serialVersionUID = 1L;
    
	 private Long id;
	
     private String nom;
    
     private String prenom;
    
         

     // une personne peut �tre plusieurs utilisateurs diff�rents
 

     // une personne est r�dacteur de 0 � n suivi de stages
     

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