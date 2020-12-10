# MegaChessBot
This is a version of a chess bot created with Java 1.8. It is a websocket client developed to play Mega Chess.

## General Description
The chess-bot starts a connection with an existing server that plays an intermediary role, keeping a connection with rival bots. The Mega Chess Bot receives incoming data that will be processed in order to create a suitable response and send it back to the server.
The bot makes moves based upon the level of potential scoring points. It also keeps an eye on threatening enemies to prevent them from taking its most valuable pieces. 

## Goals
* Receive and handle data from the websocket server
* Accept incoming challenges
* Process data in order to select a new move
* Test and validate piece movement
* Send next move to the server
* Keep track of the game status

## Known Issues
* The Mega Chess Bot logic does not have a proper way to evaluate best movement options when there are no possible enemy pieces to take. This leads to some predictable behavior, especially at the beginning of the game.
* Blocking movements have not been incorporated to the logic.
* The unit tests do not fully cover the project. The lack of time only allowed to test piece movement and decision making strategies, and there might be some unexpected behaviour as a result of this issue.
* Some pieces of code hold many task responsibilities.
* Inconsistency in the class structure. Some classes have getters and setters while some others don't. This is due to the little time left to finish the project.

## Pending Development
* More unit tests to cover at least 90% of the system
* Blocking moves
* Board position scoring, to better evaluate possible moves when there are no enemies to take
* Code improvement
