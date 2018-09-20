package szczyzanski.external.services;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class SuggestedTagGenerator {
    private Book book;
    private int repoSize;
    private Map<Tag, Integer> suggestedTagsMap = new HashMap<>();

    public SuggestedTagGenerator(Book book, int repoSize) {
        this.book = book;
        this.repoSize = repoSize;
    }

    public void suggestTags() throws FileNotFoundException {
        File errorFile = new File("errorFile.txt");
        PrintWriter pw = new PrintWriter(errorFile);
        pw.print("Rozmiar bazy ksiazek: " + repoSize + System.getProperty("line.separator")
                + "Tagi w ksiazce:" + System.getProperty("line.separator"));
        Set<Tag> fetchedTags = book.getTagSet();
        fetchedTags.forEach(tag -> pw.print(tag + System.getProperty("line.separator")));
        pw.print("Odrzucone przez ify:" + System.getProperty("line.separator"));
        List<Book> alreadyCheckedBooks = new ArrayList<>();
        alreadyCheckedBooks.add(book);
        for(Tag fetchedTag : fetchedTags) {
            Set<Book> booksTaggedByFetchedTag = fetchedTag.getBookSet();
            if(booksTaggedByFetchedTag.size() < repoSize/2.5) {
                for(Book bookTaggedByFetchedTag : booksTaggedByFetchedTag) {
                    if(!alreadyCheckedBooks.contains(bookTaggedByFetchedTag)) {
                        alreadyCheckedBooks.add(bookTaggedByFetchedTag);
                        Set<Tag> tagsOfInternalBook = bookTaggedByFetchedTag.getTagSet();
                        for(Tag internalBookTag : tagsOfInternalBook) {
                            if(!fetchedTags.contains(internalBookTag)) {
                                if(suggestedTagsMap.containsKey(internalBookTag)) {
                                    suggestedTagsMap.put(internalBookTag, suggestedTagsMap.get(internalBookTag) + 1);
                                } else {
                                    suggestedTagsMap.put(internalBookTag, 1);
                                }
                            } else {
                                pw.print("Tag pierwotnie w ksiazce: " + internalBookTag + System.getProperty("line.separator"));
                            }
                        }
                    }
                }
            } else {
                pw.print("Zbyt duzy rozmiar: " + fetchedTag
                        + " " + booksTaggedByFetchedTag.size() +  System.getProperty("line.separator"));
            }
        }
        pw.close();
    }

    public void printSuggestedTags() throws FileNotFoundException {
        suggestTags();
        suggestedTagsMap = sortMap(suggestedTagsMap);
        File file = new File("suggestedTags.txt");
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print("Rozmiar mapy tagow: " + suggestedTagsMap.size() + System.getProperty("line.separator"));
        for(Map.Entry<Tag, Integer> entry : suggestedTagsMap.entrySet()) {
            printWriter.print(entry.getKey() + ": " + entry.getValue() + System.getProperty("line.separator"));
        }
        printWriter.close();
    }

    private Map<Tag, Integer> sortMap(Map<Tag, Integer> map) {
        List<Map.Entry<Tag, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<Tag, Integer> result = new LinkedHashMap<>();
        for(Map.Entry<Tag, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
