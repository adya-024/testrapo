import java.io.*;
import java.util.Scanner;

public class FileOperations {
    static String filePath = "sample.txt";
    public static void main(String[] args) {
        try {
            
            writeFile("Hello, this is the first line.\nThis is the second line.");
            System.out.println("Original File Content:");
            readFile(); 
            modifyFile("second", "modified");
            System.out.println("\nModified File Content:");
            readFile();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    
    public static void writeFile(String content) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }
   public static void readFile() throws IOException {
        File file = new File(filePath);
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            System.out.println(line);
        }

        reader.close();
    } 
    public static void modifyFile(String oldWord, String newWord) throws IOException {
        File file = new File(filePath);
        Scanner reader = new Scanner(file);
        StringBuilder content = new StringBuilder(); 
        while (reader.hasNextLine()) {
            content.append(reader.nextLine()).append("\n");
        }
        reader.close();
        String modifiedContent = content.toString().replaceAll(oldWord, newWord);
        FileWriter writer = new FileWriter(filePath);
        writer.write(modifiedContent);
        writer.close();
    }
}
