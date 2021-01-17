package com.alert.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPrintAlerts.class, TestSentAlert.class, TestMaxSizeAlert.class })
public class AllTests {

}
