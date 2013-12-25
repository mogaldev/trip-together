package mogal.development.triptogether.restfulServlets;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {

	@GET
	public Response sayHelloWorld() {
		return getApplicationJsonTypeResponse("Hello world!!");
	}
	
	@POST
	public Response sayHelloWorldInPost() {
		return getApplicationJsonTypeResponse("Hello world in post!!");
	}

	@GET
	@Path("/israel")
	public Response sayHelloIsrael() {
		return getApplicationJsonTypeResponse("<h1>Hello Israel!! <3</h1>");
	}

	@GET
	@Path("/{country}")
	public Response sayHelloCountry(@PathParam("country") String country) {
		return getApplicationJsonTypeResponse("Hello " + country + "!!!");
	}
	
	@GET
	@Path("/getTime")
	public String getTime() {
		return new Date().toString();
	}
	
	private Response getApplicationJsonTypeResponse(String string) {
		return Response.ok(string).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}