package org.saheb.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Util {
	private static Client client = null;
	public static Client getClient() {
		if(client == null ) {
			client = ClientBuilder.newClient();
		}
		return client;
	}
	public static String getStringFromFile(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
