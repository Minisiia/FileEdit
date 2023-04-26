package file_edit.handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HandlerNIO {
    public static void writeNIO(Path filePath, String message) {
        try {
            Files.writeString(filePath, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readNIO(Path filePath) {
        try {
            String data = Files.readString(filePath);
            System.out.println("Text from file: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
