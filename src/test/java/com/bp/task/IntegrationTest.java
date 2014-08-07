package com.bp.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.bp.task.domain.PaymentSchedule;
import com.bp.task.report.PaymentReportGenerator;
import com.bp.task.report.PaymentReportGeneratorCSVImpl;
import com.bp.task.service.PaymentService;
import com.bp.task.service.PaymentServiceImpl;

/**
 * Integration Test - which will test the system end to end to check inputs and outputs.
 * 
 * @author amilne
 * 
 */
public class IntegrationTest {

	private PaymentService paymentService;
	private PaymentReportGenerator paymentReportGenerator;

	@Before
	public void setUp() {
		paymentService = new PaymentServiceImpl();
		paymentReportGenerator = new PaymentReportGeneratorCSVImpl();
		
		DateTime dateFrom = new DateTime(2014, 1, 1, 0, 0, 0, 0);
		DateTime dateTo = new DateTime(2014, 12, 31, 0, 0, 0, 0);

		PaymentSchedule paymentSchedule = paymentService.calculatePaymentSchedule(dateFrom, dateTo);
		paymentReportGenerator.generateReport(paymentSchedule, "test-output.csv");
	}

	@Test
	public void testSuccess() throws Exception {
		File expectedOutputFile = new File("src/test/resources/test-expected-output.csv");
		File actualOutputFile = new File("test-output.csv");
		assertTrue("Actual CSV output is different to expected output", FileUtils.contentEquals(expectedOutputFile, actualOutputFile));

	}
	
	@Test
	public void testFailure() throws Exception {
		File expectedOutputFile = new File("src/test/resources/test-unexpected-output.csv");
		File actualOutputFile = new File("test-output.csv");
		assertFalse("Actual CSV output is the same as expected output", FileUtils.contentEquals(expectedOutputFile, actualOutputFile));
	}

}
