package fr.sii.nosql.server.allocine.buisiness;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

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

	private AlloCineMovieCertificate alloCineMovieCertificate;

	private AlloCineCode color;

	private AlloCineFormatList alloCineFormatList;

	private List<AlloCineCode> language;

	private String budget;

	private String trailerEmbed;

	@JsonIgnore
	private List<Media> media;

	@JsonIgnore
	private List<BoxOffice> boxOffice;

	private boolean hasBluRay;

	private Date bluRayReleaseDate;

	private boolean hasDVD;

	private Date dvdReleaseDate;

	private boolean hasVOD;

	private boolean hasBroadcast;

	private Date nextBroadcast;

	private boolean hasShowtime;

	@JsonIgnore
	private List<AlloCineReview> helpfulPositiveReview;

	@JsonIgnore
	private List<AlloCineReview> helpfulNegativeReview;

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
	public AlloCineMovieCertificate getMovieCertificate() {
		return alloCineMovieCertificate;
	}

	@JsonIgnore
	public void setMovieCertificate(AlloCineMovieCertificate movieCertificate) {
		this.alloCineMovieCertificate = movieCertificate;
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

	public boolean isHasBluRay() {
		return hasBluRay;
	}

	public void setHasBluRay(boolean hasBluRay) {
		this.hasBluRay = hasBluRay;
	}

	public boolean isHasDVD() {
		return hasDVD;
	}

	public void setHasDVD(boolean hasDVD) {
		this.hasDVD = hasDVD;
	}

	public boolean isHasVOD() {
		return hasVOD;
	}

	public void setHasVOD(boolean hasVOD) {
		this.hasVOD = hasVOD;
	}

	public boolean isHasBroadcast() {
		return hasBroadcast;
	}

	public void setHasBroadcast(boolean hasBroadcast) {
		this.hasBroadcast = hasBroadcast;
	}

	@JsonIgnore
	public List<AlloCineReview> getHelpfulPositiveReview() {
		return helpfulPositiveReview;
	}

	@JsonIgnore
	public void setHelpfulPositiveReview(List<AlloCineReview> helpfulPositiveReview) {
		this.helpfulPositiveReview = helpfulPositiveReview;
	}

	@JsonIgnore
	public List<AlloCineReview> getHelpfulNegativeReview() {
		return helpfulNegativeReview;
	}

	@JsonIgnore
	public void setHelpfulNegativeReview(List<AlloCineReview> helpfulNegativeReview) {
		this.helpfulNegativeReview = helpfulNegativeReview;
	}

	public boolean isHasShowtime() {
		return hasShowtime;
	}

	public void setHasShowtime(boolean hasShowtime) {
		this.hasShowtime = hasShowtime;
	}

	public Date getDvdReleaseDate() {
		return dvdReleaseDate;
	}

	public void setDvdReleaseDate(Date dvdReleaseDate) {
		this.dvdReleaseDate = dvdReleaseDate;
	}

	public List<AlloCineCastMember> getAlloCineCastMember() {
		return alloCineCastMember;
	}

	public void setAlloCineCastMember(List<AlloCineCastMember> alloCineCastMember) {
		this.alloCineCastMember = alloCineCastMember;
	}

	public AlloCineCastingShort getAlloCineCastingShort() {
		return alloCineCastingShort;
	}

	public void setAlloCineCastingShort(AlloCineCastingShort alloCineCastingShort) {
		this.alloCineCastingShort = alloCineCastingShort;
	}

	public List<AlloCineFeature> getAlloCineFeature() {
		return alloCineFeature;
	}

	public void setAlloCineFeature(List<AlloCineFeature> alloCineFeature) {
		this.alloCineFeature = alloCineFeature;
	}

	public List<AlloCineGenre> getAlloCineGenre() {
		return alloCineGenre;
	}

	public void setAlloCineGenre(List<AlloCineGenre> alloCineGenre) {
		this.alloCineGenre = alloCineGenre;
	}

	public List<AlloCineLink> getAlloCineLink() {
		return alloCineLink;
	}

	public void setAlloCineLink(List<AlloCineLink> alloCineLink) {
		this.alloCineLink = alloCineLink;
	}

	public AlloCineMovieType getAlloCineMovieType() {
		return alloCineMovieType;
	}

	public void setAlloCineMovieType(AlloCineMovieType alloCineMovieType) {
		this.alloCineMovieType = alloCineMovieType;
	}

	public List<AlloCineNationality> getAlloCineNationality() {
		return alloCineNationality;
	}

	public void setAlloCineNationality(List<AlloCineNationality> alloCineNationality) {
		this.alloCineNationality = alloCineNationality;
	}

	public List<AlloCineNews> getAlloCineNews() {
		return alloCineNews;
	}

	public void setAlloCineNews(List<AlloCineNews> alloCineNews) {
		this.alloCineNews = alloCineNews;
	}

	public AlloCinePoster getAlloCinePoster() {
		return alloCinePoster;
	}

	public void setAlloCinePoster(AlloCinePoster alloCinePoster) {
		this.alloCinePoster = alloCinePoster;
	}

	public AlloCineRelease getAlloCineRelease() {
		return alloCineRelease;
	}

	public void setAlloCineRelease(AlloCineRelease alloCineRelease) {
		this.alloCineRelease = alloCineRelease;
	}

	public AlloCineStatistics getAlloCineStatistics() {
		return alloCineStatistics;
	}

	public void setAlloCineStatistics(AlloCineStatistics alloCineStatistics) {
		this.alloCineStatistics = alloCineStatistics;
	}

	public AlloCineTrailer getAlloCineTrailer() {
		return alloCineTrailer;
	}

	public void setAlloCineTrailer(AlloCineTrailer alloCineTrailer) {
		this.alloCineTrailer = alloCineTrailer;
	}

	public List<AlloCineTrivia> getAlloCineTrivia() {
		return alloCineTrivia;
	}

	public void setAlloCineTrivia(List<AlloCineTrivia> alloCineTrivia) {
		this.alloCineTrivia = alloCineTrivia;
	}

	public AlloCineMovieCertificate getAlloCineMovieCertificate() {
		return alloCineMovieCertificate;
	}

	public void setAlloCineMovieCertificate(AlloCineMovieCertificate alloCineMovieCertificate) {
		this.alloCineMovieCertificate = alloCineMovieCertificate;
	}

	public AlloCineFormatList getAlloCineFormatList() {
		return alloCineFormatList;
	}

	public void setAlloCineFormatList(AlloCineFormatList alloCineFormatList) {
		this.alloCineFormatList = alloCineFormatList;
	}

	public Date getBluRayReleaseDate() {
		return bluRayReleaseDate;
	}

	public void setBluRayReleaseDate(Date bluRayReleaseDate) {
		this.bluRayReleaseDate = bluRayReleaseDate;
	}

	public Date getNextBroadcast() {
		return nextBroadcast;
	}

	public void setNextBroadcast(Date nextBroadcast) {
		this.nextBroadcast = nextBroadcast;
	}
}
