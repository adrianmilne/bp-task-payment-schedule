package com.bp.task.report;

import java.io.FileWriter;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bp.task.domain.MonthlyPayment;
import com.bp.task.domain.PaymentSchedule;

/**
 * Implementation of {@link PaymentReportGenerator} which will output a
 * PaymentSchedule in a CSV format.
 * 
 * @author adrianmilne
 *
 */
public class PaymentReportGeneratorCSVImpl implements PaymentReportGenerator {

	private static final String DATE_FORMAT = "dd/MM/yyyy";

	/**
	 * {@inheritDoc}
	 */
	public void generateReport(PaymentSchedule paymentSchedule, String fileName) throws ReportGenerationException {

		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);

		try {
			FileWriter writer = new FileWriter(fileName);

			for (MonthlyPayment paymentDate : paymentSchedule.getPaymentDates()) {
				writer.append(paymentDate.getMonth());
				writer.append(",");
				writer.append(paymentDate.getSalaryPaymentDate().toString(fmt));
				writer.append(",");
				writer.append(paymentDate.getBonusPaymentDate().toString(fmt));
				writer.append('\n');
			}

			writer.flush();
			writer.close();

		} catch (Exception e) {
			throw new ReportGenerationException("Error generating report", e);
		}

	}

}
