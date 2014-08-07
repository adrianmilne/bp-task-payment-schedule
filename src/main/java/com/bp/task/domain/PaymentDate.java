package com.bp.task.domain;

import org.joda.time.DateTime;


public class PaymentDate {

	private final String month;
	private final DateTime salaryPaymentDate;
	private final DateTime bonusPaymentDate;
	
	public PaymentDate(String month, DateTime salaryPaymentDate, DateTime bonusPaymentDate) {
		super();
		this.month = month;
		this.salaryPaymentDate = salaryPaymentDate;
		this.bonusPaymentDate = bonusPaymentDate;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	
	/**
	 * @return the salaryPaymentDate
	 */
	public DateTime getSalaryPaymentDate() {
		return salaryPaymentDate;
	}
	
	/**
	 * @return the bonusPaymentDate
	 */
	public DateTime getBonusPaymentDate() {
		return bonusPaymentDate;
	}
	
	@Override
	public String toString() {
		return "PaymentDate [month=" + month + ", salaryPaymentDate=" + salaryPaymentDate + ", bonusPaymentDate=" + bonusPaymentDate + "]";
	}
		
}
