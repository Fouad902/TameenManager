package src;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class FileReaderEx {
    String filePath = "F:\\\\VSCode-java\\\\Training-Project\\\\src\\\\passwords";

    FileReaderEx(String filePath) {
        this.filePath = filePath;
    }

    void readFile(String string) {
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class BufferedReaderEx {
    String filePath = "F:\\\\VSCode-java\\\\Training-Project\\\\src\\\\passwords";

    BufferedReaderEx(String filePath) {
        this.filePath = filePath;
    }

    void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
