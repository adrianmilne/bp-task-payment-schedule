package com.bp.task.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.bp.task.domain.PaymentDate;
import com.bp.task.domain.PaymentSchedule;


public class PaymentServiceImpl implements PaymentService {

	private static final int WEDNESDAY = 3;
	private static final int FRIDAY = 5;
	private static final String MONTH_FORMAT = "MMMM";
	
	/**
	 * {@inheritDoc}
	 */
	public PaymentSchedule calculatePaymentSchedule(final DateTime dateFrom, final DateTime dateTo) {
		
		List<PaymentDate> paymentDates = new ArrayList<PaymentDate>();
		
		DateTime monthDate = dateFrom;
		while(monthDate.isBefore(dateTo)){
			paymentDates.add(calculatePaymentDateForMonth(monthDate));
			monthDate = monthDate.plusMonths(1);
		}
		
		return new PaymentSchedule(dateFrom, dateTo, paymentDates);
	}

	
	private PaymentDate calculatePaymentDateForMonth(DateTime date){
		DateTime salaryDate = calculateSalaryDate(date);
		DateTime bonusDate = calculateBonusDate(date);
		return new PaymentDate(date.toString(MONTH_FORMAT), salaryDate, bonusDate);
	}
	
	private DateTime calculateSalaryDate(DateTime date){
		DateTime salaryDate = date.dayOfMonth().withMaximumValue();
		if(salaryDate.getDayOfWeek() > FRIDAY){
			salaryDate = salaryDate.withDayOfWeek(FRIDAY);
		}
		return salaryDate;
	}
	
	private DateTime calculateBonusDate(DateTime date){
		DateTime bonusDate = date.withDayOfMonth(15).plusMonths(1);
		
		if(bonusDate.getDayOfWeek() > FRIDAY){
			bonusDate = bonusDate.withDayOfWeek(WEDNESDAY);
		}
		return bonusDate;
	}
}
