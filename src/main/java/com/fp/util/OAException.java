package com.fp.util;

//OA系统自定义异常
public class OAException extends RuntimeException {

	public OAException() {
		// TODO Auto-generated constructor stub
	}

	public OAException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OAException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public OAException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OAException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
