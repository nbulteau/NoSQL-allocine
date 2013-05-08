package fr.sii.nosql.server.allocine.buisiness;

public class MovieResult
{
	private AlloCineMovie alloCineMovie;

	public AlloCineMovie getMovie()
	{
		return this.alloCineMovie;
	}

	public void setMovie(AlloCineMovie alloCineMovie)
	{
		this.alloCineMovie = alloCineMovie;
	}
}
