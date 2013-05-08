package fr.sii.nosql.server.allocine.buisiness;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("movie")
public class AlloCineMovie {
	private List<AlloCineCastMember> alloCineCastMember;

	private AlloCineCastingShort alloCineCastingShort;

	private long code;

	private List<AlloCineFeature> alloCineFeature;

	private List<AlloCineGenre> alloCineGenre;

	private String keywords;

	private List<AlloCineLink> alloCineLink;

	private AlloCineMovieType alloCineMovieType;

	private List<AlloCineNationality> alloCineNationality;

	private List<AlloCineNews> alloCineNews;

	private String originalTitle;

	private AlloCinePoster alloCinePoster;

	private Number productionYear;

	private AlloCineRelease alloCineRelease;

	private int runtime;

	private AlloCineStatistics alloCineStatistics;

	private String synopsis;

	private String synopsisShort;

	private List<AlloCineCode> tag;

	private String title;

	private AlloCineTrailer alloCineTrailer;

	private List<AlloCineTrivia> alloCineTrivia;

	private AlloCineCode movieCertificate;

	private AlloCineCode color;

	private AlloCineFormatList alloCineFormatList;

	private List<AlloCineCode> language;

	private String budget;
	
	private String trailerEmbed;
	
	@JsonIgnore
	private List<Media> media;
	
	@JsonIgnore
	private List<BoxOffice> boxOffice;

	public List<BoxOffice> getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(List<BoxOffice> boxOffice) {
		this.boxOffice = boxOffice;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public String getTrailerEmbed() {
		return trailerEmbed;
	}

	public void setTrailerEmbed(String trailerEmbed) {
		this.trailerEmbed = trailerEmbed;
	}

	public List<AlloCineCastMember> getCastMember() {
		return this.alloCineCastMember;
	}

	public void setCastMember(List<AlloCineCastMember> alloCineCastMember) {
		this.alloCineCastMember = alloCineCastMember;
	}

	public AlloCineCastingShort getCastingShort() {
		return this.alloCineCastingShort;
	}

	public void setCastingShort(AlloCineCastingShort alloCineCastingShort) {
		this.alloCineCastingShort = alloCineCastingShort;
	}

	public long getCode() {
		return this.code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public List<AlloCineFeature> getFeature() {
		return this.alloCineFeature;
	}

	public void setFeature(List<AlloCineFeature> alloCineFeature) {
		this.alloCineFeature = alloCineFeature;
	}

	public List<AlloCineGenre> getGenre() {
		return this.alloCineGenre;
	}

	public void setGenre(List<AlloCineGenre> alloCineGenre) {
		this.alloCineGenre = alloCineGenre;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<AlloCineLink> getLink() {
		return this.alloCineLink;
	}

	public void setLink(List<AlloCineLink> alloCineLink) {
		this.alloCineLink = alloCineLink;
	}

	public AlloCineMovieType getMovieType() {
		return this.alloCineMovieType;
	}

	public void setMovieType(AlloCineMovieType alloCineMovieType) {
		this.alloCineMovieType = alloCineMovieType;
	}

	public List<AlloCineNationality> getNationality() {
		return this.alloCineNationality;
	}

	public void setNationality(List<AlloCineNationality> alloCineNationality) {
		this.alloCineNationality = alloCineNationality;
	}

	@JsonIgnore
	public List<AlloCineNews> getNews() {
		return this.alloCineNews;
	}

	@JsonIgnore
	public void setNews(List<AlloCineNews> alloCineNews) {
		this.alloCineNews = alloCineNews;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public AlloCinePoster getPoster() {
		return this.alloCinePoster;
	}

	public void setPoster(AlloCinePoster alloCinePoster) {
		this.alloCinePoster = alloCinePoster;
	}

	public Number getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(Number productionYear) {
		this.productionYear = productionYear;
	}

	public AlloCineRelease getRelease() {
		return this.alloCineRelease;
	}

	public void setRelease(AlloCineRelease alloCineRelease) {
		this.alloCineRelease = alloCineRelease;
	}

	public int getRuntime() {
		return this.runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public AlloCineStatistics getStatistics() {
		return this.alloCineStatistics;
	}

	public void setStatistics(AlloCineStatistics alloCineStatistics) {
		this.alloCineStatistics = alloCineStatistics;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getSynopsisShort() {
		return this.synopsisShort;
	}

	public void setSynopsisShort(String synopsisShort) {
		this.synopsisShort = synopsisShort;
	}

	@JsonIgnore
	public List<AlloCineCode> getTag() {
		return this.tag;
	}

	@JsonIgnore
	public void setTag(List<AlloCineCode> tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AlloCineTrailer getTrailer() {
		return this.alloCineTrailer;
	}

	public void setTrailer(AlloCineTrailer alloCineTrailer) {
		this.alloCineTrailer = alloCineTrailer;
	}

	@JsonIgnore
	public List<AlloCineTrivia> getTrivia() {
		return this.alloCineTrivia;
	}

	@JsonIgnore
	public void setTrivia(List<AlloCineTrivia> alloCineTrivia) {
		this.alloCineTrivia = alloCineTrivia;
	}

	@JsonIgnore
	public AlloCineCode getMovieCertificate() {
		return movieCertificate;
	}

	@JsonIgnore
	public void setMovieCertificate(AlloCineCode movieCertificate) {
		this.movieCertificate = movieCertificate;
	}

	public AlloCineCode getColor() {
		return color;
	}

	public void setColor(AlloCineCode color) {
		this.color = color;
	}

	public AlloCineFormatList getFormatList() {
		return alloCineFormatList;
	}

	public void setFormatList(AlloCineFormatList alloCineFormatList) {
		this.alloCineFormatList = alloCineFormatList;
	}

	public List<AlloCineCode> getLanguage() {
		return language;
	}

	public void setLanguage(List<AlloCineCode> language) {
		this.language = language;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
}
