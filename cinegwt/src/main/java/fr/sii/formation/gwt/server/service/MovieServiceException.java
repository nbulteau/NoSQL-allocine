package fr.sii.formation.gwt.server.service;

public class MovieServiceException extends Exception
{

	private static final long serialVersionUID = 1L;

	public MovieServiceException()
	{
		super();
	}

	// public MovieServiceException(String message, Throwable cause,
	// boolean enableSuppression, boolean writableStackTrace) {
	// super(message, cause, enableSuppression, writableStackTrace);
	// }

	public MovieServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public MovieServiceException(String message)
	{
		super(message);
	}

	public MovieServiceException(Throwable cause)
	{
		super(cause);
	}
}
