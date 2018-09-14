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

    @Column(name = "isbn")
    private Long isbn;

    @ManyToMany(targetEntity = Author.class, mappedBy = "bookSet")
    private Set<Author> authorSet = new HashSet<>();

    @Column(name = "number_of_pages")
    private int noOfPages;

    @Column(name = "original_title")
    private String originalTitile;

    @Column(name = "origin")
    private String origin;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "tag")
    private String tagSet;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Shelf.class)
    private Shelf shelf;

    public Book() {}

    public Book(final String title, final Shelf shelf, final Set<Author> authorSet) {
        this.title = title;
        this.shelf = shelf;
        this.authorSet = authorSet;
    }

    public Long getId() {
        return id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getOriginalTitile() {
        return originalTitile;
    }

    public void setOriginalTitile(String originalTitile) {
        this.originalTitile = originalTitile;
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

    public String getTagSet() {
        return tagSet;
    }

    public void setTagSet(String tagSet) {
        this.tagSet = tagSet;
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
        return      "Title: " + title + System.getProperty("line.separator")
                +   "Authors: " + System.getProperty("line.separator")
                +   "ISBN: " + isbn + System.getProperty("line.separator")
                +   "Number of pages: " + noOfPages + System.getProperty("line.separator")
                +   "Original title: " + originalTitile + System.getProperty("line.separator")
                +   "Origin: " + origin + System.getProperty("line.separator")
                +   "Publisher: " + publisher + System.getProperty("line.separator")
                +   "Tags: ";
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
