package szczyzanski.external.services;

import szczyzanski.book.domain.entities.Book;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class LCCoverDownloader {

    private String title;
    private URL searcherUrl;
    private List<Image> images = new ArrayList<>();
    private List<URL> coverUrls = new ArrayList<>();
    private Long id;

    public LCCoverDownloader(Book book) {
        this.title = titleForm(book.getTitle());
        this.id = book.getId();
    }

    private String titleForm(String title) {
        title = Normalizer.normalize(title, Normalizer.Form.NFD);
        title = title.replaceAll("\\p{M}", "");
        title = title.replaceAll("ł", "l");
        if(title.contains(":")) {
            title = title.substring(0, title.indexOf(":"));
        }
        if(title.contains(".")) {
            title = title.substring(0, title.indexOf("."));
        }
        return title;
    }

    public void downloadCover() {
        try {
            createSearcherUrl();
            System.out.println(searcherUrl);
            getCoverUrl();
            getCover();
            saveCover();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSearcherUrl() throws MalformedURLException {
        final String PRE_TITLE_URL = "http://lubimyczytac.pl/searcher/getsuggestions?phrase=";
        searcherUrl = new URL(PRE_TITLE_URL + title.replaceAll("\\s", "+"));
    }

    private void getCoverUrl() throws IOException {
        String file = downloadFileContent(searcherUrl);

        if(file.startsWith("{")) {
            JsonObject jsonObject = Json.createReader(new StringReader(file)).readObject();
            for(String key : jsonObject.keySet()) {
                try {
                    String cover = jsonObject.getJsonObject(key).getString("cover").replace("50x75", "352x500");
                    try {
                        coverUrls.add(new URL(cover));
                    } catch (MalformedURLException e) {
                        System.out.println(cover); //todo check if empty?
                    }
                } catch (NullPointerException npe) {
                    System.out.println("rekord bez okladki");
                }
            }
        } else if (file.startsWith("[")){
            JsonArray jsonArray = Json.createReader(new StringReader(file)).readArray();
            try {
                String cover = jsonArray.getJsonObject(0).getString("cover").replace("50x75", "352x500");
                coverUrls.add(new URL(cover));
            } catch (NullPointerException npe) {
                System.out.println("rekord bez okladki");
            }
        }
    }

    private void getCover() throws IOException {
        for(URL url : coverUrls) {
            images.add(ImageIO.read(url));
        }
    }

    private void saveCover() throws IOException {
        for(int i = 0; i < images.size(); i++) {
            File outputFile = new File("covers/" + id + "/" + i + ".jpg");
            outputFile.mkdirs();
            ImageIO.write((RenderedImage) images.get(i), "jpg", outputFile);
        }
    }

    private String downloadFileContent(URL url) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line, fileContent = "";
            while((line = br.readLine()) != null) {
                fileContent += line + System.getProperty("line.separator");
            }
            return fileContent;
        }
    }
}
