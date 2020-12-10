package response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetGameOverResponse {
	@JsonProperty("board")
	public String board;
	
	@JsonProperty("board_id")
	public String boardId;
	
	@JsonProperty("white_username")
	public String whiteUsername;
	
	@JsonProperty("black_username")
	public String blackUsername;
	
	@JsonProperty("white_score")
	public String whiteScore;
	
	@JsonProperty("black_score")
	public String blackScore;
}
