package com.alert.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import org.junit.Test;

import com.alert.service.AbstractAlertService;
import com.alert.service.Alert;
import com.alert.service.AlertService;

public class TestSentAlert {
	AbstractAlertService alertService;

	@Test
	public void testSendAlert() {
		int resentIntervalInSec = 3;
		int maxSize = 5;
		alertService = new AlertService(maxSize, resentIntervalInSec);
		IntStream.range(0, maxSize)
				.forEach(i -> alertService.sendAlert(new Alert("Root Clause " + i % maxSize, "Message " + i)));

		try {
			// Sleep thread for "resentIntervalInSec" seconds so that next alert will be
			// sent after "resentIntervalInSec" seconds
			// If sleeps increases than resentIntervalInSec value, test case will get fail.
			Thread.sleep(resentIntervalInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Expected output when alert is sent
		String expectedOutput = "Root Clause 0: Message 0\r\n"; // Notice the \n for new line.

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// alertService.printAllAlerts();
		alertService.sendAlert(new Alert("Root Clause 0", "Message 0"));

		assertEquals(expectedOutput, outContent.toString());
	}

}
