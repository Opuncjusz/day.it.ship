package com.rd.accelerometer.client;

import com.rd.accelerometer.websocket.converter.GameMessageConverter;
import com.rd.accelerometer.websocket.to.GameMessage;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connector {

    private static Logger LOGGER = LoggerFactory.getLogger(Connector.class);

    private WebSocketClient websocket;

    public boolean run(String uri) throws URISyntaxException {
        System.out.println("Lacze z ... " + uri);

        websocket = new WebSocketClient(new URI(uri)) {

            @Override
            public void onMessage(String message) {
                LOGGER.info("got: " + message + "\n");
            }

            @Override
            public void onOpen(ServerHandshake handshake) {
                LOGGER.info("You are connected to server: " + getURI() + "\n");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                LOGGER.warn("You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason + "\n");
            }

            @Override
            public void onError(Exception ex) {
                LOGGER.error("Exception occured ...\n" + ex + "\n");
            }
        };

        websocket.connect();


        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return websocket.isOpen();
    }

    public boolean isOpen() {
        return websocket.isOpen();
    }

    public void send(GameMessage message) {
        send(GameMessageConverter.fromGameMessage(message));
    }

    public void send(String content) {
        websocket.send(content);
    }

    public void close() {websocket.close();}
}
