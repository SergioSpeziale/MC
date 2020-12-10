package service;

import java.util.HashMap;

import core.MegaChessBot;
import request.Message;

public class ChallengeOpponentService extends Service {

	private HashMap<String,String> body;
	
	public ChallengeOpponentService(MegaChessBot bot) {
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
		Message message = new Message("challenge", this.body);
		String content = message.toJsonString();		
		
		this.bot.sendTextMessage(content);		
	}
}
