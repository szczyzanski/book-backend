package szczyzanski.entities.builders.bn.catalog.parser.property.setter.implementation;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.PropertySetter;

public class OriginalTitlePropertySetter implements PropertySetter {
    @Override
    public void setProperty(String value, Book book) {
        book.setOriginalTitle((String) value);
    }
}
