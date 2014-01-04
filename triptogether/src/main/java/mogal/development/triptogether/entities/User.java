package mogal.development.triptogether.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
@NamedQuery(name = User.NAMED_QUERY_FIND_ALL, query = "SELECT p FROM User p")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String NAMED_QUERY_FIND_ALL = "User.findAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;

	@Column(name="image_url")
	private String imageUrl;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="city")
	private String city;
	
	@Column(name="is_loged_in")
	private Boolean isLogedIn;
	
	public User() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getIsLogedIn() {
		return isLogedIn;
	}

	public void setIsLogedIn(Boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}
}
