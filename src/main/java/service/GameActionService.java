package service;

import java.util.HashMap;

import core.MegaChessBot;
import request.MovementMessage;

public class GameActionService extends Service {
	private HashMap<String,Object> data;

	public GameActionService(MegaChessBot bot) {
		super(bot);
		// TODO Auto-generated constructor stub
	}
	
	public HashMap<String, Object> getData() {
		return data;
	}
	
	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	@Override
	public void run() {
		MovementMessage movementReq = new MovementMessage("move", this.data);
		String content = movementReq.toJsonString();		
		//content = content.replace('\\', Character.MIN_VALUE);
		content = content.replaceAll("\\\\", "");

		content = content.replaceAll("\"\\{","{");
		content = content.replaceAll("\\}\"", "}");
		content = content.replaceAll("\\s+", "");
		
//		content = content.replaceAll("\"{", "{");
//		content = content.replaceAll("}\"", "}");
		this.bot.sendTextMessage(content);		
	}
}
