package handlers;

import java.util.HashMap;

import javax.json.JsonObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.MegaChessBot;
import response.GetChallengeResponse;
import response.GetConnectedUsersResponse;
import response.GetYourTurnResponse;
import response.GetGameOverResponse;


public class ClientMessageHandler {
	private final HashMap<String, ClientMessageRunnable> taskList = new HashMap<>();

    public ClientMessageHandler() {

        this.populateTaskList();
    }

    private void populateTaskList() {

        taskList.put("update_user_list", new ClientMessageRunnable() {
            @Override
            public void run() {
            	ObjectMapper mapper = new ObjectMapper();
            	try {
					GetConnectedUsersResponse response = mapper.readValue(this.data.toString(), GetConnectedUsersResponse.class);
					this.bot.notifyUsersListUpdate(response.usersList);
            	} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
            }
        });
        
        taskList.put("ask_challenge", new ClientMessageRunnable() {
            @Override
            public void run() {
            	ObjectMapper mapper = new ObjectMapper();
            	try {
					GetChallengeResponse response = mapper.readValue(this.data.toString(), GetChallengeResponse.class);
					this.bot.notifyChallenge(response);
            	} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
            }
        });
        
        taskList.put("your_turn", new ClientMessageRunnable() {
            @Override
            public void run() {
            	ObjectMapper mapper = new ObjectMapper();
            	try {
					GetYourTurnResponse response = mapper.readValue(this.data.toString(), GetYourTurnResponse.class);
					System.out.println("Movimientos pendientes: "+ response.moveLeft + ". Mi color: "+ response.actualTurn);
					System.out.println("________________");
					for(int i = 1; i < 257 ; i ++) {
						System.out.print(response.board.charAt(i - 1));
						if(i%16 == 0) {
							System.out.println("");
						}
					}
					System.out.println("________________");

					this.bot.makeMove(response);
            	} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
            }
        });
        
        taskList.put("gameover", new ClientMessageRunnable() {
            @Override
            public void run() {
            	ObjectMapper mapper = new ObjectMapper();
            	try {
            		GetGameOverResponse response = mapper.readValue(this.data.toString(), GetGameOverResponse.class);
					this.bot.showEndGameResults(response);
            	} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
            }
        });
        
        taskList.put("response_error", new ClientMessageRunnable() {
            @Override
            public void run() {
            	try {
            		System.out.println("msg error: " + this.data.toString());
            	} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
    }	   
	
	
	// Public

	public void handleMessage(JsonObject json, MegaChessBot bot) {		
		String event = json.getString("event");
		JsonObject data = json.get("data").asJsonObject();
		System.out.println("Handler message: " + event);
		taskList.get(event).execute(data, bot);	
	}
}



class ClientMessageRunnable implements Runnable {	
	protected JsonObject data;
	protected MegaChessBot bot;
	
	public ClientMessageRunnable() {}

	@Override
	public void run() {}
	
	public void execute(JsonObject data, MegaChessBot bot) {
		this.data = data;
		this.bot = bot;
		this.run();
	}	
}
