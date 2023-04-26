package file_edit.handlers;

import java.io.*;
import java.nio.file.Path;


public class HandlerIO {
    public static void writeToFile(OutputStream outputStream, String message) {
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(InputStream inputStream) {
        try {
            StringBuilder sb = new StringBuilder();
            int temp;
            while ((temp = inputStream.read()) != -1) {
                sb.append((char) temp);
            }
            System.out.println("Text from file: " + sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getTextCharset(InputStreamReader inputStreamReader) {
        String charsetName = inputStreamReader.getEncoding();
        System.out.println("Charset detected: " + charsetName);
    }


    public static void writeToFile(Writer writer, String message) {
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFile(Reader reader) {
        try {
            StringBuilder sb = new StringBuilder();
            int temp;
            while ((temp = reader.read()) != -1) {
                sb.append((char) temp);
            }
            System.out.println("Text from file: " + sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFilePushbackReader(PushbackReader pushbackReader) {
        try {
            StringBuilder sb = new StringBuilder();
            int temp;
            while ((temp = pushbackReader.read()) != -1) {
                char c = (char) temp;
                if (c == 'o') {
                    pushbackReader.unread('0');
                } else {
                    pushbackReader.unread(c);
                }
                sb.append((char) pushbackReader.read());
            }
            System.out.println("Text from file: " + sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void writeToFileObject(ObjectOutputStream objectOutputStream, T object) {
        try {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readObjectFromFile(Path path, Class<T> clazz) {
        T object = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            object = clazz.cast(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void writeSequenceInputStream(SequenceInputStream sequenceInputStream, OutputStream outputStream) {
        try {
            int data;
            while ((data = sequenceInputStream.read()) != -1) {
                outputStream.write(data);
            }
            sequenceInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCombinedStream(BufferedReader reader) {
        try {
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(" ").append(temp);
            }
            System.out.println("Text from file: " + sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDataToFile(DataOutputStream outputStream, String line, int integer) {
        try {
            outputStream.writeUTF(line);
            outputStream.writeInt(integer);
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readDataFile(DataInputStream inputStream) {
        try {
            while (inputStream.available() > 0) {
                String line = inputStream.readUTF();
                int integer = inputStream.readInt();
                System.out.println("Text from file: " + line + " " + integer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writePipeWriter(PipedWriter pipedWriter, String message) {
        try {
            pipedWriter.write(message);
            pipedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readPipeReader(PipedReader pipedReader) {
        try {
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int temp;
            while ((temp = pipedReader.read(buffer)) != -1) {
                sb.append(buffer, 0, temp);
            }
            System.out.println("Text from file: " + sb);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
