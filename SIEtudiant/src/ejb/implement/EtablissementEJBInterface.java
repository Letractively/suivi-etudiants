package ejb.implement;

import java.util.List;

import javax.ejb.Remote;

import entity.Etablissement; 
@Remote
public interface EtablissementEJBInterface {
	List<Etablissement> findAllEtablissements();
	Etablissement createEtablissement(Etablissement etablissement);
	void removeEtablissement(Etablissement etablissement);
	void updateEtablissement(Etablissement etablissement);
	Etablissement findEtablissementById(Long id);
	 List<Etablissement> findAllEtablissementsByEtudiant(Long id);
}
