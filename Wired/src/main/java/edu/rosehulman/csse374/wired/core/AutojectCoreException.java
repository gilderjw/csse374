package edu.rosehulman.csse374.wired.core;

public class AutojectCoreException extends Exception {
	private static final long serialVersionUID = 6408144652966483225L;

	public AutojectCoreException() {
		super("An error occured while performing autoject operation.");
	}

	public AutojectCoreException(String message) {
		super(message);
	}

	public AutojectCoreException(Throwable cause) {
		super(cause);
	}

	public AutojectCoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public AutojectCoreException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
