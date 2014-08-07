package com.bp.task.report;

import com.bp.task.domain.PaymentSchedule;


public interface PaymentReportGenerator {

	void generateReport(PaymentSchedule paymentSchedule, String fileName);
}
