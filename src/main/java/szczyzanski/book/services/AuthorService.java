package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.repositiories.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return (List) authorRepository.findAll();
    }

    public void saveDefault() {
        authorRepository.save(new Author("Jason", "Kapusta"));
    }
}
