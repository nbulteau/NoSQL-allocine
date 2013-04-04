package fr.sii.formation.gwt.shared.buisiness;

import java.io.Serializable;

public class Actor implements Serializable {

	private static final long serialVersionUID = 1L;

	private String role;

	private Person person;

	public Actor() {
		super();
	}

	public Actor(Person person, String role) {
		super();
		this.person = person;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Actor [role=" + role + ", person=" + person + "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
