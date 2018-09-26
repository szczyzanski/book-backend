package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.repositiories.AuthorRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return (List) authorRepository.findAll();
    }

    public void saveDefault() {
        authorRepository.save(new Author("Jason", "Kapusta", null));
        authorRepository.save(new Author("Marek", "Kurwisyn", null));
        authorRepository.save(new Author("Jason", "Mistyczny", null));
    }

    //todo CHANGE TO GETBYID AND HANDLE LIKE BOOK
    public Author getOne(final Long id) {
        return authorRepository.findById(id).get();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void save(Set<Author> authors) {
        for(Author author : authors) {
            authorRepository.save(author);
        }
    }

    public Set<Author> getDefaultSet() {
        Set<Author> authorSet = new HashSet<>();
        authorSet.add(getOne(1L));
        authorSet.add(getOne(2L));
        return authorSet;
    }

    public Author findByName(final String forname, final String surname) {
        return authorRepository.findByName(forname, surname);
    }

    public Set<Author> findByBookId(final long id) {
        return authorRepository.findByBookId(id);
    }
}
