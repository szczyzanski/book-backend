package szczyzanski.book.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @ManyToMany
    @JoinTable(name = "authors_books",
                joinColumns = {@JoinColumn(name = "authors_id")},
                inverseJoinColumns = {@JoinColumn(name = "books_id")})
    private Set<Book> bookSet = new HashSet<Book>();

    Author() {}

    public Author(final String firstName, final String surname, final Set<Book> bookSet) {
        this.firstName = firstName;
        this.surname = surname;
        this.bookSet = bookSet;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return firstName + " " + surname;
    }

    public void addBook(Book book) {
        bookSet.add(book);
        book.getAuthorSet().add(this);
    }

    public void removeBook(Book book) {
        bookSet.remove(book);
        book.getAuthorSet().remove(this);
    }
}
