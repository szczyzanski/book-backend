package szczyzanski.book.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ShelfDTO {
    private Long id;
    private int room;
    private int row;
    private int column;
    private boolean test;
    @JsonManagedReference
    private List<BookDTO> bookList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public List<BookDTO> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookDTO> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Shelf in room: " + room + " of row: " + row + " and column: " + column;
    }
}
