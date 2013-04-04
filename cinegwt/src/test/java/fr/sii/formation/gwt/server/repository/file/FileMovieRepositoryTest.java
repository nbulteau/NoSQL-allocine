package fr.sii.formation.gwt.server.repository.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.allocine.repository.AlloCineRepository;
import fr.sii.formation.gwt.server.allocine.repository.RetrieveException;
import fr.sii.formation.gwt.server.allocine.service.ConvertException;

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

	@Test
	public void listMissingMovies() throws IOException, ConvertException {
		String allocinePath = "D:/allocine/";
		File jsonFile = null;
		String fileName;
		List<String> ids = new ArrayList<>();

		try {
			alloCineRepository.retrieveMovie(387);
		} catch (RetrieveException e) {
			System.out.println(e.getMessage());
		}

		for (int j = 1; j < 100000; j++) {
			fileName = allocinePath + 0 + "/" + j + ".json";
			jsonFile = new File(fileName);
			if (!jsonFile.exists()) {
				ids.add("" + j);
				System.out.println("Add ids : " + j);
				try {
					alloCineRepository.retrieveMovie(j);
				} catch (RetrieveException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		for (int i = 1; i < 3; i++) {
			for (int j = 0; j < 100000; j++) {
				fileName = allocinePath + i + "/" + j + ".json";
				if (!jsonFile.exists()) {
					ids.add("" + j);
					System.out.println("Add ids : " + j);
					try {
						alloCineRepository.retrieveMovie(j);
					} catch (RetrieveException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		System.out.println("List ids : " + ids.size());

	}

	public String getFileExtension(String nomFichier) {
		int posPoint = nomFichier.lastIndexOf('.');
		if (0 < posPoint && posPoint <= nomFichier.length() - 2) {
			return nomFichier.substring(posPoint + 1);
		}
		return "";
	}

	public String getFileName(String nomFichier) {
		int posPoint = nomFichier.lastIndexOf('.');
		if (0 < posPoint && posPoint <= nomFichier.length() - 2) {
			return nomFichier.substring(0, posPoint);
		}
		return nomFichier;
	}
}
