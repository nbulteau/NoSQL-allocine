package fr.sii.nosql.server.allocine.buisiness;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("movieCertificate")
public class AlloCineMovieCertificate {
	private AlloCineCode certificate;

	public AlloCineCode getCertificate() {
		return certificate;
	}

	public void setCertificate(AlloCineCode certificate) {
		this.certificate = certificate;
	}

}
