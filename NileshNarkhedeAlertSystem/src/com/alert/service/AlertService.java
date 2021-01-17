/**
 * 
 */
package com.alert.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * @author nilesh narkhede
 *
 */
public class AlertService extends AbstractAlertService {

	/**
	 * In memory queue to store all alerts of size N
	 */
	Queue<Alert> alertQueue = new LinkedList<Alert>();

	/**
	 * In memory map to store alert with root cause - this helps to determine lasted
	 * alerted time
	 */
	Map<String, Alert> hmLastAlertSentOn = new HashMap<>();

	/**
	 * Parameterized Constructor
	 * 
	 * @param maxSize
	 * @param resendIntervalInSeconds
	 */
	public AlertService(int maxSize, int resendIntervalInSeconds) {
		super(maxSize, resendIntervalInSeconds);
	}

	/**
	 * This prints all alerts stored in in-memory queue
	 */
	@Override
	public void printAllAlerts() {
		for (Alert alert : alertQueue) {
			printAlert(alert);
		}
	}

	/**
	 * This prints current size of the in-memory queue
	 */
	@Override
	public void getCurrentSize() {
		System.out.println("Alert Total Size = " + alertQueue.size());
	}

	@Override
	public void sendAlert(Alert alert) {
		alert.setAlertedOn(new Date());
		Alert tmpAlert = hmLastAlertSentOn.get(alert.getRootCause());
		saveRecentAlerts(alert);
		if (!checkLastAlertSentWithinInterval(tmpAlert)) {
			printAlert(alert);
		}
	}

	/**
	 * This checks if given alert is sent within
	 * {@link #getResendIntervalInSeconds()} time
	 * 
	 * @param alert
	 * @return
	 */
	private boolean checkLastAlertSentWithinInterval(Alert alert) {
		if (alert == null)
			return false;

		Date currentDate = new Date();
		long currentSeconds = currentDate.getTime() / 1000;
		long lastAlertSeconds = alert.getAlertedOn().getTime() / 1000;
		return currentSeconds < lastAlertSeconds + getResendIntervalInSeconds();
	}

	/**
	 * This add alert in the in-memory queue and removed the older alerts if
	 * {@link #getMaxSize()} exceeds
	 * 
	 * @param alert
	 */
	private void saveRecentAlerts(Alert alert) {
		if (alertQueue.size() >= getMaxSize()) {
			alertQueue.poll();
		}
		alertQueue.add(alert);
		hmLastAlertSentOn.put(alert.getRootCause(), alert);
	}

	/**
	 * This print the given alert message
	 * 
	 * @param alert
	 */
	private void printAlert(Alert alert) {
		//System.out.println(String.format("%s, %s: %s", alert.getAlertedOn(), alert.getRootCause(), alert.getMessage()));
		System.out.println(String.format("%s: %s", alert.getRootCause(), alert.getMessage()));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Alert> allAlerts = new ArrayList<>();
		IntStream.range(0, 10).forEach(i -> allAlerts.add(new Alert("Root Clause " + i % 5, "Message " + i)));

		AlertService alertService = new AlertService(5, 16);
		allAlerts.forEach(alert -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("===============");
			alertService.sendAlert(alert);
			alertService.printAllAlerts();
			alertService.getCurrentSize();
		});
	}
}
