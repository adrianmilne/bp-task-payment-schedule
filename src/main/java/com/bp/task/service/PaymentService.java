package com.bp.task.service;

import org.joda.time.DateTime;

import com.bp.task.domain.PaymentSchedule;

/**
 * Payment Schedule Service.
 * 
 * @author adrianmilne
 *
 */
public interface PaymentService {

	/**
	 * Calculate a Payment Schedule between two dates. Useful if you need to do this without generating a report.
	 * @param dateFrom Start Date
	 * @param dateTo End Date
	 * @return PaymentShedule
	 * @throws PaymentServiceException
	 */
	PaymentSchedule calculatePaymentSchedule(DateTime dateFrom, DateTime dateTo) throws PaymentServiceException;
	
	/**
	 * Generate a report for a Payment Schedule.
	 * @param dateFrom Start Date
	 * @param dateTo End Date
	 * @param fileName Name of report file to generate (this is a bit limited - but ok for demo)
	 * @throws PaymentServiceException
	 */
	void generatePaymentScheduleReport(DateTime dateFrom, DateTime dateTo, String fileName) throws PaymentServiceException;
	
}
