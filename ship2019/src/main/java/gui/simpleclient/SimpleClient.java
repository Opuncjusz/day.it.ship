package gui.simpleclient;

import client.Connector;

public class SimpleClient {

	private Connector connector;

	public SimpleClient() {
		// connector = new Connector();
		SimpleClientTemplate.main(null);
		// SimpleClientTemplate.connector = connector;
	}

}
