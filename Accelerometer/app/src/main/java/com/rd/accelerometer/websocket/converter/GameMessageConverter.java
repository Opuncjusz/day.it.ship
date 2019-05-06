package com.rd.accelerometer.websocket.converter;

import com.google.gson.Gson;
import com.rd.accelerometer.websocket.to.GameMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameMessageConverter {

    private static Logger LOGGER = LoggerFactory.getLogger(GameMessageConverter.class);

    public static GameMessage getGameMessage(String content) {
        GameMessage fromJson = new Gson().fromJson(content, GameMessage.class);

        LOGGER.debug(fromJson.toString());

        return fromJson;
    }

    public static String fromGameMessage(GameMessage content) {
        return new Gson().toJson(content);
    }

}