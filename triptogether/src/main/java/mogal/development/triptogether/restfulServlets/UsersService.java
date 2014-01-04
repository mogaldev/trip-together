package mogal.development.triptogether.restfulServlets;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mogal.development.triptogether.ejbs.PersistenceEjbLocal;
import mogal.development.triptogether.entities.User;
import mogal.development.triptogether.utilities.Converter;
import mogal.development.triptogether.utilities.DateUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Path("/user")
public class UsersService {

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
		
		return getApplicationJsonTypeResponse(jsonArray);
	}
	
	private Response getApplicationJsonTypeResponse(JsonElement jsonElement) {
		return Response.ok(jsonElement).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
	
	private JsonObject getUserAsJson(User user) {
		JsonObject json = new JsonObject();
		json.addProperty("Id", user.getId());
		json.addProperty("UserName", user.getUserName());
		json.addProperty("Email", user.getEmail());
		json.addProperty("BirthDate", DateUtils.getDateFormat(user.getBirthDate()));
		json.addProperty("City", Converter.nvl(user.getCity()));
		json.addProperty("IsLogedIn", user.getIsLogedIn());
		
		return json;
	}
}