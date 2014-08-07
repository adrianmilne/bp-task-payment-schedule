package com.bp.task.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import com.bp.task.domain.MonthlyPayment;
import com.bp.task.domain.PaymentSchedule;
import com.bp.task.report.PaymentReportGenerator;

/**
 * Unit Tests for {@link PaymentServiceImpl}
 * 
 * @author adrianmilne
 */
public class PaymentServiceTest extends JUnit4Mockery {

	private PaymentService service;
	private DateTime dateFrom;
	private DateTime dateTo;
	private PaymentReportGenerator mockPaymentReportGenerator;

	@Before
	public void setup() {

		// Using JMock to mock out injected classes - as we don't need to test
		// these in a unit test for this class - could be extended much further..
		setImposteriser(ClassImposteriser.INSTANCE);
		mockPaymentReportGenerator = mock(PaymentReportGenerator.class);

		// test date range covers all of 2014
		dateFrom = new DateTime(2014, 1, 1, 0, 0, 0, 0);
		dateTo = new DateTime(2014, 12, 31, 0, 0, 0, 0);

		service = new PaymentServiceImpl(mockPaymentReportGenerator);

	}

	/**
	 * Test that the Payment Schedule is calculated for 2014 correctly.
	 */
	@Test
	public void testCalculatePaymentSchedule() {
		PaymentSchedule paymentSchedule = service.calculatePaymentSchedule(dateFrom, dateTo);
		PaymentSchedule expectedPaymentSchedule = createExpectedTestData();
		assertEquals(expectedPaymentSchedule, paymentSchedule);
	}

	/**
	 * Test that an {@link PaymentServiceException.class} is thrown.
	 */
	@Test(expected = PaymentServiceException.class)
	public void testCalculatePaymentScheduleException() {
		service.calculatePaymentSchedule(null, null);
	}

	@Test
	public void testGeneratePaymentScheduleReport() {

		checking(new Expectations() {
			{
				oneOf(mockPaymentReportGenerator).generateReport(with(any(PaymentSchedule.class)),
						with(any(String.class)));
			}
		});

		service.generatePaymentScheduleReport(dateFrom, dateTo, "filenametest");

		assertIsSatisfied();
	}

	private PaymentSchedule createExpectedTestData() {
		List<MonthlyPayment> paymentDates = new ArrayList<MonthlyPayment>();
		paymentDates.add(createExpectedTestPayment("January", "31/01/2014", "12/02/2014"));
		paymentDates.add(createExpectedTestPayment("February", "28/02/2014", "12/03/2014"));
		paymentDates.add(createExpectedTestPayment("March", "31/03/2014", "15/04/2014"));
		paymentDates.add(createExpectedTestPayment("April", "30/04/2014", "15/05/2014"));
		paymentDates.add(createExpectedTestPayment("May", "30/05/2014", "11/06/2014"));
		paymentDates.add(createExpectedTestPayment("June", "30/06/2014", "15/07/2014"));
		paymentDates.add(createExpectedTestPayment("July", "31/07/2014", "15/08/2014"));
		paymentDates.add(createExpectedTestPayment("August", "29/08/2014", "15/09/2014"));
		paymentDates.add(createExpectedTestPayment("September", "30/09/2014", "15/10/2014"));
		paymentDates.add(createExpectedTestPayment("October", "31/10/2014", "12/11/2014"));
		paymentDates.add(createExpectedTestPayment("November", "28/11/2014", "15/12/2014"));
		paymentDates.add(createExpectedTestPayment("December", "31/12/2014", "15/01/2015"));
		return new PaymentSchedule(dateFrom, dateTo, paymentDates);
	}

	private MonthlyPayment createExpectedTestPayment(String month, String salaryDate, String bonusDate) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		return new MonthlyPayment(month, formatter.parseDateTime(salaryDate), formatter.parseDateTime(bonusDate));
	}
}
