package fr.sii.nosql.server.allocine.buisiness;

public class CastMember
{
	private Code activity;

	private Person person;

	private String role;

	private Picture picture;

	public Code getActivity()
	{
		return this.activity;
	}

	public void setActivity(Code activity)
	{
		this.activity = activity;
	}

	public Person getPerson()
	{
		return this.person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	public Picture getPicture()
	{
		return this.picture;
	}

	public void setPicture(Picture picture)
	{
		this.picture = picture;
	}

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}
}
