package ejb.implement;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Etudiant;

@Remote
public interface EtudiantEJBInterface {
	List<Etudiant> findAllEtudiants();
	Etudiant createEtudiant(Etudiant etudiant);
	void removeEtudiant(Etudiant etudiant);
	void updateEtudiant(Etudiant etudiant);
	Etudiant findEtudiantById(Long id);
	List<Etudiant> findAllEtudiantsByEnt(Long ent);
	List<Etudiant> findAllEtudiantsActuel(Date dateActu, long idEtab);
	List<Etudiant> findAllEtudiantsByFormation(Long formation);

}
