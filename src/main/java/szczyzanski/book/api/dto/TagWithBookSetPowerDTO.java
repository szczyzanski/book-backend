package szczyzanski.book.api.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class TagWithBookSetPowerDTO {
    private Long id;
    private String value;
    private int bookSetPower;
    private Long mainId;

    TagWithBookSetPowerDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
