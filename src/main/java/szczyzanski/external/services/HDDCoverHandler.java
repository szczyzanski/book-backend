package szczyzanski.external.services;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HDDCoverHandler {
    private static long id;
    private static List<Image> images = new ArrayList<>();
    private static File source = new File("covers/" + 0 + "/");

    //todo copy whole folder instead of file by file
    //todo error during deleting
    public static void saveCovers(Long id) throws IOException {
        HDDCoverHandler.id = id;
        copyDirectory();
        clearCovers();
    }

    public static void clearCovers() throws IOException {
        if(source.exists()) {
            FileUtils.deleteDirectory(source);
        }
    }

    private static void copyDirectory() throws IOException {
        if(source.exists()) {
            File destination = new File("covers/" + id + "/");
            FileUtils.copyDirectory(source, destination);
        }
    }
}
