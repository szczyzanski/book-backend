package szczyzanski.entities.builders.bn.catalog.parser.property.setter;

import szczyzanski.book.domain.entities.Book;

public interface PropertySetter {
    void setProperty(String value, Book book);
}
