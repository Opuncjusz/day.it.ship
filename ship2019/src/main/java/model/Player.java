package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private String id;
	private List<String> motorIds;

	public Player() {
		this.motorIds = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getMotorIds() {
		return motorIds;
	}

	public void setMotorIds(List<String> motorIds) {
		this.motorIds = motorIds;
	}

}
