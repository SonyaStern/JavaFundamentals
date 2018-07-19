package javase03.task3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private static final Pattern IMG_PATTERN = Pattern.compile(
            "<img(\\s+.*?)(?:src\\s*=\\s*(?:'|\")(.*?)(?:'|\"))(.*?)/>",
            Pattern.DOTALL|Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) throws Exception {
        HtmlParser filter = new HtmlParser();
        String str = filter.getFileContents
                ("C:\\Users\\Stern\\IdeaProjects\\task22\\src\\main\\java\\javase03\\task3\\index.html");
        System.out.println(filter.rewriteImgTag(str));
    }

    private String rewriteImgTag(String str) {
        StringBuilder result = new StringBuilder();
        Matcher m = IMG_PATTERN.matcher(str);
        int i = 1;
        while (m.find()) { // find next match
            result.append(i++).append(") ").append(m.group().trim()).append("\n");
        }
        return result.toString();
    }

    private String getFileContents(String fileName) throws Exception {
        File theFile = new File(fileName);
        byte[] bytes = new byte[(int) theFile.length()];
        InputStream in = new FileInputStream(theFile);
        int m = 0 , n;
        while (m < bytes.length) {
            n = in.read(bytes, m, bytes.length - m);
            m += n;
        }
        in.close();

        return new String(bytes);
    }
}
