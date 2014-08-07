package com.bp.task.report;

public class ReportGenerationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReportGenerationException(String message, Exception e) {
		super(message, e);
	}

}
