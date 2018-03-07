package szczyzanski.book.domain.entities;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "fName")
    private String authorsFirstname;

    @Column(name = "sName")
    private String authorsSurname;

    Book() {}

    public Book(final String title, final String authorsFirstname, final String authorsSurname) {
        this.title = title;
        this.authorsFirstname = authorsFirstname;
        this.authorsSurname = authorsSurname;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorsFirstname() {
        return authorsFirstname;
    }

    public String getAuthorsSurname() {
        return authorsSurname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorsFirstname(String authorsFirstname) {
        this.authorsFirstname = authorsFirstname;
    }

    public void setAuthorsSurname(String authorsSurname) {
        this.authorsSurname = authorsSurname;
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

    @Override
    public String toString() {
        return title + " by " + authorsFirstname + " " + authorsSurname;
    }
}
