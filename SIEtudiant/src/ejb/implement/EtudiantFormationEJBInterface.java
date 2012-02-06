package ejb.implement;

import java.util.List;

import javax.ejb.Remote;

import entity.EtudiantFormation;

@Remote
public interface EtudiantFormationEJBInterface {
	List<EtudiantFormation> findAllEtudiantFormation();
	List<EtudiantFormation> findFormationsByStudentId(Long idEtudiant);
	EtudiantFormation createEtudiantFormation(EtudiantFormation etudiantFormation);
	void removeEtudiantFormation(EtudiantFormation etudiantFormation);
}
