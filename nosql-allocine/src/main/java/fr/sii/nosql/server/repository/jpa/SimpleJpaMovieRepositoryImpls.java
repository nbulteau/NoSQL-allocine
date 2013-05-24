package fr.sii.nosql.server.repository.jpa;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("jpa")
@Repository("simpleJpaMovieRepository")
public class SimpleJpaMovieRepositoryImpls implements MovieRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public Movie findById(Long id) {
		Movie movie = null;
		
		ValueObject valueObject = entityManager.find(ValueObject.class, id);
		if(valueObject != null) {
			try {
				movie = mapper.readValue(valueObject.getJson(), Movie.class);
			} catch ( IOException e) {
					e.printStackTrace();
			}			
		}
		
		return movie;
	}

	@Override
	public Movie save(Movie movie) {
		
		String json = null;
		try {
			json = mapper.writeValueAsString(movie);
		} catch ( IOException e) {
			e.printStackTrace();
		}
		
		ValueObject valueObject = new ValueObject(movie.getId(), json);
		entityManager.persist(valueObject);
		
		return movie;
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
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
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
