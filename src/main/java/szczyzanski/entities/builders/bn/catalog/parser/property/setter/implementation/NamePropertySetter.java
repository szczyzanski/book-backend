package szczyzanski.entities.builders.bn.catalog.parser.property.setter.implementation;

import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.PropertySetter;

import java.util.HashSet;
import java.util.Set;

public class NamePropertySetter implements PropertySetter {
    @Override
    public void setProperty(String value, Book book) {
        Set<Author> authors = getAuthors(book);
        authors.add(createAuthor(splitAuthorsData(value)));
        book.setAuthorSet(authors);
    }

    private Set<Author> getAuthors(Book book) {
        Set<Author> authors = book.getAuthorSet();
        if(authors == null) {
            authors = new HashSet<>();
        }
        return authors;
    }

    private String[] splitAuthorsData(String authorName) {
        String[] parts = authorName.split("\\s+");
        String surname = parts[parts.length - 1];
        String firstname = "";
        for(int i = 0; i < parts.length - 1; i++) {
            firstname += parts[i] + " ";
        }
        if(firstname.endsWith(" ")) {
            firstname = firstname.substring(0, firstname.length() - 1);
        }
        return new String[] {firstname, surname};
    }

    //TODO: existing?
    private Author createAuthor(String[] authorData) {
        return new Author(authorData[0], authorData[1], null);
    }
}
