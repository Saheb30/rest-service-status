package org.saheb.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceStatusList {
	private List<ServiceStatus> serviceStatusList = new ArrayList<>();

	@Override
	public String toString() {
		return "[serviceStatusList=" + serviceStatusList + "]";
	}

	public List<ServiceStatus> getServiceStatusList() {
		return serviceStatusList;
	}

	public void setServiceStatusList(List<ServiceStatus> serviceStatusList) {
		this.serviceStatusList = serviceStatusList;
	}
}
