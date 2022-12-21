package file_edit;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
                                                                    // BufferedWriter/ BufferedReader
        System.out.println("Edit TextBufferedWriter.txt");
        try (Writer bufferedWriter = new BufferedWriter(new FileWriter("src/file_edit/TextBufferedWriter.txt"));
             Reader bufferedReader = new BufferedReader(new FileReader("src/file_edit/TextBufferedWriter.txt"))) {
            bufferedWriter.write("Buffered Writer");
            System.out.println("Done");
            bufferedWriter.flush();
            String s = "";
            int temp;
            for (; ; ) {
                temp = bufferedReader.read();
                if (temp == -1) break;
                s += (char) temp;
            }
            System.out.println("Text from TextBufferedWriter.txt:");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");  // FileWriter/ FileReader

        System.out.println("Edit TextFileWriter.txt");
        try (Writer fileWriter = new FileWriter("src/file_edit/TextFileWriter.txt");
             Reader fileReader = new FileReader("src/file_edit/TextFileWriter.txt")) {
            fileWriter.write("File Writer");
            System.out.println("Done");
            fileWriter.flush();
            String s = "";
            int temp;
            for (; ; ) {
                temp = fileReader.read();
                if (temp == -1) break;
                s += (char) temp;
            }
            System.out.println("Text from TextFileWriter.txt:");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");  // PrintWriter/ BufferedReader

        System.out.println("Edit TextPrintWriter.txt");
        try (Writer printWriter = new PrintWriter("src/file_edit/TextPrintWriter.txt");
             BufferedReader reader = new BufferedReader(new FileReader("src/file_edit/TextPrintWriter.txt"))) {
            printWriter.write("Print Writer");
            System.out.println("Done");
            printWriter.flush();
            String s = "";
            int temp;
            for (; ; ) {
                temp = reader.read();
                if (temp == -1) break;
                s += (char) temp;
            }
            System.out.println("Text from TextPrintWriter.txt:");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");  // DataOutputStream/ DataInputStream
        System.out.println("Edit DataOutputStream.txt");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/file_edit/DataOutputStream.txt"));
             DataInputStream dataInputStream = new DataInputStream(new FileInputStream("src/file_edit/DataOutputStream.txt"))) {
            dataOutputStream.writeUTF("Data Output Stream");
            System.out.println("Done");
            dataOutputStream.flush();
            System.out.println("Text from DataOutputStream.txt:");
            while (dataInputStream.available() > 0) {
                String s = dataInputStream.readUTF();
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");  // Files.newBufferedWriter/ Files.newBufferedReader
        System.out.println("Edit TextFile.txt");
        Path path = Paths.get("src/file_edit/TextFile.txt");
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
             BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            bw.write("Files and Path");
            System.out.println("Done");
            bw.flush();
            String s = "";
            int temp;
            for (; ; ) {
                temp = br.read();
                if (temp == -1) break;
                s += (char) temp;
            }
            System.out.println("Text from TextFile.txt:");
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
