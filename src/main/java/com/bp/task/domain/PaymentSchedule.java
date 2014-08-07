package com.bp.task.domain;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;

/**
 * Immutable Domain Object which is a wrapper around a payment schedule (allowing us to add metadata to the schedule).
 * 
 * @author adrianmilne
 *
 */
public class PaymentSchedule {

	private final DateTime dateFrom;
	private final DateTime dateTo;
	private final List<MonthlyPayment> paymentDates;
	
	public PaymentSchedule(DateTime dateFrom, DateTime dateTo, List<MonthlyPayment> paymentDates) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.paymentDates = paymentDates;
	}

	/**
	 * Starting date of the Payment Schedule.
	 * @return the dateFrom
	 */
	public DateTime getDateFrom() {
		return dateFrom;
	}

	/**
	 * End Date of the Payment Schedule.
	 * @return the dateTo
	 */
	public DateTime getDateTo() {
		return dateTo;
	}
	
	/**
	 * Monthly Payment Dates within the Schedule.
	 * @return the paymentDates
	 */
	public List<MonthlyPayment> getPaymentDates() {
		return paymentDates;
	}

	@Override
	public String toString() {
		return "PaymentSchedule [dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", paymentDates=" + paymentDates + "]";
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
