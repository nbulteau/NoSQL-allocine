package fr.sii.formation.gwt.server.repository.mongodb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import fr.sii.formation.gwt.shared.MovieFilter;
import fr.sii.formation.gwt.shared.buisiness.Kind;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@Repository
@Profile("mongodb")
public class MongoDBMovieMapReduceRepositoryImpl implements MongoDBMovieMapReduceRepository {
	private final static Logger LOGGER = LoggerFactory.getLogger(MongoDBMovieMapReduceRepositoryImpl.class);

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
	public List<Movie> findByActorMR(long id) {
		List<Movie> movies = new ArrayList<Movie>();

		Query query = new Query(Criteria.where("actors.person._id").is(id));
		query.with(new Sort(Direction.ASC, "title"));

		MapReduceResults<Movie> results = mongoOperations.mapReduce(query, "movies", MAP_ALL, REDUCE_ALL, Movie.class);

		logMapReduceResults(results);

		for (Movie movie : results) {
			movies.add(movie);
		}
		return movies;
	}

	@Override
	public long countWithQuery(MovieFilter movieFilter) {
		Query query = generateQuery(movieFilter);
		return mongoOperations.count(query, Movie.class);
	}

	@Override
	public long countWithQueryMR(MovieFilter movieFilter) {
		Query query = generateQuery(movieFilter);

		MapReduceResults<Movie> results = mongoOperations.mapReduce(query, "movies", MAP_COUNT, REDUCE_COUNT, Movie.class);

		logMapReduceResults(results);

		long result = results.getCounts().getOutputCount();
		return result;
	}

	@Override
	public int averageDurationWithQueryMR(MovieFilter movieFilter) {
		Query query = generateQuery(movieFilter);

		MapReduceOptions options = MapReduceOptions.options().outputTypeInline();
		MapReduceResults<ValueObject> results = mongoOperations.mapReduce(query, "movies", MAP_DURATION, REDUCE_DURATION, options, ValueObject.class);

		int result = 0;
		for (Iterator<ValueObject> iterator = results.iterator(); iterator.hasNext();) {
			ValueObject valueObject = iterator.next();
			int nb = results.getCounts().getEmitCount();
			result = valueObject.getValue() / nb;
		}

		return result;
	}

	@Override
	public int averageDuration(MovieFilter movieFilter) {
		List<Movie> moviesList = findByKind(movieFilter);
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
	public List<Movie> findByKindMR(MovieFilter movieFilter) {
		List<Movie> movies = new ArrayList<Movie>();
		Query query = generateQuery(movieFilter);
		// query.with(new Sort(Direction.ASC, "title"));

		MapReduceResults<Movie> results = mongoOperations.mapReduce(query, "movies", MAP_ALL, REDUCE_ALL, Movie.class);

		logMapReduceResults(results);

		for (Movie movie : results) {
			movies.add(movie);
		}
		return movies;
	}

	@Override
	public List<Movie> findByKind(MovieFilter movieFilter) {
		Query query = generateQuery(movieFilter);
		// query.with(new Sort(Direction.ASC, "title"));

		return mongoOperations.find(query, Movie.class);
	}

	private Query generateQuery(MovieFilter movieFilter) {
		Query query;
		Kind kind = null;
		if (movieFilter != null) {
			kind = movieFilter.getKind();
		}
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

}
