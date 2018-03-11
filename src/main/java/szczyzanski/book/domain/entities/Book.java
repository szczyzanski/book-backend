package szczyzanski.book.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Shelf.class)
    private Shelf shelf;

    @ManyToMany(targetEntity = Author.class, mappedBy = "bookSet")
    private Set<Author> authorSet = new HashSet<Author>();

    Book() {}

    public Book(final String title, final Shelf shelf, final Set<Author> authorSet) {
        this.title = title;
        this.shelf = shelf;
        this.authorSet = authorSet;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Shelf getShelf() {
        return this.shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public Set<Author> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet) {
        this.authorSet = authorSet;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //TODO add authors names
    @Override
    public String toString() {
        return title + " by author with id: ";
    }

    public void addAuthor(Author author) {
        authorSet.add(author);
        author.getBookSet().add(this);
    }

    public void removeAuthor(Author author) {
        authorSet.remove(author);
        author.getBookSet().remove(this);
    }
}
