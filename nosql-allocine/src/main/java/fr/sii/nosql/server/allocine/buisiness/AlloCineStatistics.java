package fr.sii.nosql.server.allocine.buisiness;

import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;

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

}
