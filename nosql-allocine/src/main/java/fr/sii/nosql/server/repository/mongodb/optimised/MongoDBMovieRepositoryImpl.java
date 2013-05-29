package fr.sii.nosql.server.repository.mongodb.optimised;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("mongodb")
@Repository
public class MongoDBMovieRepositoryImpl implements MongoDBMovieRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(MongoDBMovieRepositoryImpl.class);

	private final static String MAP_COUNT = "function(){emit(this._id, 1);}";

	private final static String REDUCE_COUNT = "function(key,values){var sum=0;for(var i in values) sum += values[i]; return sum;}";

	private final static String MAP_DURATION = "function(){if(this.duration > 0) {emit(\"dur\", this.duration);}};";

	private final static String REDUCE_DURATION = "function(key,values){var sum = 0; for(var i = 0; i < values.length; i++) { sum += values[i];};  return sum;};";

	private final static String MAP_ALL = "function(){emit(this._id, this);}";

	private final static String REDUCE_ALL = "function(key,values){return values;}";

	class ValueObject {
		public String id;

		public int value;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "ValueObject [id=" + id + ", value=" + value + "]";
		}

	}

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<Movie> findByActorMR(String name) {
		List<Movie> movies = new ArrayList<Movie>();

		Query query = new Query(Criteria.where("castMembers.person.name").is(name));
		query.with(new Sort(Direction.ASC, "title"));

		MapReduceResults<Movie> results = mongoOperations.mapReduce(query, "movies", MAP_ALL, REDUCE_ALL, Movie.class);

		logMapReduceResults(results);

		for (Movie movie : results) {
			movies.add(movie);
		}
		return movies;
	}

	@Override
	public long countWithQuery(Kind kind) {
		Query query = generateQuery(kind);
		return mongoOperations.count(query, Movie.class);
	}

	@Override
	public long countWithQueryMR(Kind kind) {
		Query query = generateQuery(kind);

		MapReduceResults<Movie> results = mongoOperations.mapReduce(query, "movies", MAP_COUNT, REDUCE_COUNT, Movie.class);

		logMapReduceResults(results);

		long result = results.getCounts().getOutputCount();
		return result;
	}

	@Override
	public int averageDurationWithQueryMR(Kind kind) {
		Query query = generateQuery(kind);

		MapReduceOptions options = MapReduceOptions.options().outputTypeInline();
		MapReduceResults<ValueObject> results = mongoOperations.mapReduce(query, "movies", MAP_DURATION, REDUCE_DURATION, options, ValueObject.class);

		long result = 0;
		for (Iterator<ValueObject> iterator = results.iterator(); iterator.hasNext();) {
			ValueObject valueObject = iterator.next();
			long nb = results.getCounts().getEmitCount();
			result = (valueObject.getValue() / nb);
		}

		return (int) result;
	}

	@Override
	public int averageDuration(Kind kind) {
		List<Movie> moviesList = findByKinds(kind);
		int sum = 0;
		int index = 0;
		for (Movie movie : moviesList) {
			if (movie.getDuration() > 0) {
				sum += movie.getDuration();
				index++;
			}
		}
		float result = sum / index;
		return (int) result;
	}

	@Override
	public List<Movie> findByKindMR(Kind kind) {
		List<Movie> movies = new ArrayList<Movie>();
		Query query = generateQuery(kind);
		// query.with(new Sort(Direction.ASC, "title"));

		MapReduceResults<Movie> results = mongoOperations.mapReduce(query, "movies", MAP_ALL, REDUCE_ALL, Movie.class);

		logMapReduceResults(results);

		for (Movie movie : results) {
			movies.add(movie);
		}
		return movies;
	}

	@Override
	public List<Movie> findByKinds(Kind kind) {
		Query query = generateQuery(kind);
		// query.with(new Sort(Direction.ASC, "title"));
		return mongoOperations.find(query, Movie.class);
	}

	private Query generateQuery(Kind kind) {
		Query query;
		if (kind == null) {
			query = new Query();
		} else {
			query = new Query(Criteria.where("kinds").is(kind.getLabel()));
		}
		return query;
	}

	private void logMapReduceResults(MapReduceResults<Movie> results) {
		LOGGER.debug("Input count : {}", results.getCounts().getInputCount());
		LOGGER.debug("Output count : {}", results.getCounts().getOutputCount());
		LOGGER.debug("Emit count : {}", results.getCounts().getEmitCount());
		LOGGER.debug("Output Collection : {}", results);
		LOGGER.debug("Emit Loop Time : {}", results.getTiming().getEmitLoopTime());
		LOGGER.debug("Map Time : {}", results.getTiming().getMapTime());
		LOGGER.debug("Total Time : {}", results.getTiming().getTotalTime());
	}

	@Override
	public Movie findById(Long id) {
		return mongoOperations.findById(id, Movie.class);
	}

	@Override
	public Movie save(Movie movie) {
		mongoOperations.save(movie);
		return movie;
	}

	@Override
	public List<Movie> save(List<Movie> movies) {
		mongoOperations.insert(movies, Movie.class);
		return movies;
	}

	@Override
	public void delete(Movie movie) {
		mongoOperations.remove(movie);
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
		return findById(id) != null;
	}

	@Override
	public long count() {
		return mongoOperations.count(null, Movie.class);
	}

	@Override
	public void delete(Long id) {
		Movie movie = findById(id);
		if (movie != null) {
			mongoOperations.remove(movie);
		}
	}

	@Override
	public void deleteAll() {
		mongoOperations.dropCollection(Movie.class);
	}

	@Override
	public List<Movie> findByTitle(String title) {
		Query query = new Query(Criteria.where("title").is(title));

		List<Movie> results = mongoOperations.find(query, Movie.class);

		return results;
	}

	@Override
	public List<Movie> findByTitleLike(String string) {
		Query query = new Query(Criteria.where("title").is("/^" + string + "/"));
		query.with(new Sort(Direction.ASC, "title"));

		List<Movie> results = mongoOperations.find(query, Movie.class);

		return results;
	}

	@Override
	public List<Movie> findByActor(long id) {
		Query query = new Query(Criteria.where("castMembers.person._id").is(id));
		query.with(new Sort(Direction.ASC, "title"));

		List<Movie> results = mongoOperations.find(query, Movie.class);

		return results;
	}

	@Override
	public List<Movie> findByActor(String name) {
		Query query = new Query(Criteria.where("castMembers.person.name").is(name));
		query.with(new Sort(Direction.ASC, "title"));

		List<Movie> results = mongoOperations.find(query, Movie.class);

		return results;
	}

	@Override
	public List<Movie> findByDirector(long id) {
		Query query = new Query(Criteria.where("directors._id").is(id));
		query.with(new Sort(Direction.ASC, "title"));

		List<Movie> results = mongoOperations.find(query, Movie.class);

		return results;
	}

	@Override
	public List<Movie> findByDirector(String name) {
		Query query = new Query(Criteria.where("directors.name").is(name));
		query.with(new Sort(Direction.ASC, "title"));

		List<Movie> results = mongoOperations.find(query, Movie.class);

		return results;
	}

}
