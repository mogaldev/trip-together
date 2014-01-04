package mogal.development.triptogether.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mogal.development.triptogether.entities.User;

/**
 * Session Bean implementation class PersistenceEjb
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class PersistenceEjb implements PersistenceEjbLocal {
	
	@PersistenceContext(unitName="TripTogetherPersistence")
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public PersistenceEjb() {
    }
    
    public List<User> getUsers() {
    	@SuppressWarnings("unchecked")
		List<User> users = entityManager.createNamedQuery("User.findAll").getResultList();
    	
    	return users;
    }
    
}
