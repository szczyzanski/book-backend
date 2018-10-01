package szczyzanski.book.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class BookDTO {
    private Long id;
    private long isbn;
    @JsonBackReference
    private Set<AuthorDTO> authorSet;
    private int noOfPages;
    private String originalTitle;
    private String origin;
    private String publisher;
    @JsonBackReference
    private Set<TagDTO> tagSet;
    private String title;
    private ShelfDTO shelf;

    BookDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public Set<AuthorDTO> getAuthorSet() {
        return authorSet;
    }

    public void setAuthorSet(Set<AuthorDTO> authorSet) {
        this.authorSet = authorSet;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Set<TagDTO> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<TagDTO> tagSet) {
        this.tagSet = tagSet;
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

    @Override
    public String toString() {
        return title;
    }
}
