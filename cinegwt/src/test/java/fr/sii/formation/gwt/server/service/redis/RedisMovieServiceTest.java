package fr.sii.formation.gwt.server.service.redis;

import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.service.MovieService;
import fr.sii.formation.gwt.server.service.MovieServiceException;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("redis")
public class RedisMovieServiceTest {

	@Autowired
	@Qualifier("mongoDBMovieService")
	MovieService mongoDBMovieService;

	@Autowired
	@Qualifier("redisMovieService")
	MovieService redisMovieService;

	@Test
	public void updateMovies() throws IOException, MovieServiceException {
		long nbMovies = mongoDBMovieService.getMoviesCount(null);
		System.out.println("nb movies  : " + nbMovies);

		int step = 100;
		int count = 0;
		int index = count * step;
		while (count < nbMovies) {
			List<Movie> movies = mongoDBMovieService.fetchMovies(count, step, null);
			for (Movie movie : movies) {
				System.out.println("=> " + index++ + " : movie : " + movie.getTitle());
				redisMovieService.save(movie);
			}
			count++;
		}
	}
}
