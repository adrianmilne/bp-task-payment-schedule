package com.bp.task.domain;

import java.util.List;

import org.joda.time.DateTime;


public class PaymentSchedule {

	private final DateTime dateFrom;
	private final DateTime dateTo;
	private final List<PaymentDate> paymentDates;
	
	public PaymentSchedule(DateTime dateFrom, DateTime dateTo, List<PaymentDate> paymentDates) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.paymentDates = paymentDates;
	}

	/**
	 * @return the dateFrom
	 */
	public DateTime getDateFrom() {
		return dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public DateTime getDateTo() {
		return dateTo;
	}
	
	/**
	 * @return the paymentDates
	 */
	public List<PaymentDate> getPaymentDates() {
		return paymentDates;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentSchedule [dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", paymentDates=" + paymentDates + "]";
	}

	
}
