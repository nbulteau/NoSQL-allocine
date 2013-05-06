package fr.sii.nosql.server.allocine.buisiness;

public class MovieListResult {
	private AlloCineFeed alloCineFeed;

	public AlloCineFeed getFeed() {
		return alloCineFeed;
	}

	public void setFeed(AlloCineFeed alloCineFeed) {
		this.alloCineFeed = alloCineFeed;
	}
}
