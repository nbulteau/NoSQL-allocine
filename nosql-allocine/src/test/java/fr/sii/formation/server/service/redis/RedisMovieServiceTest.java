package fr.sii.formation.server.service.redis;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.service.MovieService;

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

}
