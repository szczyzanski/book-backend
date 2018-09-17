package szczyzanski.entities.builders.bn.catalog.parser.property.setter.implementation;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.PropertySetter;

public class ISBNPropertySetter implements PropertySetter {
    @Override
    public void setProperty(String value, Book book) {
        book.setIsbn(Long.parseLong(value));
    }
}