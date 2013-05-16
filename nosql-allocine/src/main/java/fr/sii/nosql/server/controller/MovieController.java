package fr.sii.nosql.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.shared.buisiness.Movie;

@Controller("movieController")
public class MovieController {

	@Autowired
	MovieService movieService;

	@RequestMapping(value = { "/movie/{id}" }, method = { RequestMethod.GET })
	Movie findById(@PathVariable long id) {
		return movieService.findById(id);
	}
}
