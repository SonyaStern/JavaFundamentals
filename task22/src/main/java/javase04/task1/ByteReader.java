package javase04.task1;

import lombok.Cleanup;

import java.io.*;
import java.util.HashMap;

public class ByteReader {
    private static String[] keys = {"abstract", "continue", "for", "new", "switch",
            "assert", "default", "goto", "package", "synchronized",
            "boolean", "do", "if", "private", "this",
            "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws",
            "case", "enum", "instanceof", "return", "transient",
            "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile",
            "const", "float", "native", "super", "while"};
    private static HashMap<String, Integer> KeyVoc = new HashMap<>();

    private static void counter() throws IOException {
        @Cleanup FileInputStream in_stream = new FileInputStream("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\Lab.java");
        File output = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\out.txt");
        @Cleanup FileOutputStream out_stream = new FileOutputStream(output, true);

        byte[] fileBytes = in_stream.readAllBytes();
        String fileText = new String(fileBytes);

        StringBuilder text;
        for (String key : keys) {
            text = new StringBuilder(fileText);
            int counter = 0;
            while (text.toString().contains(key)) {
                counter++;
                int pos = text.indexOf(key);
                text = new StringBuilder(text.substring(pos + key.length() - 1));
            }
            KeyVoc.put(key, counter);
            if (counter > 0)
                out_stream.write((key + ": " + counter + " \n").getBytes());
        }
    }

    public static void main(String[] args) throws IOException {
        ByteReader.counter();
    }

}
