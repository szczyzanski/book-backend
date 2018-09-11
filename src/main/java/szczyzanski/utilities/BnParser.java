package szczyzanski.utilities;

import szczyzanski.book.domain.entities.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BnParser {

   /* public static void main(String[] args) {
        System.out.println("Podaj ISBN:");
        Scanner scanner = new Scanner(System.in);
        while(true)  {
            try {
                findIsbn(Long.parseLong(scanner.nextLine()));
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }*/

    /*
    TODO:
    exception - recordNo = null;
     */
    public static Book findIsbn(long isbn) throws IOException {
        System.out.println("Uruchomiono szukanie po ISBN");
        URL url = new URL("http://katalogi.bn.org.pl/iii/encore/search/C__S" + isbn + "__Orightresult__U?lang=pol&suite=cobalt");
        String recordNo = findRecordNoInSearchingEngine(url);

        //new url forming
        String result = "http://katalogi.bn.org.pl/iii/encore/record/C__R"
                + recordNo
                + "__S"
                + isbn + "__Orightresult__U__X3?lang=pol&suite=cobalt&marcData=Y";
        url = new URL(result);
        System.out.println(result);

        Book book = new Book();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line = "";
            while((line = bufferedReader.readLine()) != null) {
                if(line.length() > 3) {
                    book = parseRecord(line, bufferedReader, book);
                }
            }
        }
        return book;
    }

    private static String findRecordNoInSearchingEngine(URL url) throws IOException {
        String result = null, line;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while((line = bufferedReader.readLine()) != null) {
                if(line.toLowerCase().contains("resultRecord-".toLowerCase())) {
                    //line cut
                    int recordStart = line.indexOf("resultRecord-") + "resultRecord-".length();
                    line = line.substring(recordStart);
                    int recordEnd = line.indexOf("\"");
                    line = line.substring(0, recordEnd);
                    result = line;
                    break;
                }
            }
        }
        return result;
    }

    private static Book parseRecord(String line, BufferedReader bufferedReader, Book book) throws IOException {
        String paramNo = line.substring(0,3), result = null;
        switch (paramNo) {
            case "020":
                result = parseIsbn(line);
                System.out.println("ISBN = " + result);
                break;
            case "100":
                result = parseName(line);
                System.out.println("Autor = " + result);
                break;
            case "245":
                result = parseTitle(line, bufferedReader);
                book.setTitle(result);
                System.out.println("Tytuł = " + result);
                break;
            case "246":
                result = parseOriginalTitle(line, bufferedReader);
                System.out.println("Tytuł oryginału = " + result);
                break;
            case "260":
                result = parsePublisher(line);
                System.out.println("Wydawca = " + result);
                break;
            case "300":
                result = parseNoOfPages(line);
                System.out.println("Liczba stron = " + result);
                break;
            case "380":
                result = parseTag(line);
                System.out.println("Tagi ogólne = " + result);
                break;
            case "386":
                result = parseOrigin(line);
                System.out.println("Przynależność kulturowa = " + result);
                break;
            case "650":
                result = parseTag(line);
                System.out.println("Tematyka = " + result);
                break;
            case "658":
                result = parseTag(line);
                System.out.println("Tematyka = " + result);
                break;
            case "651":
                result = parseTag(line);
                System.out.println("Miejsce akcji = " + result);
                break;
            case "655":
                result = parseTag(line);
                System.out.println("Gatunek = " + result);
                break;
            case "700":
                result = parseName(line);
                if(line.contains("Autor")) {
                    System.out.println("Współautor = " + result);
                } else {
                    System.out.println("Tłumacz = " + result);
                }
                break;
        }
        return book;
    }

    private static String parseName(String line) {
        String surname = null, firstname = "", result = null;
        //regex for surname
        result = line.replaceAll("\\d", "");
        result = result.replaceAll("\\s", "");
        //Pattern pattern = Pattern.compile("[\\p{L}]?[\\D]?\\p{L}*[\\D]?\\p{L}*,");
        Pattern pattern = Pattern.compile("\\D*,");
        Matcher matcher = pattern.matcher(result);
        if(matcher.find()) {
            surname = matcher.group();
        }
        //regex? for firstname
        result = result.substring(result.indexOf(surname) + surname.length());
        result = result.substring(0, result.indexOf("|"));
        //multiple names - split
        String[] tab = result.split("\\p{Lu}");
        for(String name : tab) {
            int capitalLetterIndex = result.indexOf(name) - 1;
            if(name != null && name != "" &&  capitalLetterIndex >= 0) {
                char capitalLetter = result.charAt(capitalLetterIndex);
                firstname += capitalLetter + name;
                if(firstname.length() > 2 && firstname.endsWith(".")) {
                    firstname = firstname.substring(0, firstname.length() - 1);
                }
                firstname += " ";
                result = result.substring(capitalLetterIndex + name.length() + 1);
            }
        }
        //result
        result = firstname + surname.substring(0, surname.length()-1);
        return(result);
    }

    private static String parseIsbn(String line) {
        String result = null;
        result = line.replaceAll("\\s", "");
        result = result.substring(3, 16);
        return result;
    }

    private static String parseTitle(String line, BufferedReader bufferedReader) throws IOException {
        String result = null;
        result = line.replace("245 10 ", "");
        while(!result.contains("/")) {
            result += bufferedReader.readLine();
        }
        result = result.substring(0, result.indexOf("/"));
        result = result.replaceAll("\\|.*\\|\\p{L}", " ");
        result = result.replaceAll("\\|b", "");
        result = result.trim().replaceAll(" +", " ");
        result = result.replaceAll(" :", ": ");
        return result;
    }

    private static String parseOriginalTitle(String line, BufferedReader bufferedReader) throws IOException {
        String result = null;
        result = line;
        result = result.substring(result.indexOf("Tytuł oryginału") + "Tytuł oryginału".length());
        while(!result.contains(",|")) {
            result += bufferedReader.readLine();
        }
        result = result.substring(0, result.indexOf(",|"));
        result = result.replaceAll(":\\|a", "");
        result = result.replaceAll(" :\\|[a-z]*", ":");
        result = result.trim().replaceAll(" +", " ");
        return  result;
    }

    private static String parsePublisher(String line) {
        String result = line;
        result = result.substring(result.indexOf(":|"));
        result = result.substring(0, result.indexOf(",|"));
        result = result.replaceAll(":\\|[a-z]", "");
        return result;
    }

    private static String parseNoOfPages(String line) {
        String result = line;
        result = result.substring(4);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(result);
        if(matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    private static String parseTag(String line) {
        Pattern pattern = Pattern.compile("\\p{L}+");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            String tagStart = matcher.group();
            return line.substring(line.indexOf(tagStart)).trim().replaceAll(" +", " ");
        } else {
            return null;
        }
    }

    private static String parseOrigin(String line) {
        return line.substring(line.indexOf("Przynależność kulturowa")
                + "Przynależność kulturowa".length()).replaceAll("\\|[a-z]", "").trim().replaceAll(" +", " ");
    }
}