package dev.hibernate.service.impl;

import dev.hibernate.dao.AuthorDao;
import dev.hibernate.lib.Inject;
import dev.hibernate.lib.Service;
import dev.hibernate.model.Author;
import dev.hibernate.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }
}
