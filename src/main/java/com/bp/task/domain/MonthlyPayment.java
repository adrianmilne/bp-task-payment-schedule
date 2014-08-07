package com.bp.task.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;

/**
 * Immutable Domain Object which contains information about the payments for a given month.
 * 
 * @author adrianmilne
 *
 */
public class MonthlyPayment {

	private final String month;
	private final DateTime salaryPaymentDate;
	private final DateTime bonusPaymentDate;

	public MonthlyPayment(String month, DateTime salaryPaymentDate, DateTime bonusPaymentDate) {
		super();
		this.month = month;
		this.salaryPaymentDate = salaryPaymentDate;
		this.bonusPaymentDate = bonusPaymentDate;
	}

	/**
	 * Name of the month. 
	 * @return the month Month Full Name
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Date of the Salary Payments for this month.
	 * @return the salaryPaymentDate
	 */
	public DateTime getSalaryPaymentDate() {
		return salaryPaymentDate;
	}

	/**
	 * Date of the Bonus Payments for this month,
	 * @return the bonusPaymentDate
	 */
	public DateTime getBonusPaymentDate() {
		return bonusPaymentDate;
	}

	@Override
	public String toString() {
		return "PaymentDate [month=" + month + ", salaryPaymentDate=" + salaryPaymentDate + ", bonusPaymentDate="
				+ bonusPaymentDate + "]";
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
