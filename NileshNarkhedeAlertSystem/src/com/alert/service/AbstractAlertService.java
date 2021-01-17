package com.alert.service;

public abstract class AbstractAlertService {

	private int maxSize;

	private int resendIntervalInSeconds;

	public AbstractAlertService(final int maxSize, final int resendIntervalInSeconds) {
		this.maxSize = maxSize;
		this.resendIntervalInSeconds = resendIntervalInSeconds;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getResendIntervalInSeconds() {
		return resendIntervalInSeconds;
	}

	public void setResendIntervalInSeconds(int resendIntervalInSeconds) {
		this.resendIntervalInSeconds = resendIntervalInSeconds;
	}

	/**
	 * Adds alert to a store. Trims the store if maxSize is exceeded. Prints the
	 * alert if the alert has not been raised for past `resendInterval` seconds.
	 */
	public abstract void sendAlert(Alert alert);

	/**
	 * Prints all alerts in the order they were added.
	 */
	public abstract void printAllAlerts();

	/**
	 * Prints the current size.
	 */
	public abstract void getCurrentSize();

}
