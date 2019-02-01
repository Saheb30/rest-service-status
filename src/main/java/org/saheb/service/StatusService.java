package org.saheb.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.saheb.beans.ServiceStatusList;
import org.saheb.model.ServiceStatusImpl;
import org.saheb.model.ServicesStatus;

@Path("/status")
public class StatusService {
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public ServiceStatusList getServiceStatus() {
		ServicesStatus status = new ServiceStatusImpl();
		return status.getServiceStatusList();
	}
}
