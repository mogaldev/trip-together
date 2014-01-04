package mogal.development.triptogether.restfulServlets;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import mogal.development.triptogether.ejbs.PersistenceEjbLocal;
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
	PersistenceEjbLocal ejb;

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
	
	private JsonObject getUserAsJson(User user) {
		JsonObject json = new JsonObject();
		json.addProperty("Id", user.getId());
		json.addProperty("FirstName", user.getFirstName());
		json.addProperty("LastName", user.getLastName());
		json.addProperty("Email", user.getEmail());
		json.addProperty("ImageUrl", user.getImageUrl());
		json.addProperty("BirthDate", DateUtils.getDateFormat(user.getBirthDate()));
		json.addProperty("City", Converter.nvl(user.getCity()));
		json.addProperty("IsLogedIn", user.getIsLogedIn());
		
		return json;
	}
	
}