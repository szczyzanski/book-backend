package szczyzanski.book.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "forname")
    private String forname;

    @Column(name = "surname")
    private String surname;

    @ManyToMany
    @JoinTable(name = "authors_books",
                joinColumns = {@JoinColumn(name = "authors_id")},
                inverseJoinColumns = {@JoinColumn(name = "books_id")})
    private Set<Book> bookSet = new HashSet<>();

    Author() {}

    public Author(final String forname, final String surname, final Set<Book> bookSet) {
        this.forname = forname;
        this.surname = surname;
        this.bookSet = bookSet;
    }

    public Long getId() {
        return id;
    }

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
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

    public int getBookSetPower() {
        return this.bookSet.size();
    }

    //todo error for null
//    @Override
//    public boolean equals(Object o) {
//        if(this == o) return true;
//        if(!(o instanceof Author)) return false;
//        Author author = (Author) o;
//        return Objects.equals(id, author.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }

    @Override
    public String toString() {
        return forname + " " + surname;
    }

    public void addBook(Book book) {
        if(bookSet == null) {
            bookSet = new HashSet<>();
        }
        bookSet.add(book);
    }

    public void removeBook(Book book) {
        bookSet.remove(book);
        book.getAuthorSet().remove(this);
    }
}
