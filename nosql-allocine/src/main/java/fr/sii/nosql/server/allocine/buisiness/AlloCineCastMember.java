package fr.sii.nosql.server.allocine.buisiness;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("castmember")
public class AlloCineCastMember {
	private AlloCineCode activity;

	private AlloCinePerson alloCinePerson;

	private String role;

	private AlloCinePicture alloCinePicture;

	public AlloCineCode getActivity() {
		return this.activity;
	}

	public void setActivity(AlloCineCode activity) {
		this.activity = activity;
	}

	public AlloCinePerson getPerson() {
		return this.alloCinePerson;
	}

	public void setPerson(AlloCinePerson alloCinePerson) {
		this.alloCinePerson = alloCinePerson;
	}

	public AlloCinePicture getPicture() {
		return this.alloCinePicture;
	}

	public void setPicture(AlloCinePicture alloCinePicture) {
		this.alloCinePicture = alloCinePicture;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
