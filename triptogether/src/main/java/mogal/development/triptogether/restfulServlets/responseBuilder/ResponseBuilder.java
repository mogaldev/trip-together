package mogal.development.triptogether.restfulServlets.responseBuilder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ResponseBuilder {

	private static final String RESPONSE_CODE_KEY = "response_code";
	private static final String DATA_KEY = "data";
	private static final String ERROR_KEY = "error";
	private static final String RESPONSE_CODE_OK = "OK";
	private static final String RESPONSE_CODE_ERR = "ERR";
	
	public static Response generateResponseOK(Object data) {
		JsonObject generated = new JsonObject();
		generated.addProperty(RESPONSE_CODE_KEY, RESPONSE_CODE_OK);
		generated.add(ERROR_KEY, new JsonObject());
		
		if(data instanceof JsonElement) {
			JsonElement dataJsonElement = (JsonElement) data;
			generated.add(DATA_KEY, dataJsonElement);
		} else {
			generated.addProperty(DATA_KEY, data.toString());
		}
		
		return getApplicationJsonTypeResponse(generated);
	}
	
	public static Response generateResponseERR(Object error) {
		JsonObject generated = new JsonObject();
		generated.addProperty(RESPONSE_CODE_KEY, RESPONSE_CODE_ERR);
		generated.add(DATA_KEY, new JsonObject());
		
		if(error instanceof JsonElement) {
			JsonElement errorJsonElement = (JsonElement) error;
			generated.add(ERROR_KEY, errorJsonElement);
		} else {
			generated.addProperty(ERROR_KEY, error.toString());
		}
		
		return getApplicationJsonTypeResponse(generated);
	}
	
	private static Response getApplicationJsonTypeResponse(JsonElement jsonElement) {
		return Response.ok(jsonElement.toString()).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}