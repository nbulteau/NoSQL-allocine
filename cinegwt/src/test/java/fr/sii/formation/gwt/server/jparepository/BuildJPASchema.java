package fr.sii.formation.gwt.server.jparepository;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.repository.jpa.FilmDAO;

@ActiveProfiles({ "jpa" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jpa-context.xml")
public class BuildJPASchema {

	@Autowired
	FilmDAO filmDAO;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
