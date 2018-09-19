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

    public Author getOne(final Long id) {
        return authorRepository.findById(id).get();
    }

    public Author add(Author author) {
        return authorRepository.save(author);
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
}
