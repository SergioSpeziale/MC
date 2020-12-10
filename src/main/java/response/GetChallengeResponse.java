package response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetChallengeResponse {

	@JsonProperty("username")
	public String username;
	
	@JsonProperty("board_id")
	public String boardId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	
}
