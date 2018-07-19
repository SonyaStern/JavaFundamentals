package javase04.task2;

import java.io.*;
import java.util.HashMap;


public class CharReader {
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


    private static void counter(File source, File dest) throws IOException {
        HashMap<String, Integer> KeyVoc = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String s;
            while ((s = reader.readLine()) != null) {
                builder.append(s);
            }

        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            for (String key : keys) {
                int counter = 0;
                while (builder.toString().contains(key)) {
                    counter++;
                    int pos = builder.indexOf(key);
                    builder = new StringBuilder(builder.substring(pos + key.length() - 1));
                }
                KeyVoc.put(key, counter);
                if (counter > 0)
                    writer.write((key + ": " + counter + " \n"));
                    writer.newLine();
            }
        }

    }



    public static void main(String[] args) throws IOException {
        File source = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\Lab.java");
        File dest = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\output.txt");
        CharReader.counter(source, dest);
    }
}



