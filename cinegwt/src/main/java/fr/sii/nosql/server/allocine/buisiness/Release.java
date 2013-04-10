package fr.sii.nosql.server.allocine.buisiness;

public class Release {

	private String releaseDate;

	private String reissueDate;

	private Code country;

	private Code releaseState;

	private Code releaseVersion;

	private Distributor distributor;

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReissueDate() {
		return reissueDate;
	}

	public void setReissueDate(String reissueDate) {
		this.reissueDate = reissueDate;
	}

	public Code getCountry() {
		return country;
	}

	public void setCountry(Code country) {
		this.country = country;
	}

	public Code getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(Code releaseState) {
		this.releaseState = releaseState;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public Code getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(Code releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

}
