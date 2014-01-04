package mogal.development.triptogether.restfulServlets;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import mogal.development.triptogether.ejbs.users.UsersEjbLocal;
import mogal.development.triptogether.entities.User;
import mogal.development.triptogether.restfulServlets.responseBuilder.ResponseBuilder;
import mogal.development.triptogether.utilities.Converter;
import mogal.development.triptogether.utilities.DateUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Stateless
@Path("/users")
public class UsersService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsersEjbLocal ejb;

	@GET
	@Path("/get-all-users")
	public Response getAllUsers() {		
		// Get all users
		List<User> users = ejb.getUsers();

		// Json array that will contain all the Users as JsonObjects
		JsonArray jsonArray = new JsonArray();
		for (User user : users) {
			jsonArray.add(getUserAsJson(user));
		}
		
		return ResponseBuilder.generateResponseOK(jsonArray);
	}
	
	@POST
	@Path("/register-user")
	public Response registerUser(	@FormParam("firstName") String firstName,
									@FormParam("lastName") String lastName,
									@FormParam("email") String email,
									@FormParam("imageUrl") String imageUrl,
									@FormParam("birthDate") String birthDate,
									@FormParam("city") String city) {
		User newUser = new User();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setImageUrl(imageUrl);
		newUser.setBirthDate(DateUtils.getStringFromDate(birthDate));
		newUser.setCity(city);
		Long userId = ejb.addUser(newUser);

		return ResponseBuilder.generateResponseOK(userId);
	}
	
	@POST
	@Path("/update-user")
	public Response updateUser(	@FormParam("userId") Long userId,
								@FormParam("firstName") String firstName,
								@FormParam("lastName") String lastName,
								@FormParam("email") String email,
								@FormParam("imageUrl") String imageUrl,
								@FormParam("birthDate") String birthDate,
								@FormParam("city") String city) {
		// Get the user to update from DB
		User userById = ejb.getUserById(userId);

		// Update the user data
		userById.setFirstName(firstName);
		userById.setLastName(lastName);
		userById.setEmail(email);
		userById.setImageUrl(imageUrl);
		userById.setBirthDate(DateUtils.getStringFromDate(birthDate));
		userById.setCity(city);

		// Update the User in DB
		ejb.updateUser(userById);
		
		return ResponseBuilder.generateResponseOK(userById.getId());
	}
	
	private JsonObject getUserAsJson(User user) {
		JsonObject json = new JsonObject();
		json.addProperty("Id", user.getId());
		json.addProperty("FirstName", user.getFirstName());
		json.addProperty("LastName", user.getLastName());
		json.addProperty("Email", user.getEmail());
		json.addProperty("ImageUrl", user.getImageUrl());
		json.addProperty("BirthDate", DateUtils.getDateFromString(user.getBirthDate()));
		json.addProperty("City", Converter.nvl(user.getCity()));
		json.addProperty("IsLogedIn", user.getIsLogedIn());
		
		return json;
	}
	
}