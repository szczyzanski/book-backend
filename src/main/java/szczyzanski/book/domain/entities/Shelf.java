package szczyzanski.book.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shelves")
public class Shelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "room")
    int room;

    @Column(name = "x_row")
    int row;

    @Column(name = "y_column")
    int column;

    @Column(name = "test_shelf", nullable = false)
    boolean test = false;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Book> bookList = new ArrayList<Book>();

    Shelf() {}

    public Shelf(final int room, int row, int column) {
        this.room = room;
        this.row = row;
        this.column = column;
    }

    public Long getId() {
        return id;
    }

    public int getRoom() {
        return room;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isTest() {
        return test;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Shelf)) return false;
        Shelf shelf = (Shelf) o;
        return Objects.equals(id, shelf.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Shelf in room: " + room + " of row: " + row + " and column: " + column;
    }

    public void addBook(Book book) {
        this.bookList.add(book);
        book.setShelf(this);
    }
}