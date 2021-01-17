/**
 * 
 */
package com.alert.service;

import java.util.Date;

/**
 * @author nilesh narkhede
 *
 */
public class Alert {
	private String rootCause;
	private String message;
	private Date alertedOn;
	
	public Alert(String rootCause, String message) {
		super();
		this.rootCause = rootCause;
		this.message = message;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getAlertedOn() {
		return alertedOn;
	}

	public void setAlertedOn(Date alertedOn) {
		this.alertedOn = alertedOn;
	}
}
