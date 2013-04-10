package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("rating")
public class AlloCineRating {

	private float note;

	private int $;

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public int get$() {
		return $;
	}

	public void set$(int $) {
		this.$ = $;
	}

}
