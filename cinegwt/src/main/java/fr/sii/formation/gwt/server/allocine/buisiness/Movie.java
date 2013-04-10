package fr.sii.formation.gwt.server.allocine.buisiness;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Movie {
	private List<CastMember> castMember;

	private CastingShort castingShort;

	private long code;

	private List<Feature> feature;

	private List<Genre> genre;

	private String keywords;

	private List<Link> link;

	private MovieType movieType;

	private List<Nationality> nationality;

	private List<News> news;

	private String originalTitle;

	private Poster poster;

	private Number productionYear;

	private Release release;

	private int runtime;

	private Statistics statistics;

	private String synopsis;

	private String synopsisShort;

	private List<Code> tag;

	private String title;

	private Trailer trailer;

	private List<Trivia> trivia;

	private Code movieCertificate;

	private Code color;

	private FormatList formatList;

	private List<Code> language;

	private String budget;

	public List<CastMember> getCastMember() {
		return this.castMember;
	}

	public void setCastMember(List<CastMember> castMember) {
		this.castMember = castMember;
	}

	public CastingShort getCastingShort() {
		return this.castingShort;
	}

	public void setCastingShort(CastingShort castingShort) {
		this.castingShort = castingShort;
	}

	public long getCode() {
		return this.code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public List<Feature> getFeature() {
		return this.feature;
	}

	public void setFeature(List<Feature> feature) {
		this.feature = feature;
	}

	public List<Genre> getGenre() {
		return this.genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<Link> getLink() {
		return this.link;
	}

	public void setLink(List<Link> link) {
		this.link = link;
	}

	public MovieType getMovieType() {
		return this.movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public List<Nationality> getNationality() {
		return this.nationality;
	}

	public void setNationality(List<Nationality> nationality) {
		this.nationality = nationality;
	}

	@JsonIgnore
	public List<News> getNews() {
		return this.news;
	}

	@JsonIgnore
	public void setNews(List<News> news) {
		this.news = news;
	}

	public String getOriginalTitle() {
		return this.originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Poster getPoster() {
		return this.poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public Number getProductionYear() {
		return this.productionYear;
	}

	public void setProductionYear(Number productionYear) {
		this.productionYear = productionYear;
	}

	public Release getRelease() {
		return this.release;
	}

	public void setRelease(Release release) {
		this.release = release;
	}

	public int getRuntime() {
		return this.runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Statistics getStatistics() {
		return this.statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
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
	public List<Code> getTag() {
		return this.tag;
	}

	@JsonIgnore
	public void setTag(List<Code> tag) {
		this.tag = tag;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Trailer getTrailer() {
		return this.trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	@JsonIgnore
	public List<Trivia> getTrivia() {
		return this.trivia;
	}

	@JsonIgnore
	public void setTrivia(List<Trivia> trivia) {
		this.trivia = trivia;
	}

	@JsonIgnore
	public Code getMovieCertificate() {
		return movieCertificate;
	}

	@JsonIgnore
	public void setMovieCertificate(Code movieCertificate) {
		this.movieCertificate = movieCertificate;
	}

	public Code getColor() {
		return color;
	}

	public void setColor(Code color) {
		this.color = color;
	}

	public FormatList getFormatList() {
		return formatList;
	}

	public void setFormatList(FormatList formatList) {
		this.formatList = formatList;
	}

	public List<Code> getLanguage() {
		return language;
	}

	public void setLanguage(List<Code> language) {
		this.language = language;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}
}
