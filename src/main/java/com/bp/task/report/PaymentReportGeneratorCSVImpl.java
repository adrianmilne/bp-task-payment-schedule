package com.bp.task.report;

import java.io.FileWriter;
import java.io.IOException;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.bp.task.domain.PaymentDate;
import com.bp.task.domain.PaymentSchedule;

public class PaymentReportGeneratorCSVImpl implements PaymentReportGenerator {

	/**
	 * {@inheritDoc}
	 */
	public void generateReport(PaymentSchedule paymentSchedule, String fileName) {

		System.out.println(paymentSchedule);
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");

		try {
			FileWriter writer = new FileWriter(fileName);

			for (PaymentDate paymentDate : paymentSchedule.getPaymentDates()) {
				writer.append(paymentDate.getMonth());
				writer.append(",");
				writer.append(paymentDate.getSalaryPaymentDate().toString(fmt));
				writer.append(",");
				writer.append(paymentDate.getBonusPaymentDate().toString(fmt));
				writer.append('\n');
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
