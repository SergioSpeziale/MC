package response;

import com.fasterxml.jackson.annotation.JsonProperty;



public class GetConnectedUsersResponse {

	@JsonProperty("users_list")
	public String[] usersList;
}
