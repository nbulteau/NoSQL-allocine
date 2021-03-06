package fr.sii.nosql.server.repository.hbase.optimised;

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
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Profile("hbase")
@Repository("hbaseMovieRepository")
public class HBaseMovieRepositoryImpl implements MovieRepository {

	private HBaseTemplate template;

	private HBaseTable<Long, Movie> moviesById;

	private HBaseTable1N.SL<Movie> moviesByTitle, moviesByTitleLike, moviesByActorName, moviesByDirectorName, moviesByKind;

	private HBaseTable1N.L2<Movie> moviesByActor, moviesByDirector;

	@Autowired
	private Configuration configuration;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() throws IOException {
		template = new HBaseTemplate(configuration);

		moviesById = new HBaseTable<Long, Movie>(template, "Movies", Long.class, Movie.class);
		moviesByTitle = new HBaseTable1N.SL<Movie>(template, "MoviesByTitle", Movie.class);
		moviesByTitleLike = new HBaseTable1N.SL<Movie>(template, "MoviesByTitleLike", Movie.class);
		moviesByTitleLike.setKeySerializer(new DefaultCompositeKeySerializer<String, Long>(new String8Serializer(), new LongKeySerializer()));
		moviesByActorName = new HBaseTable1N.SL<Movie>(template, "MoviesByActorName", Movie.class);
		moviesByDirectorName = new HBaseTable1N.SL<Movie>(template, "MoviesByDirectorName", Movie.class);
		moviesByActor = new HBaseTable1N.L2<Movie>(template, "MoviesByActor", Movie.class);
		moviesByDirector = new HBaseTable1N.L2<Movie>(template, "MoviesByDirector", Movie.class);
		moviesByKind = new HBaseTable1N.SL<Movie>(template, "MoviesByKind", Movie.class);
		HBaseSerializer<Movie> ser = new JacksonJsonHBaseSerializer<Movie>(Movie.class);
		// ser = new MovieSerializer();
		for (HBaseTable<?, Movie> t : new HBaseTable[] { moviesById, moviesByTitle, moviesByTitleLike, moviesByActorName, moviesByDirectorName, moviesByActor,
				moviesByDirector, moviesByKind })
			t.setValueSerializer(ser);
	}

	@Override
	public Movie save(Movie movie) {
		Long id = movie.getId();
		moviesById.put(id, movie);

		moviesByTitle.add(movie.getTitle(), id, movie);
		moviesByTitleLike.add(movie.getTitle(), id, movie);
		for (CastMember castMember : movie.getCastMembers()) {
			moviesByActorName.add(castMember.getPerson().getName(), id, movie);
			moviesByActor.add(castMember.getPerson().getId(), id, movie);
		}
		for (Person person : movie.getDirectors()) {
			moviesByDirectorName.add(person.getName(), id, movie);
			moviesByDirector.add(person.getId(), id, movie);
		}
		for (Kind k : movie.getKinds())
			moviesByKind.add(k.name(), id, movie);

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
		Movie movie = findById(id);
		moviesById.delete(id);
		moviesByTitle.remove(movie.getTitle(), id);
		for (CastMember castMember : movie.getCastMembers()) {
			moviesByActorName.remove(castMember.getPerson().getName(), id);
			moviesByActor.remove(castMember.getPerson().getId(), id);
		}
		for (Person person : movie.getDirectors()) {
			moviesByDirectorName.remove(person.getName(), id);
			moviesByDirector.remove(person.getId(), id);
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

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
		return moviesByActor.getList(id);
	}

	@Override
	public List<Movie> findByActor(String name) {
		return moviesByActorName.getList(name);
	}

	@Override
	public List<Movie> findByDirector(long id) {
		return moviesByDirector.getList(id);
	}

	@Override
	public List<Movie> findByDirector(String name) {
		return moviesByDirectorName.getList(name);
	}

	@Override
	public List<Movie> findByKinds(Kind kind) {
		return moviesByKind.getList(kind.name());
	}

	public long countByKinds(Kind kind) {
		return findByKinds(kind).size();
	}

}
