package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerServer {

    public static byte[] readFileReport(String filePatString) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePatString));
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
