package org.saheb.model;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saheb.beans.ServiceStatus;
import org.saheb.beans.ServiceStatusList;

public class ServiceStatusImpl implements ServicesStatus {
	private static final String REST_URI = "http://google.com";

	@Override
	public ServiceStatusList getServiceStatusList() {
		ServiceStatusList serviceStatusList = new ServiceStatusList();
		ServiceStatus serviceStatus = new ServiceStatus();
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(REST_URI);
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out.println("inside service response->"+response.toString());
		if(response != null && response.getStatus() == 200 && !response.toString().toLowerCase().contains("error")) {
			serviceStatus.setServiceName("GOOGLE");
			serviceStatus.setStatus(true);
			serviceStatus.setStatusCode(response.getStatus());
			serviceStatusList.getServiceStatusList().add(serviceStatus);
		}
		System.out.println("List size-->"+serviceStatusList.getServiceStatusList().size());
		return serviceStatusList;
	}
	/*public static void main(String[] args) {
		int n = new ServiceStatusImpl().getServiceStatusList().getServiceStatusList().size();
		System.out.println(n);
	}*/

}
