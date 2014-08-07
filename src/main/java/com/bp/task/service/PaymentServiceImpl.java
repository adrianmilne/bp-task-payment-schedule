package com.bp.task.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bp.task.domain.MonthlyPayment;
import com.bp.task.domain.PaymentSchedule;
import com.bp.task.report.PaymentReportGenerator;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link PaymentService}.
 * 
 * @author adrianmilne
 *
 */
public class PaymentServiceImpl implements PaymentService {

	private static Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	// Statics and Magic Numbers - could be exposed as configuration
	private static final int WEDNESDAY = 3;
	private static final int FRIDAY = 5;
	private static final String MONTH_FORMAT = "MMMM";
	private static final int DAY_OF_MONTH_FOR_BONUS_PAYMENTS = 15;

	private PaymentReportGenerator paymentReportGenerator;

	/**
	 * Use of the constructor to inject the dependencies. Normally would use Spring to handle Dependcency Injection. 
	 * @param paymentReportGenerator Payment Report Generator
	 */
	public PaymentServiceImpl(PaymentReportGenerator paymentReportGenerator) {
		this.paymentReportGenerator = paymentReportGenerator;
	}

	/**
	 * {@inheritDoc}
	 */
	public PaymentSchedule calculatePaymentSchedule(final DateTime dateFrom, final DateTime dateTo)
			throws PaymentServiceException {

		try {
			Preconditions.checkNotNull(dateFrom);
			Preconditions.checkNotNull(dateTo);

			List<MonthlyPayment> paymentDates = new ArrayList<MonthlyPayment>();
			DateTime monthDate = dateFrom;

			while (monthDate.isBefore(dateTo)) {
				paymentDates.add(calculatePaymentDateForMonth(monthDate));
				monthDate = monthDate.plusMonths(1);
			}

			return new PaymentSchedule(dateFrom, dateTo, paymentDates);

		} catch (Exception e) {
			throw new PaymentServiceException("Error calculating payment schedule", e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void generatePaymentScheduleReport(DateTime dateFrom, DateTime dateTo, String fileName)
			throws PaymentServiceException {
		
		try {
			
			Preconditions.checkNotNull(dateFrom);
			Preconditions.checkNotNull(dateTo);
			Preconditions.checkNotNull(fileName);
			paymentReportGenerator.generateReport(calculatePaymentSchedule(dateFrom, dateTo), "test-file.csv");
			
		} catch (Exception e) {
			throw new PaymentServiceException("Error calculating payment schedule", e);
		}
	}

	/**
	 * Calculate the Monthly Payments for the month.
	 * 
	 * @param date
	 *            use the date of the month
	 * @return MonthlyPayment
	 */
	private MonthlyPayment calculatePaymentDateForMonth(DateTime date) {
		DateTime salaryDate = calculateSalaryDate(date);
		DateTime bonusDate = calculateBonusDate(date);
		return new MonthlyPayment(date.toString(MONTH_FORMAT), salaryDate, bonusDate);
	}

	/**
	 * Calculate the salary payment date of the month.
	 * 
	 * @param date
	 *            use the date of the month
	 * @return payment date
	 */
	private DateTime calculateSalaryDate(DateTime date) {
		DateTime salaryDate = date.dayOfMonth().withMaximumValue();
		if (salaryDate.getDayOfWeek() > FRIDAY) {
			salaryDate = salaryDate.withDayOfWeek(FRIDAY);
		}
		return salaryDate;
	}

	/**
	 * Calculate the bonus payment date of the month.
	 * 
	 * @param date
	 *            use the date of the month
	 * @return payment date
	 */
	private DateTime calculateBonusDate(DateTime date) {

		// TODO: We are calculating the bonus date for the current month (which
		// will be NEXT month here)
		// Check if this is correct - or if we should be calculating for the
		// previous month
		DateTime bonusDate = date.withDayOfMonth(DAY_OF_MONTH_FOR_BONUS_PAYMENTS).plusMonths(1);

		if (bonusDate.getDayOfWeek() > FRIDAY) {
			bonusDate = bonusDate.withDayOfWeek(WEDNESDAY);
		}
		return bonusDate;
	}

}
