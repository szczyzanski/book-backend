package szczyzanski.book.api.dto.full.book;

public class InnerAuthor {
    private String name;

    public InnerAuthor() {}

    public InnerAuthor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InnerAuthor{" +
                "name='" + name + '\'' +
                '}';
    }
}
