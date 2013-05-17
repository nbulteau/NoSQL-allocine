package fr.sii.formation.gwt.server.repository.file;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("mongodb")
public class FileMovieRepositoryTest {

	
	@Autowired
	private FileMovieRepository fileMovieRepository;

	@Autowired
	private MovieService movieService;

	@Test
	public void populate() throws InterruptedException, MovieServiceException {

		Iterable<Movie> iterable = fileMovieRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, false);
			index++;
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("populate " + index + " movies in "+ (end - deb) + " ms");
	}
}
