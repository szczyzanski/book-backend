package szczyzanski.book.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "authors_with_bs_power")
public class AuthorWithBookSetPower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "forname")
    private String forname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "bs_power")
    private int bookSetPower;

    @Column(name = "main_id")
    private Long mainId;

    AuthorWithBookSetPower() {}

    public AuthorWithBookSetPower(Author author) {
        this.forname = author.getForname();
        this.surname = author.getSurname();
        this.bookSetPower = author.getBookSetPower();
        this.mainId = author.getId();
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "AuthorWithBookSetPower{" +
                "forname='" + forname + '\'' +
                ", surname='" + surname + '\'' +
                ", bsPower=" + bookSetPower +
                '}';
    }
}
