package szczyzanski.external.services;

import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
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

    public LCCoverDownloader(BookWithFullInfoDTO bookWithFullInfoDTO) {
        this.title = titleForm(bookWithFullInfoDTO.getTitle());
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
        if(title.contains(";")) {
            title = title.substring(0, title.indexOf(";"));
        }
        if(title.endsWith("?")) {
            title = title.substring(0, title.length() - 1);
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
        //todo empty json?
        //todo refactor - instanceof on JsonValue instead of startWith???
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
            for(JsonValue jsonValue : jsonArray) {
                if(jsonValue instanceof JsonObject) {
                    try {
                        JsonObject jsonObject = (JsonObject) jsonValue;
                        String cover =  jsonObject
                                        .getString("cover")
                                        .replace("50x75", "352x500");
                        if(cover.startsWith("http")) {
                            coverUrls.add(new URL(cover));
                        }
                    } catch (NullPointerException npe) {
                        System.out.println("rekord bez okladki");
                    } catch (MalformedURLException e) {
                        System.out.println("sprawdz url");
                    }
                }
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
            File outputFile = new File("covers/" + 0 + "/" + i + ".jpg");
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
