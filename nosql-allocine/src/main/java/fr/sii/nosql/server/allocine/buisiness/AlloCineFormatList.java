package fr.sii.nosql.server.allocine.buisiness;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("formatList")
public class AlloCineFormatList {

	private List<AlloCineCode> productionFormat;

	private List<AlloCineCode> projectionFormat;

	private List<AlloCineCode> soundFormat;

	public List<AlloCineCode> getProductionFormat() {
		return productionFormat;
	}

	public void setProductionFormat(List<AlloCineCode> productionFormat) {
		this.productionFormat = productionFormat;
	}

	public List<AlloCineCode> getProjectionFormat() {
		return projectionFormat;
	}

	public void setProjectionFormat(List<AlloCineCode> projectionFormat) {
		this.projectionFormat = projectionFormat;
	}

	public List<AlloCineCode> getSoundFormat() {
		return soundFormat;
	}

	public void setSoundFormat(List<AlloCineCode> soundFormat) {
		this.soundFormat = soundFormat;
	}

}
