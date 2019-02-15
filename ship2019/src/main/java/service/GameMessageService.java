package service;

import websocket.to.GameMessage;

public interface GameMessageService {

	void dispatch(GameMessage message);

}
