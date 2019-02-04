package org.saheb.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saheb.beans.ServiceStatus;
import org.saheb.beans.ServiceStatusList;
import org.saheb.util.Util;

public class RestClient implements Runnable{
	@Override
	public void run() {
		new RestClient().getStatus();
	}
	private void getStatus(){
		ServiceStatusList serviceStatusList = new ServiceStatusList();
		ServiceStatus serviceStatus = new ServiceStatus();
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/services-status/rs").path("status");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out.println(response);
		if(response != null && response.getStatus() == 200 && !response.toString().toLowerCase().contains("error")) {
			serviceStatus.setServiceName("SERVICE_1");
			serviceStatus.setStatus(true);
			serviceStatus.setStatusCode(response.getStatus());
			serviceStatusList.getServiceStatusList().add(serviceStatus);
		}
		System.out.println(serviceStatusList.toString());
	}
	public static Response getStatusPost(String uri, String requestBody) {
		Client client = Util.getClient();
		WebTarget webTarget = client.target(uri);
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.post(Entity.entity(requestBody, MediaType.APPLICATION_JSON));
	}

}
