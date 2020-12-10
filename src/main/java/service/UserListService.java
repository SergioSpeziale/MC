package service;

import java.util.HashMap;

import core.MegaChessBot;
import request.Message;

public class UserListService extends Service {
	
	public UserListService(MegaChessBot bot) {
		super(bot);
	}
	
	@Override
	public void run() {
		Message message = new Message("get_connected_users", new HashMap<String,String>());
		String content = message.toJsonString();		
		
		this.bot.sendTextMessage(content);		
	}
}
