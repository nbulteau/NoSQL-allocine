package fr.sii.nosql.server.repository.cassandra;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public class CassandraMovieRepositoryImpl implements MovieRepository{

	private Cluster cluster;

	private Session session;

	public CassandraMovieRepositoryImpl() {
		
	}
	
	public Session getSession() {
		return session;
	}

	private void connect(String node) {
		cluster = Cluster.builder().addContactPoint(node).build();

		Metadata metadata = cluster.getMetadata();

		System.out.printf("Connected to cluster: %s\n",
				metadata.getClusterName());

		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}
	}

	private void close() {
		cluster.shutdown();
	}

	private void createSchema() {
		session = cluster.connect();
		session.execute("CREATE KEYSPACE cinema WITH replication "
				+ "= {'class':'SimpleStrategy', 'replication_factor':3};");
		
		session.execute(
			      "CREATE TABLE cinema.movies (" +
			            "id long PRIMARY KEY," + 
			            "title text," + 
			            "originaltitle text," + 
			            "movie blob" + 
			            ");");
	}

	@Override
	public Movie findById(Long id) {
		return null;
	}

	@Override
	public Movie save(Movie movie) {
		PreparedStatement statement = getSession().prepare(
			      "INSERT INTO simplex.songs " +
			      "(id, title, album, artist, tags) " +
			      "VALUES (?, ?, ?, ?, ?);");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet results = getSession().execute(boundStatement.bind());
		Iterator<Row> iterator = results.iterator();
		while (iterator.hasNext()) {
			
		}
		
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
