package szczyzanski.book.api.dto;

public class BookDTO {
    private Long id;
    private String title;
    private Long authorId;
    private ShelfDTO shelfDTO;

    BookDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public ShelfDTO getShelfDTO() {
        return shelfDTO;
    }

    public void setShelfDTO(ShelfDTO shelfDTO) {
        this.shelfDTO = shelfDTO;
    }

    @Override
    public String toString() {
        return title;
    }
}
