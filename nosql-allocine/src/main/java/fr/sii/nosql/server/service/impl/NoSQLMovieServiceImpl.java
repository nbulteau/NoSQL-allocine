package fr.sii.nosql.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.file.FilePhotoRepository;
import fr.sii.nosql.server.repository.file.FilePosterRepository;
import fr.sii.nosql.server.service.MovieService;

@Service("nosqlMovieService")
public class NoSQLMovieServiceImpl extends MovieServiceImpl implements MovieService {

	@Autowired
	public NoSQLMovieServiceImpl(AlloCineService alloCineService,
			FilePosterRepository filePosterRepository,
			FilePhotoRepository filePhotoRepository) {
		super(alloCineService, null, filePosterRepository, filePhotoRepository);
	}
}
