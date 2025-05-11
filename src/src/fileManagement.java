package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class fileManagement {

    /* write to file */
    public static void writeToFile(String string, File file, boolean append) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(string);
        }
    }

    /// write tofil bs b strings
    public static void writeToFile(String string, String file, boolean append) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(string);
        }
    }

    /* read from file */
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n"); // Append line with newline for separation
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
