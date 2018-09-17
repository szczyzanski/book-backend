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
    private long isbn;

    @ManyToMany(targetEntity = Author.class, mappedBy = "bookSet")
    private Set<Author> authorSet;

    @Column(name = "number_of_pages")
    private int noOfPages;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "origin")
    private String origin;

    @Column(name = "publisher")
    private String publisher;

    @ManyToMany(targetEntity = Tag.class, mappedBy = "bookSet")
    private Set<Tag> tagSet;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Shelf.class)
    private Shelf shelf;

    public Book() {}

    public Book(long isbn, Set<Author> authorSet, int noOfPages,
                String originalTitle, String origin, String publisher,
                Set<Tag> tagSet, String title, Shelf shelf) {
        this.isbn = isbn;
        this.authorSet = authorSet;
        this.noOfPages = noOfPages;
        this.originalTitle = originalTitle;
        this.origin = origin;
        this.publisher = publisher;
        this.tagSet = tagSet;
        this.title = title;
        this.shelf = shelf;
    }

    public Long getId() {
        return id;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
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

    public void setOriginalTitle(String originalTitile) {
        this.originalTitle = originalTitile;
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

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
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
        return Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    //TODO add authors names
    @Override
    public String toString() {
        return      "Title: " + title + System.getProperty("line.separator")
                +   "Authors: " + System.getProperty("line.separator")
                +   "ISBN: " + isbn + System.getProperty("line.separator")
                +   "Number of pages: " + noOfPages + System.getProperty("line.separator")
                +   "Original title: " + originalTitle + System.getProperty("line.separator")
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
