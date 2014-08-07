package com.bp.task.service;

import org.joda.time.DateTime;

import com.bp.task.domain.PaymentSchedule;


public interface PaymentService {

	PaymentSchedule calculatePaymentSchedule(DateTime dateFrom, DateTime dateTo);
}
