package ejbInterface;

import java.util.List;

import javax.ejb.Remote;

import entity.Etudiant;

@Remote
public interface EtudiantEJBInterface {
	public List<Etudiant> findAllEtudiants();
	public Etudiant createEtudiant(Etudiant etudiant);
	public void removeEtudiant(Etudiant etudiant);
	public void updateEtudiant(Etudiant etudiant);
	public Etudiant findEtudiantById(Long id);
	public List<Etudiant> findAllEtudiantsByEnt(Long ent);

}
