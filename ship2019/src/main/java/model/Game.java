package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Game {

	private GameStatus gameStatus;
	private Map<String, Player> players;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private long gameTime = System.currentTimeMillis();

	public Game() {
		gameStatus = GameStatus.PREPAIR;
		players = new HashMap<String, Player>();
	}

	public long getGameTime() {
		return gameTime;
	}

	public String getGameTimeAsString() {
		Date date = new Date(gameTime);
		DateFormat formatter = new SimpleDateFormat("mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}

	public void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Map<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, Player> players) {
		this.players = players;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

}
