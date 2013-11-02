package fr.sii.nosql.server.allocine.buisiness;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("news")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlloCineNews {

}
