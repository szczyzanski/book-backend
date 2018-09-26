package szczyzanski.book.api.dto.full.book;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="isbn")
public class BookWithFullInfoDTO {
    private Long isbn;
    private String title;
    private String publisher;
    private int noOfPages;
    private String originalTitle;
    private String origin;
    private List<InnerAuthor> authors;
    private Set<InnerTag> tags;

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public List<InnerAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<InnerAuthor> authors) {
        this.authors = authors;
    }

    public Set<InnerTag> getTags() {
        return tags;
    }

    public void setTags(Set<InnerTag> tags) {
        this.tags = tags;
    }

    public void addTag(String value) {
        Set<InnerTag> tags = getTags();
        if(tags == null) {
            tags = new HashSet<>();
        }
        tags.add(new InnerTag(value));
        this.tags = tags;
    }

    public void addAuthor(String name) {
        List<InnerAuthor> authors = getAuthors();
        if(authors == null) {
            authors = new ArrayList<>();
        }
        authors.add(new InnerAuthor(name));
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookWithFullInfoDTO{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", noOfPages=" + noOfPages +
                ", originalTitle='" + originalTitle + '\'' +
                ", origin='" + origin + '\'' +
                ", authors=" + authors +
                ", tags=" + tags +
                '}';
    }
}
