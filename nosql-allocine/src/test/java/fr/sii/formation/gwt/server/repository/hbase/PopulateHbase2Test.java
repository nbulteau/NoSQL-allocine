package fr.sii.formation.gwt.server.repository.hbase;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class PopulateHbase2Test {
	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileRepository;

	@Autowired
	@Qualifier("hbaseMovieRepository2")
	MovieRepository movieRepo;

	@Test
	public void updateMovies() throws IOException, MovieServiceException {
		long deb = System.currentTimeMillis();
		int index = 0;
		for (Movie movie : fileRepository.all()) {
			System.out.println("=> " + index++ + " : movie : " + movie.getTitle());
			movieRepo.save(movie);
		}

		long end = System.currentTimeMillis();
		System.out.println("updateMovies : " + (end - deb) + " for " + index + " movies");
	}
}
