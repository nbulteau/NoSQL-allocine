package fr.sii.nosql.server.allocine.buisiness;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("statistics")
public class AlloCineStatistics {
	public AlloCineStatistics() {

	}

	// workaround for allocine bug
	// JSON occasionally comes from the server as "statistics" : ""
	// which is just plain stupid and makes jackson complain.
	public AlloCineStatistics(String dummy) {

	}

	private float pressRating;

	private int pressReviewCount;

	private float userRating;

	private int userReviewCount;

	private int userRatingCount;

	private int commentCount;

	private int photoCount;

	private int videoCount;

	private List<AlloCineRating> alloCineRating;

	private int fanCount;

	private int releaseWeekPosition;

	private int theaterCount;

	private int theaterCountOnRelease;

	private int editorialRatingCount;

	private int wantToSeeCount;

	private int triviaCount;

	private int rankTopMovie;

	private int variationTopMovie;

	private int admissionCount;

	private int newsCount;

	private int awardCount;

	private int nominationCount;

	public float getPressRating() {
		return pressRating;
	}

	public void setPressRating(float pressRating) {
		this.pressRating = pressRating;
	}

	public int getPressReviewCount() {
		return pressReviewCount;
	}

	public void setPressReviewCount(int pressReviewCount) {
		this.pressReviewCount = pressReviewCount;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

	public int getUserReviewCount() {
		return userReviewCount;
	}

	public void setUserReviewCount(int userReviewCount) {
		this.userReviewCount = userReviewCount;
	}

	public int getUserRatingCount() {
		return userRatingCount;
	}

	public void setUserRatingCount(int userRatingCount) {
		this.userRatingCount = userRatingCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public List<AlloCineRating> getRating() {
		return alloCineRating;
	}

	public void setRating(List<AlloCineRating> alloCineRating) {
		this.alloCineRating = alloCineRating;
	}

	public int getFanCount() {
		return fanCount;
	}

	public void setFanCount(int fanCount) {
		this.fanCount = fanCount;
	}

	public int getReleaseWeekPosition() {
		return releaseWeekPosition;
	}

	public void setReleaseWeekPosition(int releaseWeekPosition) {
		this.releaseWeekPosition = releaseWeekPosition;
	}

	public int getTheaterCount() {
		return theaterCount;
	}

	public void setTheaterCount(int theaterCount) {
		this.theaterCount = theaterCount;
	}

	public int getTheaterCountOnRelease() {
		return theaterCountOnRelease;
	}

	public void setTheaterCountOnRelease(int theaterCountOnRelease) {
		this.theaterCountOnRelease = theaterCountOnRelease;
	}

	public int getEditorialRatingCount() {
		return editorialRatingCount;
	}

	public void setEditorialRatingCount(int editorialRatingCount) {
		this.editorialRatingCount = editorialRatingCount;
	}

	public int getWantToSeeCount() {
		return wantToSeeCount;
	}

	public void setWantToSeeCount(int wantToSeeCount) {
		this.wantToSeeCount = wantToSeeCount;
	}

	public int getTriviaCount() {
		return triviaCount;
	}

	public void setTriviaCount(int triviaCount) {
		this.triviaCount = triviaCount;
	}

	public List<AlloCineRating> getAlloCineRating() {
		return alloCineRating;
	}

	public void setAlloCineRating(List<AlloCineRating> alloCineRating) {
		this.alloCineRating = alloCineRating;
	}

	public int getRankTopMovie() {
		return rankTopMovie;
	}

	public void setRankTopMovie(int rankTopMovie) {
		this.rankTopMovie = rankTopMovie;
	}

	public int getVariationTopMovie() {
		return variationTopMovie;
	}

	public void setVariationTopMovie(int variationTopMovie) {
		this.variationTopMovie = variationTopMovie;
	}

	public int getAdmissionCount() {
		return admissionCount;
	}

	public void setAdmissionCount(int admissionCount) {
		this.admissionCount = admissionCount;
	}

	public int getNewsCount() {
		return newsCount;
	}

	public void setNewsCount(int newsCount) {
		this.newsCount = newsCount;
	}

	public int getAwardCount() {
		return awardCount;
	}

	public void setAwardCount(int awardCount) {
		this.awardCount = awardCount;
	}

	public int getNominationCount() {
		return nominationCount;
	}

	public void setNominationCount(int nominationCount) {
		this.nominationCount = nominationCount;
	}

}
