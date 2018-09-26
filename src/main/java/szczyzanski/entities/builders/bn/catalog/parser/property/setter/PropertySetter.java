package szczyzanski.entities.builders.bn.catalog.parser.property.setter;

import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;

public interface PropertySetter {
    void setProperty(String value, BookWithFullInfoDTO bookWithFullInfoDTO);
}
