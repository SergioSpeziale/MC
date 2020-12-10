package service;

import java.util.HashMap;

import core.MegaChessBot;
import request.Message;

public class ChallengeAcceptanceService extends Service  {	
	private HashMap<String,String> body;
	
	public ChallengeAcceptanceService(MegaChessBot bot) {
		super(bot);
	}	
	
	public HashMap<String, String> getbody() {
		return body;
	}
	
	public void setBody(HashMap<String, String> body) {
		this.body = body;
	}

	@Override
	public void run() {
		Message message = new Message("accept_challenge", this.body);
		String content = message.toJsonString();		
		System.out.println("Veo mensaje de acceptacion de challenge " + content);
		this.bot.sendTextMessage(content);		
	}	
}
