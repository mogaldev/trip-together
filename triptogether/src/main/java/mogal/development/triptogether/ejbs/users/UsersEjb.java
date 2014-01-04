package mogal.development.triptogether.ejbs.users;

import java.util.List;

import javax.ejb.Stateless;

import mogal.development.triptogether.ejbs.PersistenceEjb;
import mogal.development.triptogether.entities.User;

@Stateless
public class UsersEjb extends PersistenceEjb implements UsersEjbLocal {
    
    public List<User> getUsers() {
    	@SuppressWarnings("unchecked")
		List<User> users = em.createNamedQuery(User.NAMED_QUERY_FIND_ALL).getResultList();
    	
    	return users;
    }
    
    public User getUserById(Long userId) {
    	return em.find(User.class, userId);
    }
    
    public Long addUser(User newUser) {
    	em.persist(newUser);
    	return newUser.getId();
    }
    
    public void updateUser(User userToUpdate) {
    	em.merge(userToUpdate);
    }
    
    public void deleteUser(Long userId) {
    	User userById = getUserById(userId);
    	em.remove(userById);
    }
}
