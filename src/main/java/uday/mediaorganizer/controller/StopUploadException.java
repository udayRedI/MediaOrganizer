package uday.mediaorganizer.controller;

public class StopUploadException extends RuntimeException {
	public StopUploadException() {
		super();
	}

	public StopUploadException(String s) {
		super(s);
	}

	public StopUploadException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public StopUploadException(Throwable throwable) {
		super(throwable);
	}
}
