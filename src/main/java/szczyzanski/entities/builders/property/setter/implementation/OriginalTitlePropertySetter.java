package szczyzanski.entities.builders.property.setter.implementation;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.entities.builders.property.setter.PropertySetter;

public class OriginalTitlePropertySetter implements PropertySetter {
    @Override
    public void setProperty(String value, Book book) {
        book.setOriginalTitile((String) value);
    }
}
