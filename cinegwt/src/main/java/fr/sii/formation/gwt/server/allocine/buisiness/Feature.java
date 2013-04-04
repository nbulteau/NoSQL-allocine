package fr.sii.formation.gwt.server.allocine.buisiness;

import java.util.List;

public class Feature
{
	private List<Code> category;

	private Number code;

	private Picture picture;

	private Publication publication;

	private String title;

	public List<Code> getCategory()
	{
		return this.category;
	}

	public void setCategory(List<Code> category)
	{
		this.category = category;
	}

	public Number getCode()
	{
		return this.code;
	}

	public void setCode(Number code)
	{
		this.code = code;
	}

	public Picture getPicture()
	{
		return this.picture;
	}

	public void setPicture(Picture picture)
	{
		this.picture = picture;
	}

	public Publication getPublication()
	{
		return this.publication;
	}

	public void setPublication(Publication publication)
	{
		this.publication = publication;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
}
