package file_edit;

import file_edit.handlers.HandlerIO;
import file_edit.handlers.HandlerNIO;
import file_edit.pojo.Person;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PushbackReader;


public class Main {
    public static void main(String[] args) {

        System.out.println("\n----------------------\nIO\n----------------------");

        // BufferedWriter/ PushbackReader - изменение потока
        System.out.println("\nEdit PushbackReader.txt");
        try (FileReader reader = new FileReader("src/file_edit/data/PushbackReader.txt");
             Writer bufferedWriter = new BufferedWriter(new FileWriter("src/file_edit/data/PushbackReader.txt"));
             PushbackReader pushbackReader = new PushbackReader(reader, 100)) {
            HandlerIO.writeToFile(bufferedWriter, "PushbackReader change o to 0. Hello world!");
            System.out.println("Initial text: PushbackReader change o to 0. Hello world!");
            HandlerIO.readFilePushbackReader(pushbackReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BufferedWriter/ BufferedReader
        System.out.println("\nEdit TextBufferedWriter.txt");
        try (Writer bufferedWriter = new BufferedWriter(new FileWriter("src/file_edit/data/TextBufferedWriter.txt"));
             Reader bufferedReader = new BufferedReader(new FileReader("src/file_edit/data/TextBufferedWriter.txt"))) {
            HandlerIO.writeToFile(bufferedWriter, "Buffered Writer");
            HandlerIO.readFile(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // FileWriter/ FileReader

        System.out.println("\nEdit TextFileWriter.txt");
        try (Writer fileWriter = new FileWriter("src/file_edit/data/TextFileWriter.txt");
             Reader fileReader = new FileReader("src/file_edit/data/TextFileWriter.txt")) {
            HandlerIO.writeToFile(fileWriter, "File Writer");
            HandlerIO.readFile(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // PrintWriter/ InputStreamReader - кодировки
        System.out.println("\nEdit TextPrintWriter.txt");
        try (PrintWriter printWriter = new PrintWriter("src/file_edit/data/TextPrintWriter.txt", StandardCharsets.UTF_16);
             InputStreamReader reader = new InputStreamReader(new FileInputStream("src/file_edit/data/TextPrintWriter.txt"), StandardCharsets.UTF_8)) {
            HandlerIO.writeToFile(printWriter, "Print Writer");
            HandlerIO.readFile(reader);
            HandlerIO.getTextCharset(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ObjectOutputStream/ ObjectInputStream - объект и файл
        System.out.println("\nEdit Person.ser");
        Path path = Paths.get("src/file_edit/data/Person.ser");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            HandlerIO.writeToFileObject(outputStream, new Person("Ann", 18));
            Person deserializedPerson = HandlerIO.readObjectFromFile(path, Person.class);
            System.out.println("Deserialized Person from file: " + deserializedPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //  SequenceInputStream/BufferedReader - слияние потоков
        System.out.println("\nEdit CombinedSequenceInputStream.txt");
        try (InputStream inputStream1 = new FileInputStream("src/file_edit/data/SequenceInputStream1.txt");
             InputStream inputStream2 = new FileInputStream("src/file_edit/data/SequenceInputStream2.txt");
             SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2);
             OutputStream outputStream = new FileOutputStream("src/file_edit/data/CombinedSequenceInputStream.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/file_edit/data/CombinedSequenceInputStream.txt")))) {
            HandlerIO.writeSequenceInputStream(sequenceInputStream, outputStream);
            HandlerIO.readCombinedStream(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DataOutputStream/ DataInputStream
        System.out.println("\nEdit DataOutputStream.txt");
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/file_edit/data/DataOutputStream.txt"));
             DataInputStream dataInputStream = new DataInputStream(new FileInputStream("src/file_edit/data/DataOutputStream.txt"))) {
            HandlerIO.writeDataToFile(dataOutputStream, "Data Output Stream", 28);
            HandlerIO.readDataFile(dataInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Files.newBufferedWriter/ Files.newBufferedReader
        System.out.println("\nEdit TextFile.txt");
        path = Paths.get("src/file_edit/data/TextFile.txt");
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
             BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            HandlerIO.writeToFile(bw, "Files and Path");
            HandlerIO.readFile(br);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n----------------------\nWithout file\n----------------------");
        System.out.println("\nByteArrayInputStream/ByteArrayOutputStream");
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            HandlerIO.writeToFile(outputStream, "ByteArrayOutputStream and ByteArrayInputStream");

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            HandlerIO.readFile(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nPipedWriter/PipedReader");
        try (PipedWriter pipedWriter = new PipedWriter();
             PipedReader pipedReader = new PipedReader(pipedWriter)) {
            Thread writerThread = new Thread(() -> HandlerIO.writePipeWriter(pipedWriter, "PipedReader and PipedWriter"));
            writerThread.start();
            HandlerIO.readPipeReader(pipedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n----------------------\nNIO\n----------------------");
        path = Paths.get("src/file_edit/data/TextNIO.txt");
        HandlerNIO.writeNIO(path,"NIO Files\nNIO Files2\nNIO Files3");
        HandlerNIO.readNIO(path);
    }
}
