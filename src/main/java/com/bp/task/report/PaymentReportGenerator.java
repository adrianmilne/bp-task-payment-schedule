package com.bp.task.report;

import com.bp.task.domain.PaymentSchedule;

/**
 * Interface for a report generator for a PaymentSchedule.
 * 
 * @author adrianmilne
 *
 */
public interface PaymentReportGenerator {

	/**
	 * Generate a report of the Payment Schedule.
	 * @param paymentSchedule Schedule to generate report for
	 * @param fileName Name of output file
	 */
	void generateReport(PaymentSchedule paymentSchedule, String fileName) throws ReportGenerationException;
}
