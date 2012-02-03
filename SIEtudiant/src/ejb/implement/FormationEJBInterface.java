package ejb.implement;

import java.util.List;

import javax.ejb.Remote;

import entity.Formation;
@Remote
public interface FormationEJBInterface {
	List<Formation> findAllFormations();
	Formation createFormation(Formation formation);
	void removeFormation(Formation formation);
	void updateFormation(Formation formation);
	Formation findFormationById(Long id);

}
