package svgapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.*;

public class SvgParser {

    public static List<Shape> parse(String filePath) throws IOException {
        List<Shape> shapes = new ArrayList<>();


        String content = Files.readString(Path.of(filePath));


        Matcher rectMatcher = Pattern.compile("<rect[^>]+>").matcher(content);
        while (rectMatcher.find()) {
            String tag = rectMatcher.group();
            int x = getIntAttr(tag, "x");
            int y = getIntAttr(tag, "y");
            int w = getIntAttr(tag, "width");
            int h = getIntAttr(tag, "height");
            String fill = getAttr(tag, "fill");
            shapes.add(new Rectangle(x, y, w, h, fill));
        }

        // Ищем круги
        Matcher circleMatcher = Pattern.compile("<circle[^>]+>").matcher(content);
        while (circleMatcher.find()) {
            String tag = circleMatcher.group();
            int cx = getIntAttr(tag, "cx");
            int cy = getIntAttr(tag, "cy");
            int r = getIntAttr(tag, "r");
            String fill = getAttr(tag, "fill");
            shapes.add(new Circle(cx, cy, r, fill));
        }

        // Ищем линии
        Matcher lineMatcher = Pattern.compile("<line[^>]+>").matcher(content);
        while (lineMatcher.find()) {
            String tag = lineMatcher.group();
            int x1 = getIntAttr(tag, "x1");
            int y1 = getIntAttr(tag, "y1");
            int x2 = getIntAttr(tag, "x2");
            int y2 = getIntAttr(tag, "y2");
            String stroke = getAttr(tag, "stroke");
            shapes.add(new Line(x1, y1, x2, y2, stroke));
        }

        return shapes;
    }


    private static String getAttr(String tag, String attr) {
        Matcher m = Pattern.compile(attr + "=\"(.*?)\"").matcher(tag);
        return m.find() ? m.group(1) : "black";
    }


    private static int getIntAttr(String tag, String attr) {
        String value = getAttr(tag, attr);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

