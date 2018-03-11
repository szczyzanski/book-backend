package szczyzanski.book.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class BookDTO {
    private Long id;
    private String title;
    private ShelfDTO shelf;
    private Set<AuthorDTO> authorSet;

    BookDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ShelfDTO getShelf() {
        return shelf;
    }

    public void setShelf(ShelfDTO shelf) {
        this.shelf = shelf;
    }

    public Set<AuthorDTO> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<AuthorDTO> authorSet) {
        this.authorSet = authorSet;
    }

    @Override
    public String toString() {
        return title;
    }
}
