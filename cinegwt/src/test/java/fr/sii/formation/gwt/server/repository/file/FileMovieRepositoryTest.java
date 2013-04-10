package fr.sii.formation.gwt.server.repository.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.allocine.repository.AlloCineRepository;
import fr.sii.formation.gwt.server.allocine.service.AlloCineService;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("file")
public class FileMovieRepositoryTest {

	@Autowired
	AlloCineRepository alloCineRepository;

	@Autowired
	FileMovieRepository movieRepository;

	@Value("${filerepo.path}")
	private String repositoryPath;

	@Autowired
	AlloCineService alloCineService;

	@Test
	// @Ignore
	public void populate() throws InterruptedException {

		Movie movie = null;

		long deb = System.currentTimeMillis();

		List<String> ids = listMissingMovies();

		long end = System.currentTimeMillis();
		System.out.println("listMissingMovies : " + (end - deb));

		// L'ordre d'itération des éléments est aléatoire dans un hashset
		Set<String> hashSet = new HashSet<>();
		for (String id : ids) {
			hashSet.add(id);
		}
		for (Iterator<String> iterator = hashSet.iterator(); iterator.hasNext();) {
			String string = iterator.next();

			movie = alloCineService.retrieveMovie(Long.parseLong(string));
			if (movie != null) {
				System.out.println(movie.getTitle());
			}
			Thread.sleep(5000);
		}

	}

	private List<String> listMissingMovies() {
		String allocinePath = "D:/allocine/";
		File jsonFile = null;
		String fileName;
		List<String> ids = new ArrayList<>();

		for (int j = 1; j < 100000; j++) {
			fileName = allocinePath + 0 + "/" + j + ".json";
			jsonFile = new File(fileName);
			if (!jsonFile.exists()) {
				ids.add("" + j);
			}
		}
		for (int i = 1; i < 3; i++) {
			int deb = i * 100000;
			int end = (i + 1) * 100000;
			for (int j = deb; j < end; j++) {
				fileName = allocinePath + i + "/" + j + ".json";
				jsonFile = new File(fileName);
				if (!jsonFile.exists()) {

					ids.add("" + j);
				}
			}
		}
		return ids;
	}

	private String getFileExtension(String nomFichier) {
		int posPoint = nomFichier.lastIndexOf('.');
		if (0 < posPoint && posPoint <= nomFichier.length() - 2) {
			return nomFichier.substring(posPoint + 1);
		}
		return "";
	}

	private String getFileName(String nomFichier) {
		int posPoint = nomFichier.lastIndexOf('.');
		if (0 < posPoint && posPoint <= nomFichier.length() - 2) {
			return nomFichier.substring(0, posPoint);
		}
		return nomFichier;
	}
}
