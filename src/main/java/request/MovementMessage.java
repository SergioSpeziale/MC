package request;

import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;

import org.json.JSONObject;

public class MovementMessage {
	private String action;
	
	private HashMap<String,Object> body;

	public MovementMessage(String action, HashMap<String, Object> body) {
		super();
		this.action = action;
		this.body = body;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public HashMap<String, Object> getbody() {
		return body;
	}

	public void setbody(HashMap<String, Object> body) {
		this.body = body;
	}
	
	public String toJsonString() {		
		JSONObject obj = new JSONObject(this.getbody());
		
		JsonObject value = Json.createObjectBuilder()
			     .add("action", this.getAction())
			     .add("data", obj.toString()).build();		
		
		return 	value.toString();
	}
}
