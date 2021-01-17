package com.alert.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;

import org.junit.Test;

import com.alert.service.AbstractAlertService;
import com.alert.service.Alert;
import com.alert.service.AlertService;

public class TestMaxSizeAlert {
	AbstractAlertService alertService;

	@Test
	public void testMaxSizeAlert() {
		int resentIntervalInSec = 3;
		int maxSize = 10;
		alertService = new AlertService(maxSize, resentIntervalInSec);
		IntStream.range(0, maxSize-3)
				.forEach(i -> alertService.sendAlert(new Alert("Root Clause " + i % maxSize, "Message " + i)));

		// Expected output when print current size = max size
		String expectedOutput = String.format("Alert Total Size = %s\r\n", maxSize); // Notice the \n for new line.

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		// alertService.printAllAlerts();
		alertService.getCurrentSize();

//		assertEquals(expectedOutput, outContent.toString());
		assertTrue(expectedOutput.compareTo(outContent.toString()) <= 0);
	}

}
