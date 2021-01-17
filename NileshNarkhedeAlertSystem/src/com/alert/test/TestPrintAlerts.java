package com.alert.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alert.service.AbstractAlertService;
import com.alert.service.Alert;
import com.alert.service.AlertService;

public class TestPrintAlerts {
	AbstractAlertService alertService;

	@Test
	public void testPrintAllAlerts() {
		alertService = new AlertService(1, 1);
		List<Alert> allAlerts = new ArrayList<>();
		IntStream.range(0, 1).forEach(i -> allAlerts.add(new Alert("Root Clause " + i % 5, "Message " + i)));
		alertService.sendAlert(allAlerts.get(0));
		
		String expectedOutput = "Root Clause 0: Message 0\r\n"; // Notice the \n for new line.

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		alertService.printAllAlerts();
		
		assertEquals(expectedOutput, outContent.toString());
	}
}
