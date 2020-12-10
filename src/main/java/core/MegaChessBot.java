package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import chessSet.Board;
import chessSet.Piece;
import chessSet.Piece.Color;
import chessSet.Turn;
import config.MegaChessBotConfig;
import handlers.ClientMessageHandler;
import response.GetChallengeResponse;
import response.GetGameOverResponse;
import response.GetYourTurnResponse;
import service.ChallengeAcceptanceService;
import service.ChallengeOpponentService;
import service.GameActionService;
import service.UserListService;

public class MegaChessBot extends TextWebSocketHandler{
	
	private WebSocketSession clientSession;	
	
	private ClientMessageHandler msgHandler;
	
	private int opponentsNumber;

	
	public MegaChessBot() {
		super();
		
		this.opponentsNumber = 0;
		StandardWebSocketClient webSocketClient = new StandardWebSocketClient();  
		
		this.msgHandler = new ClientMessageHandler();

		try {
			
			this.clientSession = webSocketClient.doHandshake(this, new WebSocketHttpHeaders(), 
					MegaChessBotConfig.getUri()).get();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	// Overrides

	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {        
        JsonObject json = Json.createReader(new StringReader(message.getPayload())).readObject();
        System.out.println("Mensaje entrante: " + message.getPayload());
        this.msgHandler.handleMessage(json, this);
    }	
	
	// Public
	
	public void sendTextMessage(String content) {    	
        try {
        	synchronized(this.clientSession) {
        		this.clientSession.sendMessage(new TextMessage(content));
    			System.out.println("Mensaje saliente: " + content);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	// Actions
	
	public void getUserList() {
		UserListService userListservice = new UserListService(this);
		userListservice.run();		
	}
	
	public void notifyUsersListUpdate(String[] usersList) {		
		String players = "";		
		for(int i = 0 ; i < usersList.length ; i++) {			
			players += usersList[i];			
			if(i != (usersList.length - 1)) {
				players += ", ";
			}
		}		
		System.out.println("Jugadores disponibles: " + players);
		
		if(this.opponentsNumber == 0) {
			this.chooseOpponent(usersList);
		}		
	}
	
	public void notifyChallenge(GetChallengeResponse challenge) {
		
		System.out.println("Acepto challenge de " + challenge.getUsername());	
//		System.out.println("El jugador " + challenge.getUsername() + " lo desafía. ¿Acepta el juego? S/N");
//		if(this.evaluateChallenge()) {
		if(challenge.getUsername().equalsIgnoreCase("SergioHR")) {
			ChallengeAcceptanceService challengeAcceptedService = new ChallengeAcceptanceService(this);	 
			HashMap<String,String> body = new HashMap<String,String>();
			body.put("board_id", challenge.getBoardId());
			challengeAcceptedService.setBody(body);
			challengeAcceptedService.run();
		}
	}
	
	public void makeMove(GetYourTurnResponse turnResponse) {
		Color color = turnResponse.actualTurn.equals("white") ? Piece.Color.WHITE : Piece.Color.BLACK;
		String boardLine = turnResponse.board;
		String turnToken = turnResponse.turnToken;
		Board board = this.createBoard(turnResponse.boardId, color, boardLine);		
		Turn turn = new Turn(turnToken, board, color);		
		GameActionService gameActionService = new GameActionService(this);	 
		HashMap<String, Object> nextMove = turn.getNextMove();
		gameActionService.setData(nextMove);
		gameActionService.run();
	}
	
	public void showEndGameResults(GetGameOverResponse gameOver) {
		System.out.println("Resultado final: \n" + gameOver );
	}
	
	// End Actions
	
	private boolean evaluateChallenge() {		
		String answer= "";
		
		while(!answer.equalsIgnoreCase("S") && !answer.equalsIgnoreCase("N")) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				answer = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return answer.equalsIgnoreCase("S");
	}
	
	private void chooseOpponent(String[] usersList) {		
		String answer= "";
//		boolean selected = false;
//		
//		System.out.println("Seleccione oponente: ");
//		while(!selected && !answer.equalsIgnoreCase("skip")) {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//			try {
//				answer = reader.readLine();				
//				for( int i = 0 ; i < usersList.length ; i ++ ) {
//					if( answer.equals(usersList[i]) ) selected = true;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
		ChallengeOpponentService challengeOpponentService = new ChallengeOpponentService(this);	 
		HashMap<String,String> body = new HashMap<String,String>();
		answer = "SergioHR"; 
		if(answer != "" && !answer.equalsIgnoreCase("skip")) {
			body.put("message", "¿Jugamos?");
			body.put("username", answer);		
			
			challengeOpponentService.setBody(body);
			challengeOpponentService.run();
			this.opponentsNumber ++;
		}		
	}
	
	private Board createBoard(String boardId, Color color, String boardLine) {
		Board board = new Board(boardId);				
		board.fillSpots(boardLine, color);
		return board;
	}
}
