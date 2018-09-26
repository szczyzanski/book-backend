package szczyzanski.book.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToMany
    @JoinTable(name = "tags_books",
            joinColumns = {@JoinColumn(name = "tags_id")},
            inverseJoinColumns = {@JoinColumn(name = "books_id")})
    private Set<Book> bookSet = new HashSet<>();

    public Tag() {}

    public Tag(String value, Set<Book> bookSet) {
        this.value = value;
        this.bookSet = bookSet;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public void addBook(Book book) {
        if(bookSet == null) {
            bookSet = new HashSet<>();
        }
        bookSet.add(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(value, tag.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
