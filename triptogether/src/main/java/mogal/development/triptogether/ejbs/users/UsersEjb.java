package mogal.development.triptogether.ejbs.users;

import java.util.List;

import javax.ejb.Stateless;

import mogal.development.triptogether.ejbs.PersistenceEjb;
import mogal.development.triptogether.entities.User;

@Stateless
public class UsersEjb extends PersistenceEjb implements UsersEjbLocal {
    
    public List<User> getUsers() {
    	@SuppressWarnings("unchecked")
		List<User> users = entityManager.createNamedQuery("User.findAll").getResultList();
    	
    	return users;
    }
    
}
