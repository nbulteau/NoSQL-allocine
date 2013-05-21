package fr.sii.nosql.server.repository.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Repository;

import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Profile("redis")
@Repository("redisMovieRepository")
public class RedisMovieRepositoryImpl implements RedisMovieRepository {

	private final class MovieComparator implements Comparator<Movie> {
		@Override
		public int compare(Movie movie1, Movie movie2) {
			// this optimization is usually worthwhile, and can
			// always be added
			if (movie1 == movie2) {
				return 0;
			}
			int comparison = movie1.getTitle().compareTo(movie2.getTitle());
			if (comparison != 0) {
				return comparison;
			}
			return 0;
		}
	}

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	@Resource(name="redisTemplate")
	RedisTemplate<String, Movie> movieTemplate = null;

	@PostConstruct
	protected void init() {
//		JacksonJsonRedisSerializer<Movie> jsonSerializer = new JacksonJsonRedisSerializer<Movie>(
//				Movie.class);
		RedisSerializer<String> serializer = new RedisSerializer<String>() {

			@Override
			public byte[] serialize(String t) throws SerializationException {
				return t.getBytes();
			}

			@Override
			public String deserialize(byte[] bytes)
					throws SerializationException {
				return new String(bytes);
			}
		};

//		movieTemplate = new RedisTemplate<String, Movie>();
//		movieTemplate.setConnectionFactory(jedisConnectionFactory);
//		movieTemplate.setValueSerializer(jsonSerializer);
		movieTemplate.setKeySerializer(serializer);
		movieTemplate.setExposeConnection(true);
		movieTemplate.afterPropertiesSet();
	}

	@Override
	public Movie save(Movie movie) {
		String movieKey = KeyUtils.movie(movie.getId());

		// movieTemplate.multi();

		// FIXME PAB : atomic get and set
		if (movieTemplate.opsForValue().get(movieKey) == null) {
			movieTemplate.opsForValue().increment(KeyUtils.moviesCount(), 1);
		}
		movieTemplate.opsForValue().set(movieKey, movie);

//		for (CastMember castMember : movie.getCastMembers()) {
//			String actorKey = KeyUtils.actor(castMember.getPerson().getId());
//			movieTemplate.opsForSet().add(actorKey, movie);
//			String actorName = KeyUtils.actorName(castMember.getPerson()
//					.getName());
//			movieTemplate.opsForSet().add(actorName, movie);
//		}
//
//		for (Person person : movie.getDirectors()) {
//			String directorKey = KeyUtils.director(person.getId());
//			movieTemplate.opsForSet().add(directorKey, movie);
//			String directorName = KeyUtils.directorName(person.getName());
//			movieTemplate.opsForSet().add(directorName, movie);
//		}
//
//		for (Kind kind : movie.getKinds()) {
//			String kindKey = KeyUtils.kind(kind.getLabel());
//			movieTemplate.opsForSet().add(kindKey, movie);
//		}
		return movie;

		// movieTemplate.exec();
	}

	@Override
	public Movie findById(Long id) {
		String key = KeyUtils.movie(id);
		return movieTemplate.opsForValue().get(key);
	}

	@Override
	public void delete(Movie movie) {
		String key = KeyUtils.movie(movie.getId());
		movieTemplate.opsForValue().getOperations().delete(key);

		// TODO check movie exists and then decrement KeyUtils.moviesCount()
		for (CastMember castMember : movie.getCastMembers()) {
			String actorKey = KeyUtils.actor(castMember.getPerson().getId());
			movieTemplate.opsForHash().getOperations().delete(actorKey);
		}
		for (Person person : movie.getDirectors()) {
			String directorKey = KeyUtils.director(person.getId());
			movieTemplate.opsForSet().getOperations().delete(directorKey);
		}

		for (Kind kind : movie.getKinds()) {
			String kindKey = KeyUtils.kind(kind.getLabel());
			movieTemplate.opsForSet().getOperations().delete(kindKey);
		}
	}

	private List<Movie> findAll() {
		Collection<byte[]> keys = getConnection().keys(
				KeyUtils.MOVIE.getBytes());
		Collection<String> movieKeys = new ArrayList<String>();
		for (byte[] key : keys) {
			movieKeys.add(new String(key));
		}

		return movieTemplate.opsForValue().multiGet(movieKeys);
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
	public boolean exists(Long id) {
		String key = KeyUtils.movie(id);
		return getConnection().exists(key.getBytes());
	}

	private RedisConnection getConnection() {
		return movieTemplate.getConnectionFactory().getConnection();
	}

	@Override
	public long count() {
		String result = new String(getConnection().get(
				KeyUtils.MOVIES_COUNT.getBytes()));
		return Long.parseLong(result);
	}

	@Override
	public long countKinds() {
		Set<byte[]> keys = getConnection().keys(KeyUtils.kind("*").getBytes());
		return keys.size();
	}

	@Override
	public long countMoviesWithQuery(Kind kind) {
		return findByKinds(kind).size();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Movie> findByTitle(final String title) {
		return findInAllMovies(new Predicate<Movie>() {
			@Override
			public boolean apply(Movie m) {
				String t = m.getTitle();
				return t != null && t.equals(title);
			}
		});
	}

	@Override
	public List<Movie> findByTitleLike(final String string) {
		return findInAllMovies(new Predicate<Movie>() {
			@Override
			public boolean apply(Movie m) {
				String t = m.getTitle();
				return t != null && t.startsWith(string);
			}
		});
	}

	@Override
	public List<Movie> findByActor(long id) {
		return findByKeyOrdered(KeyUtils.actor(id));
	}

	private List<Movie> findByKeyOrdered(String key) {
		List<Movie> movies = new ArrayList<Movie>(movieTemplate.opsForSet()
				.members(key));
		Collections.sort(movies, new MovieComparator());
		return movies;
	}

	@Override
	public List<Movie> findByActor(final String name) {
		return findByKeyOrdered(KeyUtils.actorName(name));
		// return findInAllMovies(new Predicate<AlloCineMovie>() {
		// @Override
		// public boolean apply(AlloCineMovie t) {
		// for (AlloCineCastMember a : t.getActors())
		// if (name.equals(a.getPerson().getName()))
		// return true;
		// return false;
		// }
		// });
	}

	interface Predicate<T> {
		boolean apply(T t);
	}

	private List<Movie> findInAllMovies(Predicate<Movie> p) {
		List<Movie> movies = new ArrayList<Movie>();
		for (Movie movie : findAll())
			if (movie != null && p.apply(movie))
				movies.add(movie);
		return movies;
	}

	@Override
	public List<Movie> findByDirector(long id) {
		String directorKey = KeyUtils.director(id);
		Set<Movie> moviesOfDirector = movieTemplate.opsForSet().members(
				directorKey);
		return new ArrayList<Movie>(moviesOfDirector);
	}

	@Override
	public List<Movie> findByDirector(final String name) {
		return findByKeyOrdered(KeyUtils.directorName(name));
		// return findInAllMovies(new Predicate<AlloCineMovie>() {
		// @Override
		// public boolean apply(AlloCineMovie t) {
		// for (AlloCinePerson p : t.getDirectors())
		// if (name.equals(p.getName()))
		// return true;
		// return false;
		// }
		// });
	}

	@Override
	public List<Movie> findByKinds(final Kind kind) {
		return findByKeyOrdered(KeyUtils.kind(kind.getLabel()));
		// FIXME : sorts only on kinds ?
		// return findInAllMovies(new Predicate<AlloCineMovie>() {
		// @Override
		// public boolean apply(AlloCineMovie t) {
		// return t.getKinds().contains(movieFilter.getKind());
		// }
		// });
	}
}
