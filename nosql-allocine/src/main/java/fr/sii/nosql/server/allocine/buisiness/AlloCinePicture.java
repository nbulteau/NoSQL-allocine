package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("picture")
public class AlloCinePicture extends AlloCinePoster {

	private String path;

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
