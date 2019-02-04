package org.saheb.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.saheb.beans.ServiceStatus;
import org.saheb.beans.ServiceStatusList;
import org.saheb.client.RestClient;
import org.saheb.util.EmailUtil;
import org.saheb.util.Util;

public class ServiceStatusImpl implements ServicesStatus {
	private static final String SALESFORCE_URI = "http://lti2-sboservices.thehartford.com/bi_sc_re_sfleads_svc/updateQuoteDetails";
	private static final String GET_RATING_URI = "https://lti2-smallbizquote.thehartford.com/api/ratingService/getRating";
	private static final String GET_PREMIUM_URI = "https://lti2-smallbizquote.thehartford.com/api/ratingService/getPremiumForOffering ";
	private static final Map<String, String> serviceMap = new HashMap<>();
	static {
		serviceMap.put("SALESFORCE", SALESFORCE_URI);
		serviceMap.put("GET_RATING", GET_RATING_URI);
		serviceMap.put("GET_PREMIUM", GET_PREMIUM_URI);
	}

	@Override
	public ServiceStatusList getServiceStatusList() {
		ServiceStatusList serviceStatusList = new ServiceStatusList();;
		ServiceStatus serviceStatus = null;
		for(Entry<String, String> entry : serviceMap.entrySet()) {
			String serviceName = entry.getKey();
			String serviceURI = entry.getValue();
			
			serviceStatus = new ServiceStatus();
			
			String requestBody = Util.getJsonStrFromFile("QuoteData.json");
			Response response = RestClient.getStatusPost(serviceURI, requestBody);
			if(response != null && response.getStatus() >= 200 && response.getStatus() <= 226 && !response.toString().toLowerCase().contains("error")) {
				serviceStatus.setServiceName(serviceName);
				serviceStatus.setStatus(true);
				serviceStatus.setStatusCode(response.getStatus());
			}else {
				serviceStatus.setServiceName(serviceName);
				serviceStatus.setStatus(false);
				serviceStatus.setStatusCode(response.getStatus());
			}
			serviceStatusList.getServiceStatusList().add(serviceStatus);
		}
		EmailUtil.sendEmail(serviceStatusList);
		System.out.println("List size-->"+serviceStatusList.getServiceStatusList().size());
		return serviceStatusList;
	}
}
