package com.bp.task.report;

import com.bp.task.domain.PaymentSchedule;


public class PaymentReportGeneratorCSVImpl implements PaymentReportGenerator {

	public void generateReport(PaymentSchedule paymentSchedule) {
		
		System.out.println(paymentSchedule);
	
	}

}
