package mogal.development.triptogether.ejbs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class PersistenceEjb implements PersistenceEjbLocal {
	
	@PersistenceContext(unitName="TripTogetherPersistence")
	protected EntityManager entityManager;
	
}
