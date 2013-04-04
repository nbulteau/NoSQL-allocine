package fr.sii.formation.gwt.server.service.hbase;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.repository.file.FileMovieRepository;
import fr.sii.formation.gwt.server.service.MovieService;
import fr.sii.formation.gwt.server.service.MovieServiceException;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class HBaseMovieServiceTest {

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileRepository;

	@Autowired
	@Qualifier("hbaseMovieService")
	MovieService hbaseMovieService;

	@Test
	public void updateMovies() throws IOException, MovieServiceException {
		long nbMovies = fileRepository.count();
		System.out.println("nb movies  : " + nbMovies);
		/*
		 * int index = 0; for (Movie movie : fileRepository.all()) { System.out.println("=> " + index++ + " : movie : "
		 * + movie.getTitle()); hbaseMovieService.save(movie); }
		 */
	}
}
