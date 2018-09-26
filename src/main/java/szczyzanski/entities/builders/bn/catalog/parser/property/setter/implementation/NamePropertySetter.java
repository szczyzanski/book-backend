package szczyzanski.entities.builders.bn.catalog.parser.property.setter.implementation;

import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.PropertySetter;

public class NamePropertySetter implements PropertySetter {
    @Override
    public void setProperty(String value, BookWithFullInfoDTO bookWithFullInfoDTO) {
        bookWithFullInfoDTO.addAuthor(value);
    }
}
