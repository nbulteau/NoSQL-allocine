package fr.sii.nosql.server.service.file;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Service("fileMovieService")
public class FileMovieServiceImpl implements MovieService {

	private final static Logger LOGGER = LoggerFactory.getLogger(FileMovieServiceImpl.class);

	private final FileMovieRepository fileMovieRepository;

	private final AlloCineService alloCineService;

	@Autowired(required = true)
	public FileMovieServiceImpl(FileMovieRepository fileMovieRepository, AlloCineService alloCineService) {
		super();
		this.fileMovieRepository = fileMovieRepository;
		this.alloCineService = alloCineService;
	}

	@Override
	public boolean exists(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movie findById(long id) {
		return fileMovieRepository.findOne(id);
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByTitleLike(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByActor(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByActor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByDirector(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByDirector(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Movie movie) throws MovieServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countByKind(Kind kind) {
		// TODO Auto-generated method stub
		return 0;
	}

}
