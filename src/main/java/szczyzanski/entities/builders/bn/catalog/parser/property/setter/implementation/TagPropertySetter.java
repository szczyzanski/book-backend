package szczyzanski.entities.builders.bn.catalog.parser.property.setter.implementation;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.PropertySetter;

import java.util.HashSet;
import java.util.Set;

public class TagPropertySetter implements PropertySetter {
    @Override
    public void setProperty(String value, Book book) {
        Set<Tag> tags = getTags(book);
        tags.add(createTag(value));
        book.setTagSet(tags);
    }

    private Set<Tag> getTags(Book book) {
        Set<Tag> tags = book.getTagSet();
        if(tags == null) {
            tags = new HashSet<>();
        }
        return tags;
    }

    private Tag createTag(String value) {
        return new Tag(value, null);
    }
}
