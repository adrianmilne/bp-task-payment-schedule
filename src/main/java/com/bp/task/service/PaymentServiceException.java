package com.bp.task.service;

public class PaymentServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PaymentServiceException(String message, Exception e) {
		super(message, e);
	}

}
