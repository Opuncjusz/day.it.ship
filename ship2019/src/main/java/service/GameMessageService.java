package service;

import org.java_websocket.WebSocket;

import websocket.to.GameMessage;

public interface GameMessageService {

	void dispatch(GameMessage message, WebSocket websocket);

}
