package fr.sii.nosql.server.repository.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import fr.sii.nosql.server.allocine.buisiness.MovieResult;
import fr.sii.nosql.server.allocine.service.MovieMapperService;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Repository("fileMovieRepository")
public class FileMovieRepositoryImpl extends FileRepository<Movie> implements FileMovieRepository {

	private final static Logger LOG = LoggerFactory.getLogger(FileMovieRepositoryImpl.class);

	private final MovieMapperService movieMapperService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	public FileMovieRepositoryImpl(MovieMapperService movieMapperService, @Value("${filemovies.path}") String repositoryPath,
			@Value("${filemovies.suffix}") String suffix) {
		super(repositoryPath, suffix);
		this.movieMapperService = movieMapperService;
	}

	@Override
	public void save(Long id, MovieResult movie) {

		File file = getFile(id);
		try {
			objectMapper.writeValue(file, movie);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Movie load(File f) {
		try {
			MovieResult alloMovie = objectMapper.readValue(f, MovieResult.class);
			Movie movie = movieMapperService.convertToBuisinessObject(alloMovie.getMovie());
			return movie;
		} catch (Exception e) {
			LOG.info("was loading " + f.getName(), e);
			return null;
		}
	}

	@Override
	public Movie findById(Long id) {
		File file = getFile(id);

		return load(file);
	}

	@Override
	public void delete(Long id) {
		File file = getFile(id);
		file.delete();
	}

	// MovieRepository

	@Override
	public Movie save(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Movie movie) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Movie> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Movie> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

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
	public List<Movie> findByKinds(Kind kind) {
		// TODO Auto-generated method stub
		return null;
	}

}
