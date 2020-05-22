package dev.hibernate.service.impl;

import dev.hibernate.dao.GenreDao;
import dev.hibernate.lib.Inject;
import dev.hibernate.lib.Service;
import dev.hibernate.model.Genre;
import dev.hibernate.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }
}
