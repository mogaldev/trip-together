package mogal.development.triptogether.ejbs;

import java.util.List;

import javax.ejb.Local;

import mogal.development.triptogether.entities.Product;

@Local
public interface PersistenceEjbLocal {
	public List<Product> getProducts();
}
