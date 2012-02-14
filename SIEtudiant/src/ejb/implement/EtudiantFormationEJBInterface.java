package ejb.implement;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.EtudiantFormation;

@Remote
public interface EtudiantFormationEJBInterface {
	List<EtudiantFormation> findAllEtudiantFormation();
	List<EtudiantFormation> findFormationsByStudentId(Long idEtudiant,long etab);
	List<EtudiantFormation> findFormationsUlterieureByStudentId(Long idEtudiant, long etab);
	List<EtudiantFormation> findFormationsAnterieuresByStudentId(Long idEtudiant, long etab);
	List<EtudiantFormation> findAllFormationsByStudentId(Long idEtudiant);
	EtudiantFormation createEtudiantFormation(EtudiantFormation etudiantFormation);
	void removeEtudiantFormation(EtudiantFormation etudiantFormation);
}
