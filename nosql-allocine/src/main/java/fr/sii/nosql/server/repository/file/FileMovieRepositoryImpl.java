package fr.sii.nosql.server.repository.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import fr.sii.nosql.server.allocine.buisiness.MovieResult;
import fr.sii.nosql.server.allocine.service.ConvertException;
import fr.sii.nosql.server.allocine.service.MovieMapperService;
import fr.sii.nosql.shared.buisiness.Movie;

@Repository("fileMovieRepository")
public class FileMovieRepositoryImpl implements FileMovieRepository {
	private static final String JSON_SUFFIX = ".json";

	private final static Logger LOG = LoggerFactory.getLogger(FileMovieRepositoryImpl.class);

	@Value("${filerepo.path}")
	private String repositoryPath;

	@Autowired
	private MovieMapperService movieMapperService;

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Map<Long, Movie> movies = new HashMap<Long, Movie>();

	public String getRepositoryPath() {
		return repositoryPath;
	}

	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}

	public MovieMapperService getMovieMapperService() {
		return movieMapperService;
	}

	public void setMovieMapperService(MovieMapperService movieMapperService) {
		this.movieMapperService = movieMapperService;
	}

	@PostConstruct
	public void init() throws IOException, ConvertException {
		LOG.debug("repo is " + getRepositoryPath());

	}

	@Override
	public long count() {
		List<File> files = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			File dir = new File(repositoryPath + File.separator + i);
			files.addAll(Arrays.asList(dir.listFiles()));
		}
		return files.size();
	}

	@Override
	public Iterable<Movie> all() {
		final List<File> files = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			File dir = new File(repositoryPath + File.separator + i);
			files.addAll(Arrays.asList(dir.listFiles()));
		}

		return new Iterable<Movie>() {
			@Override
			public Iterator<Movie> iterator() {
				return new Iterator<Movie>() {
					int i = 0;// next movie index

					@Override
					public boolean hasNext() {
						return i < files.size();
					}

					@Override
					public Movie next() {
						try {
							return load(files.get(i++), false);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}

	@Override
	public Collection<Movie> findAll() {
		return movies.values();
	}

	@Override
	public Movie findOne(Long id) {
		Movie movie = null;
		File file = getFile(id);
		if (file != null) {
			movie = load(file, false);
		}
		return movie;
	}

	@Override
	public void add(long id, String body) {
		File file = getFile(id);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(body);
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(long id, MovieResult movie) {

		File file = getFile(id);
		try {
			objectMapper.writeValue(file, movie);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File getFile(Long id) {
		return new File(getRepositoryPath() + File.separator + (id / 100000) + File.separator + id + JSON_SUFFIX);
	}

	private Movie load(File f, boolean cache) {
		try {
			MovieResult alloMovie = objectMapper.readValue(f, MovieResult.class);
			Movie movie = getMovieMapperService().convertToBuisinessObject(alloMovie.getMovie());
			if (cache) {
				movies.put(movie.getId(), movie);
			}
			return movie;
		} catch (Exception e) {
			LOG.info("was loading " + f.getName(), e);
			return null;
		}
	}

}