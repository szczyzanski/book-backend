package szczyzanski.book.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AuthorDTO {
    private Long id;
    private String forname;
    private String surname;
    private Set<BookDTO> bookSet;

    AuthorDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<BookDTO> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<BookDTO> bookSet) {
        this.bookSet = bookSet;
    }

    @Override
    public String toString() {
        return forname + " " + surname;
    }
}
