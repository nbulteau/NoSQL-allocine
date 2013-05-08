package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("person")
public class AlloCinePerson {
	private long code;

	private String name;

	public long getCode() {
		return this.code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
