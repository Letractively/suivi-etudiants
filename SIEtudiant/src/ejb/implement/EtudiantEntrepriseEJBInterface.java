package ejb.implement;

import java.util.List;

import javax.ejb.Remote;

import entity.EtudiantEntreprise;
@Remote
public interface EtudiantEntrepriseEJBInterface {
	List<EtudiantEntreprise> findAllEtudiantEntreprise();
	List<EtudiantEntreprise> findCompaniesByStudentId(Long idEtudiant);
	EtudiantEntreprise createEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise);
	void removeEtudiantEntreprise(EtudiantEntreprise etudiantEntreprise);
}
