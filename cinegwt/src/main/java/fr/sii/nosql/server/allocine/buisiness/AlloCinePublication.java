package fr.sii.nosql.server.allocine.buisiness;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("publication")
public class AlloCinePublication {

	private Date dateStart;

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

}
