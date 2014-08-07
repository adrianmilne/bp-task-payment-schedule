package com.bp.task;

import java.util.Calendar;

import org.joda.time.DateTime;

import com.bp.task.report.PaymentReportGenerator;
import com.bp.task.report.PaymentReportGeneratorCSVImpl;
import com.bp.task.service.PaymentService;
import com.bp.task.service.PaymentServiceImpl;

public class Main {

	public static void main(String[] args) {
		
		PaymentService paymentService;
		PaymentReportGenerator paymentReportGenerator;
		
		paymentService = new PaymentServiceImpl();
		paymentReportGenerator = new PaymentReportGeneratorCSVImpl();
		
		DateTime dateFrom = new DateTime();
		DateTime dateTo = new DateTime(Calendar.getInstance().get(Calendar.YEAR) + 1, 1, 1, 0, 0, 0, 0);
		
		paymentReportGenerator.generateReport(paymentService.calculatePaymentSchedule(dateFrom, dateTo), "/home/amilne/testfile.csv");
		
	}
}
