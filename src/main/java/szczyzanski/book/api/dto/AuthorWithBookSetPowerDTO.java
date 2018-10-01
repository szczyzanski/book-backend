package szczyzanski.book.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class AuthorWithBookSetPowerDTO {
    private Long id;
    private String forname;
    private String surname;
    private int bookSetPower;
    private Long mainId;

    AuthorWithBookSetPowerDTO () {}

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

    public int getBookSetPower() {
        return bookSetPower;
    }

    public void setBookSetPower(int bookSetPower) {
        this.bookSetPower = bookSetPower;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }
}
