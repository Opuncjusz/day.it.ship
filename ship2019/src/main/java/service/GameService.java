package service;

import websocket.to.GameMessage;

interface GameService {

	void setSpeed(GameMessage message);

	void startConnectionWithCar();

	void sendAdminInfo();

}
