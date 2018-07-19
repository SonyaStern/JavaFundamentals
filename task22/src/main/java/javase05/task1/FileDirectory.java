package javase05.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileDirectory {
    private static void getDirectory(File source) throws NoSuchFieldException {
        if (!source.exists()) throw new NoSuchFieldException();
        if(source.isDirectory()) {
            for (File item : Objects.requireNonNull(source.listFiles())) {
                System.out.println(item.getName());
            }
        }
    }

    static void readFile(File source) {
        StringBuilder builder = new StringBuilder();
        if (source.exists() && source.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
                String s;
                while ((s = reader.readLine()) != null) {
                    builder.append(s);
                }
                System.out.println(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File doesn't exist or it isn't a file");
        }

    }

    public static void CreateFolder(File new_source) {
        boolean created = new_source.mkdir();
        if(created)
            System.out.println("Folder has been created");
    }

    public static void CreateFile(File new_file) {
        try
        {
            boolean created = new_file.createNewFile();
            if(created)
                System.out.println("File has been created");
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public static void Delete(File source) throws NoSuchFieldException {
        if (!source.exists()) throw new NoSuchFieldException();
        boolean deleted = source.delete();
        if(deleted)
            System.out.println("Folder has been deleted");
    }

    public static void Write(File source, Path path, String text) throws NoSuchFieldException, NotFileException {
        if (!source.exists()) throw new NoSuchFieldException();
        if (source.isDirectory()) throw new NotFileException("It's not a file");
        try(BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        File source = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src");
        FileDirectory.getDirectory(source);
    }


}


