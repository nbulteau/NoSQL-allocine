package fr.sii.nosql.server.allocine.buisiness;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("code")
public class AlloCineCode {
	private String $;

	private int code;

	public String get$() {
		return this.$;
	}

	public void set$(String $) {
		this.$ = $;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
