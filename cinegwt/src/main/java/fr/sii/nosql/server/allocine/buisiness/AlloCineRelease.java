package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("release")
public class AlloCineRelease {

	private String releaseDate;

	private String reissueDate;

	private AlloCineCode country;

	private AlloCineCode releaseState;

	private AlloCineCode releaseVersion;

	private AlloCineDistributor alloCineDistributor;

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

	public AlloCineCode getCountry() {
		return country;
	}

	public void setCountry(AlloCineCode country) {
		this.country = country;
	}

	public AlloCineCode getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(AlloCineCode releaseState) {
		this.releaseState = releaseState;
	}

	public AlloCineDistributor getDistributor() {
		return alloCineDistributor;
	}

	public void setDistributor(AlloCineDistributor alloCineDistributor) {
		this.alloCineDistributor = alloCineDistributor;
	}

	public AlloCineCode getReleaseVersion() {
		return releaseVersion;
	}

	public void setReleaseVersion(AlloCineCode releaseVersion) {
		this.releaseVersion = releaseVersion;
	}

}
