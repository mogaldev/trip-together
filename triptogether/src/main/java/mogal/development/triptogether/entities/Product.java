package mogal.development.triptogether.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "image_credits_url")
	private String imageCreditsUrl;

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageCreditsUrl() {
		return imageCreditsUrl;
	}

	public void setImageCreditsUrl(String imageCreditsUrl) {
		this.imageCreditsUrl = imageCreditsUrl;
	}

}