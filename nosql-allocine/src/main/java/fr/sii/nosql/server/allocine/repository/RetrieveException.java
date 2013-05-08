package fr.sii.nosql.server.allocine.repository;

public class RetrieveException extends Exception
{

	private static final long serialVersionUID = 1L;

	public RetrieveException()
	{
		super();
	}

	public RetrieveException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public RetrieveException(String message)
	{
		super(message);
	}

	public RetrieveException(Throwable cause)
	{
		super(cause);
	}
}
