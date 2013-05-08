package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("distributor")
public class AlloCineDistributor {
	private String name;

	private Number code;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Number getCode() {
		return this.code;
	}

	public void setCode(Number code) {
		this.code = code;
	}

}
