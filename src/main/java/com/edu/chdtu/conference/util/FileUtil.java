package com.edu.chdtu.conference.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtil {

    private static String homeLocation = System.getProperty("user.home");
    private static String relativeLocation = "\\user";

    public static void createUserFolder(String userFolder) throws IOException {
        String path = homeLocation + File.separator + userFolder + File.separator;
        if (!Files.exists(Paths.get(path))) {
            Files.createDirectory(Paths.get(path));
        }
    }

    public static String getAbsolutePath(String userFolder, String fileName) {
        return homeLocation + File.separator + userFolder + File.separator + fileName;
    }

    public static String getRelativePath(String userFolder, String fileName) {
        return relativeLocation + File.separator + userFolder + File.separator + fileName;
    }

    public static Path relativeToAbsolute(String filePath) {
        return Paths.get(homeLocation + File.separator + Paths.get(relativeLocation).relativize(Paths.get(filePath)));
    }

}
