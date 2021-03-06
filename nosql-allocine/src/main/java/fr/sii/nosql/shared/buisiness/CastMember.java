package fr.sii.nosql.shared.buisiness;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class CastMember implements Serializable {

	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String role;

	@OneToOne(cascade = { CascadeType.MERGE })
	private Person person;

	public CastMember() {
		super();
	}

	public CastMember(Person person, String role) {
		super();
		this.person = person;
		this.role = role;
	}

	@Override
	public String toString() {
		return "CastMember [role=" + role + ", person=" + person + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
