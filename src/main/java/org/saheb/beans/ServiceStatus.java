package org.saheb.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceStatus {
	private String serviceName;
	private boolean status;
	private int statusCode;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "ServiceStatus [serviceName=" + serviceName + ", status=" + status + ", statusCode=" + statusCode + "]";
	}
	
}
