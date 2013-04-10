package fr.sii.nosql.server.repository.hbase.complex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import dataaccess.hbase.simple.HBaseTable;
import dataaccess.hbase.simple.HBaseTable1N;
import dataaccess.hbase.simple.HBaseTemplate;
import dataaccess.hbase.simple.serializers.DefaultCompositeKeySerializer;
import dataaccess.hbase.simple.serializers.HBaseSerializer;
import dataaccess.hbase.simple.serializers.JacksonJsonHBaseSerializer;
import dataaccess.hbase.simple.serializers.LongKeySerializer;
import dataaccess.hbase.simple.serializers.String8Serializer;
import fr.sii.nosql.server.repository.hbase.HBaseMovieRepository;
import fr.sii.nosql.shared.MovieFilter;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("hbase")
@Repository("hbaseMovieRepository2")
public class HBaseMovieRepositoryComplexImpl implements HBaseMovieRepository {

	@Autowired
	private Configuration configuration;
	private HBaseTemplate template;
	private HBaseTable<Long, Movie> moviesById;
	private HBaseTable1N.SL<Movie> moviesByTitle, moviesByTitleLike;
	@Autowired
	private HBaseActorRepo actorRepo;
	@Autowired
	private HBaseActorNameRepo actorNameRepo;
	@Autowired
	private HBaseDirectorRepo directorRepo;
	@Autowired
	private HBaseDirectorNameRepo directorNameRepo;
	@Autowired
	private HBaseKindRepo kindRepo;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() throws IOException {
		template = new HBaseTemplate(configuration);
		HBaseSerializer<Movie> ser = new JacksonJsonHBaseSerializer<Movie>(Movie.class);

		moviesById = new HBaseTable<Long, Movie>(template, "Movies", Long.class, Movie.class);
		moviesByTitle = new HBaseTable1N.SL<Movie>(template, "MoviesByTitle", Movie.class);
		moviesByTitleLike = new HBaseTable1N.SL<Movie>(template, "MoviesByTitleLike", Movie.class);
		moviesByTitleLike.setKeySerializer(new DefaultCompositeKeySerializer<String, Long>(new String8Serializer(),
				new LongKeySerializer()));
		for (HBaseTable<?, Movie> t : new HBaseTable[] { moviesById, moviesByTitle, moviesByTitleLike })
			t.setValueSerializer(ser);
	}

	@Override
	public void save(Movie movie) {
		// Long id = movie.getId();
		// moviesById.put(id, movie);
		// moviesByTitle.add(movie.getTitle(), id, movie);
		// moviesByTitleLike.add(movie.getTitle(), id, movie);
		actorRepo.addMovie(movie);
		actorNameRepo.addMovie(movie);
		directorRepo.addMovie(movie);
		directorNameRepo.addMovie(movie);
		kindRepo.addMovie(movie);
	}

	@Override
	public void delete(Movie movie) {
		throw new UnsupportedOperationException("not needed");
	}

	@Override
	public List<Movie> findAll() {
		throw new UnsupportedOperationException("too many movies");
	}

	@Override
	public List<Movie> findAll(Sort sort) {
		throw new UnsupportedOperationException("too many movies");
	}

	@Override
	public Page<Movie> findAll(Pageable pageable) {
		throw new UnsupportedOperationException("not needed");
	}

	@Override
	public Movie findOne(Long id) {
		return moviesById.get(id);
	}

	@Override
	public boolean exists(Long id) {
		return moviesById.exists(id);
	}

	@Override
	public long count() {
		return moviesById.count();
	}

	@Override
	public void delete(Long id) {
		throw new UnsupportedOperationException("not needed");
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException("bad idea");
	}

	@Override
	public List<Movie> findByTitle(String title) {
		return moviesByTitle.getList(title);
	}

	@Override
	public List<Movie> findByTitleLike(String string) {
		List<Movie> list = moviesByTitleLike.getList(string);
		List<Movie> result = new ArrayList<Movie>();
		for (Movie m : list)
			if (m.getTitle().startsWith(string))
				result.add(m);
		return result;
	}

	@Override
	public List<Movie> findByActor(long id) {
		return actorRepo.getMovies(id);
	}

	@Override
	public List<Movie> findByActor(String name) {
		return actorNameRepo.getMovies(name);
	}

	@Override
	public List<Movie> findByDirector(long id) {
		return directorRepo.getMovies(id);
	}

	@Override
	public List<Movie> findByDirector(String name) {
		return directorNameRepo.getMovies(name);
	}

	@Override
	public List<Movie> findByKind(MovieFilter movieFilter) {
		return kindRepo.getMovies(movieFilter.getKind().name());
	}

	@Override
	public long countByKind(MovieFilter movieFilter) {
		return kindRepo.count(movieFilter.getKind().name());
	}
}
