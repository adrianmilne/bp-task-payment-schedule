package com.bp.task.service;

import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;


public class PaymentServiceTest extends JUnit4Mockery {

	@Before
	public void setup() {

		setImposteriser(ClassImposteriser.INSTANCE);
	}
}
