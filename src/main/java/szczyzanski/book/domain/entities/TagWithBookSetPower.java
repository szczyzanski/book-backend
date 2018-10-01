package szczyzanski.book.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "tags_with_bs_power")
public class TagWithBookSetPower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "bs_power")
    private int bookSetPower;

    @Column(name = "main_id")
    private Long mainId;

    TagWithBookSetPower() {}

    public TagWithBookSetPower(Tag tag) {
        this.value = tag.getValue();
        this.bookSetPower = tag.getBookSetPower();
        this.mainId = tag.getId();
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        return "TagWithBookSetPower{" +
                "value='" + value + '\'' +
                ", bsPower=" + bookSetPower +
                ", mainId=" + mainId +
                '}';
    }
}
