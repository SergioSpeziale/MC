package response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetYourTurnResponse {
	@JsonProperty("board_id")
	public String boardId;
	
	@JsonProperty("turn_token")
	public String turnToken;
	
	@JsonProperty("username")
	public String username;
	
	@JsonProperty("actual_turn")
	public String actualTurn;
	
	@JsonProperty("board")
	public String board;
	
	@JsonProperty("move_left")
	public String moveLeft;
	
	@JsonProperty("opponent_username")
	public String opponentUsername;
	
	
}
