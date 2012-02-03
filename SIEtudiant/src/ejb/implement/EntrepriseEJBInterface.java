package ejb.implement;

import java.util.List;

import javax.ejb.Remote;

import entity.Entreprise;
@Remote
public interface EntrepriseEJBInterface {
	List<Entreprise> findAllEntreprises();
	Entreprise createEntreprise(Entreprise entreprise);
	void removeEntreprise(Entreprise entreprise);
	void updateEntreprise(Entreprise entreprise);
	Entreprise findEntrepriseById(Long id);
}
