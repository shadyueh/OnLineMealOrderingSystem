package com.wangyanci.exception;

public class BanException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BanException() {
		super();
	}

	public BanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BanException(String message, Throwable cause) {
		super(message, cause);
	}

	public BanException(String message) {
		super(message);
	}

	public BanException(Throwable cause) {
		super(cause);
	}
}
