package javase04.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private static void ReadWrite(File source, File dest) {
        List<String> data = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(source), "utf-8"))) {

            String s;
            while ((s = reader.readLine()) != null) {
                data.add(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(dest), "utf-16"))) {
            for (String s: data) {
                writer.write(s);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\out1.txt");
        File dest = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\output2.txt");
        FileReader.ReadWrite(source, dest);
    }
}
