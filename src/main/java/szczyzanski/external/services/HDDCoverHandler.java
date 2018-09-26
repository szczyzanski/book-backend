package szczyzanski.external.services;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HDDCoverHandler {
    private static long id;
    private static List<Image> images = new ArrayList<>();

    //todo copy whole folder instead of file by file
    //todo error during deleting
    public static void handleCovers(Long id) throws IOException {
        HDDCoverHandler.id = id;
        getExistingCovers();
        saveCovers();
        deleteOldCovers();
    }

    private static void getExistingCovers() throws IOException {
        String path = "covers/" + 0 + "/";
        File[] files =  new File(path).listFiles();
        for(File file : files) {
            images.add(ImageIO.read(file));
        }
    }

    private static void saveCovers() throws IOException {
        for(int i = 0; i < images.size(); i++) {
            File outputFile = new File("covers/" + id + "/" + i + ".jpg");
            outputFile.mkdirs();
            ImageIO.write((RenderedImage) images.get(i), "jpg", outputFile);
        }
    }

    private static void deleteOldCovers() throws IOException {
        FileUtils.deleteDirectory(new File("covers/" + 0 + "/"));
    }
}
