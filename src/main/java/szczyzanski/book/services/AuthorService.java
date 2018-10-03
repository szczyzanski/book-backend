package szczyzanski.book.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.AuthorWithBookSetPower;
import szczyzanski.book.domain.repositiories.AuthorRepository;
import szczyzanski.book.domain.repositiories.AuthorWithBookSetPowerRepository;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorWithBookSetPowerRepository authorWithBookSetPowerRepository;

    public List<Author> findAll() {
        return (List) authorRepository.findAll();
    }

    //TODO IF REFACTOR?
    public void save(Set<Author> authors) {
        for(Author author : authors) {
            authorRepository.save(author);
            AuthorWithBookSetPower authorWithBookSetPower = authorWithBookSetPowerRepository
                                                                .findByName(author.getForname(),
                                                                    author.getSurname());
            if(authorWithBookSetPowerRepository.count() > 3) {
                AuthorWithBookSetPower lastAuthor = authorWithBookSetPowerRepository.getAuthorWithLastBSPower();
                if(author.getBookSetPower() > lastAuthor.getBookSetPower()) {
                    if(authorWithBookSetPower == null) {
                        authorWithBookSetPowerRepository.save(new AuthorWithBookSetPower(author));
                    } else {
                        authorWithBookSetPower.setBookSetPower(authorWithBookSetPower.getBookSetPower() + 1);
                        authorWithBookSetPowerRepository.save(authorWithBookSetPower);
                    }
                    authorWithBookSetPowerRepository.delete(lastAuthor);
                }
            } else {
                if(authorWithBookSetPower == null) {
                    authorWithBookSetPowerRepository.save(new AuthorWithBookSetPower(author));
                } else {
                    authorWithBookSetPower.setBookSetPower(authorWithBookSetPower.getBookSetPower() + 1);
                    authorWithBookSetPowerRepository.save(authorWithBookSetPower);
                }
            }
        }
    }

    public Author findByName(final String forname, final String surname) {
        return authorRepository.findByName(forname, surname);
    }

    public Set<Author> findByBookId(final long id) {
        return authorRepository.findByBookId(id);
    }

    public List<AuthorWithBookSetPower> getMostPopular() {
        return (List) authorWithBookSetPowerRepository.findAll();
    }

    public void deleteAll() {
        authorRepository.deleteAll();
        authorWithBookSetPowerRepository.deleteAll();
    }
}
