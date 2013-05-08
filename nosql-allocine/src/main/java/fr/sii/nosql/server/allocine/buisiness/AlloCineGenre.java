package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("genre")
public class AlloCineGenre {
	private String $;

	private Number code;

	public String get$() {
		return $;
	}

	public void set$(String $) {
		this.$ = $;
	}

	public Number getCode() {
		return code;
	}

	public void setCode(Number code) {
		this.code = code;
	}

}
