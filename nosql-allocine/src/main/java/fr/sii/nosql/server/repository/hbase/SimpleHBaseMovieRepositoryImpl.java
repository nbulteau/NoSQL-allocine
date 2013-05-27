package fr.sii.nosql.server.repository.hbase;

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
import dataaccess.hbase.simple.HBaseTemplate;
import dataaccess.hbase.simple.serializers.HBaseSerializer;
import dataaccess.hbase.simple.serializers.JacksonJsonHBaseSerializer;
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("hbase")
@Repository("simpleHBaseMovieRepository")
public class SimpleHBaseMovieRepositoryImpl implements MovieRepository {

	private HBaseTemplate template;

	private HBaseTable<Long, Movie> moviesById;

	@Autowired
	private Configuration configuration;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() throws IOException {
		template = new HBaseTemplate(configuration);

		moviesById = new HBaseTable<Long, Movie>(template, "Movies", Long.class, Movie.class);
		HBaseSerializer<Movie> ser = new JacksonJsonHBaseSerializer<Movie>(Movie.class);
		// ser = new MovieSerializer();
		for (HBaseTable<?, Movie> t : new HBaseTable[] { moviesById })
			t.setValueSerializer(ser);
	}

	@Override
	public Movie save(Movie movie) {
		Long id = movie.getId();
		moviesById.put(id, movie);
		return movie;
	}

	@Override
	public void delete(Movie movie) {
		delete(movie.getId());
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
	public Movie findById(Long id) {
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
		moviesById.delete(id);
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
		List<Movie> result = new ArrayList<Movie>();
		return result;
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

	public long countByKinds(Kind kind) {
		return findByKinds(kind).size();
	}

}
