package service;

import core.MegaChessBot;

public interface ServiceInterface {	
	public void run() throws Exception;
}

class Service implements ServiceInterface {
	MegaChessBot bot;
	
	public Service(MegaChessBot bot) {
		this.bot = bot;
	}

	@Override
	public void run() throws Exception {
		throw new Exception("run() must be implemented by subclasses");
		
	}
}