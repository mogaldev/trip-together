package mogal.development.triptogether.restfulServlets;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	private UsersEjbLocal ejb;

	@GET
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
	
	@GET
	@Path("/{userId}")
	public Response getAllUsers(@PathParam("userId") Long userId) {		
		User selectedUser = ejb.getUserById(userId);

		return ResponseBuilder.generateResponseOK(getUserAsJson(selectedUser));
	}
	
	@POST
	public Response createUser(	@FormParam("firstName") String firstName,
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
	
	@PUT
	@Path("/{userId}")
	public Response updateUser(	@PathParam("userId") Long userId,
								@FormParam("firstName") String firstName,
								@FormParam("lastName") String lastName,
								@FormParam("email") String email,
								@FormParam("imageUrl") String imageUrl,
								@FormParam("birthDate") String birthDate,
								@FormParam("city") String city) {
		// Get the user to update from DB
		User userById = ejb.getUserById(userId);
		
		if(userById == null) {
			return ResponseBuilder.generateResponseERR("User not found");
		}

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
	
	@DELETE
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") Long userId) {
		ejb.deleteUser(userId);
		
		return ResponseBuilder.generateResponseOK(null);
	}
	
	@DELETE
	@Path("/all")
	public Response deleteAllUsers() {
		ejb.deleteAll();
		
		return ResponseBuilder.generateResponseOK(null);
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