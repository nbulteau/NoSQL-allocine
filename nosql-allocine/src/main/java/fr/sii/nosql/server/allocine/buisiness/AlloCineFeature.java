package fr.sii.nosql.server.allocine.buisiness;

import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("feature")
public class AlloCineFeature {
	private List<AlloCineCode> category;

	private Number code;

	private AlloCinePicture alloCinePicture;

	private AlloCinePublication alloCinePublication;

	private String title;

	public List<AlloCineCode> getCategory() {
		return this.category;
	}

	public void setCategory(List<AlloCineCode> category) {
		this.category = category;
	}

	public Number getCode() {
		return this.code;
	}

	public void setCode(Number code) {
		this.code = code;
	}

	public AlloCinePicture getPicture() {
		return this.alloCinePicture;
	}

	public void setPicture(AlloCinePicture alloCinePicture) {
		this.alloCinePicture = alloCinePicture;
	}

	public AlloCinePublication getPublication() {
		return this.alloCinePublication;
	}

	public void setPublication(AlloCinePublication alloCinePublication) {
		this.alloCinePublication = alloCinePublication;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
