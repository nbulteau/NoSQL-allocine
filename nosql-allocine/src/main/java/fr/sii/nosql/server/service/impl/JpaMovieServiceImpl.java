package fr.sii.nosql.server.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.file.FilePhotoRepository;
import fr.sii.nosql.server.repository.file.FilePosterRepository;
import fr.sii.nosql.server.repository.jpa.optimised.JpaMovieRepository;
import fr.sii.nosql.server.repository.jpa.optimised.JpaPersonRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Profile("jpa")
@Service("jpaMovieService")
public class JpaMovieServiceImpl extends MovieServiceImpl implements MovieService {

	private final JpaPersonRepository personRepository;

	@Autowired
	public JpaMovieServiceImpl(AlloCineService alloCineService, JpaMovieRepository jpaMovieRepository, JpaPersonRepository jpaPersonRepository,
			FilePosterRepository filePosterRepository, FilePhotoRepository filePhotoRepository) {
		super(alloCineService, filePosterRepository, filePhotoRepository);
		setMovieRepository(jpaMovieRepository);
		this.personRepository = jpaPersonRepository;
	}

	@Override
	@Transactional
	public void save(Movie movie, boolean isDownloadPictures) throws MovieServiceException {
		LOGGER.info("save : {} : {}", movie.getId(), movie.getTitle());

		invariant();

		if (movie.getId() == 0) {
			throw new MovieServiceException("movie id == 0");
		}

		if (isDownloadPictures) {
			// poster
			savePosters(movie);
			// actors and directiors picture
			savePictures(movie);
		}

		Set<Person> directors = new HashSet<>();
		for (Person director : movie.getDirectors()) {
			Person person = personRepository.findOne(director.getId());
			if (person == null) {
				director = personRepository.save(director);
				directors.add(director);
			} else {
				directors.add(person);
			}
		}
		movie.setDirectors(directors);

		for (CastMember castMember : movie.getCastMembers()) {
			Person person = personRepository.findOne(castMember.getPerson().getId());
			if (person != null) {
				person = personRepository.save(person);
				castMember.setPerson(person);
			}
		}

		// insert movie into db
		movieRepository.save(movie);
	}
}
