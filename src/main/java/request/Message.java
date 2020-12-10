package request;

import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Message {
private String action;
	
	private HashMap<String,String> data;

	public Message(String action, HashMap<String, String> data) {
		super();
		this.action = action;
		this.data = data;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}
	
	public String toJsonString() {		
		JsonObjectBuilder builder = Json.createObjectBuilder();
		this.getData().forEach(builder::add);
		JsonObject obj = builder.build();
		
		JsonObject value = Json.createObjectBuilder()
			     .add("action", this.getAction())
			     .add("data", obj).build();		
		
		return 	value.toString();
	}
}
