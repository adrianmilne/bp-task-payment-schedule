package com.bp.task;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bp.task.report.PaymentReportGenerator;
import com.bp.task.report.PaymentReportGeneratorCSVImpl;
import com.bp.task.service.PaymentService;
import com.bp.task.service.PaymentServiceImpl;

/**
 * Main Entry Point to the class.
 * 
 * Build the project using Maven, and run
 * "java -jar payment-scheduler-0.0.1-SNAPSHOT-jar-with-dependencies.jar" on the
 * command line to execute.
 * 
 * @author adrianmilne
 *
 */
public class Main {

	private static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		LOG.info("Beginning Payment Schedule File Generation....");

		PaymentService paymentService;
		PaymentReportGenerator paymentReportGenerator;

		paymentReportGenerator = new PaymentReportGeneratorCSVImpl();
		paymentService = new PaymentServiceImpl(paymentReportGenerator);

		// Current Date
		DateTime dateFrom = new DateTime();
		// End Date (end of the year)
		DateTime dateTo = new DateTime(Calendar.getInstance().get(Calendar.YEAR) + 1, 1, 1, 0, 0, 0, 0);

		String filename = "PaymentSchedule.csv";
		
		// Can overwite default filename with a command line parameter
		if(args != null && args.length > 0l){
			filename = args[0];
		}
		
		paymentReportGenerator.generateReport(paymentService.calculatePaymentSchedule(dateFrom, dateTo), filename);

		logCompletionMessage(filename);

	}

	/**
	 * Log completion message
	 */
	private static void logCompletionMessage(String filename) {

		StringBuilder sb = new StringBuilder();
		sb.append("\n\n********************************************");
		sb.append("\n* File has been generated with name ");
		sb.append(filename);
		sb.append("\n********************************************\n");
		LOG.info(sb.toString());
	}
}
