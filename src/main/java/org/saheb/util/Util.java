package org.saheb.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class Util {
	private static Client client = null;
	private static String quoteData = null;
	public static Client getClient(){
		if(client == null){
			client = ClientBuilder.newClient();
		}
		return client;
	}
	private String getFile(String fileName) {
		String path = getClass().getClassLoader().getResource(fileName).getPath();
		if (path == null) {
			return null;
		}
		path = path.substring(1, path.length());
		return path;
	}

	public static String getJsonStrFromFile(String fileName) {
		try {
			if(quoteData == null){
				Stream<String> lines = Files.lines(Paths.get(new Util().getFile(fileName)), StandardCharsets.ISO_8859_1);
				quoteData = lines.map(String::trim).collect(Collectors.joining());
				lines.close();
			}
			return quoteData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
