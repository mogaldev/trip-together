package mogal.development.triptogether.ejbs.users;

import java.util.List;

import javax.ejb.Local;

import mogal.development.triptogether.entities.User;

@Local
public interface UsersEjbLocal {
	public List<User> getUsers();
}
