package fr.sii.formation.gwt.server.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.sii.formation.gwt.server.allocine.service.AlloCineServiceImpl;

@Profile("jpa")
@Service("populateService")
public class PopulateServiceImpl implements PopulateService {

	private static final String HTTP_WWW_ALLOCINE_FR = "http://www.allocine.fr/film/fichefilm_gen_cfilm=";

	private final static Logger LOGGER = LoggerFactory.getLogger(AlloCineServiceImpl.class);

	@Autowired
	// @Qualifier("mongoDBMovieService")
	@Qualifier("jpaMovieService")
	private MovieService movieService;

	@Override
	public void populate(String inputFileName) throws MovieServiceException {
		FileInputStream fileInputStream = null;
		BufferedReader br = null;
		try {
			fileInputStream = new FileInputStream(inputFileName);
			br = new BufferedReader(new InputStreamReader(fileInputStream));

			String alloCineString = null;
			while ((alloCineString = br.readLine()) != null) {
				if (alloCineString.contains(HTTP_WWW_ALLOCINE_FR)) {
					String id = alloCineString.replace(HTTP_WWW_ALLOCINE_FR, "").replace(".html", "");

					try {
						movieService.retrieveAndSave(Long.parseLong(id), false);
					} catch (NumberFormatException e) {
						LOGGER.info("Wrong movie id : {}", id);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("populateDBs : ", e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error("populateDBs : ", e);
				}
			}
			try {
				br.close();
			} catch (IOException e) {
				LOGGER.error("populateDBs : ", e);
			}

		}

	}
}
