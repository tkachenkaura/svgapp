package svgapp;

import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class SvgParser {
    public static List<Shape> parse(String filePath) throws Exception {
        List<Shape> shapes = new ArrayList<>();
        String content = Files.readString(Path.of(filePath));

        Matcher rectMatcher = Pattern.compile("<rect[^>]+>").matcher(content);
        while (rectMatcher.find()) {
            String tag = rectMatcher.group();
            int x = Integer.parseInt(getAttr(tag, "x"));
            int y = Integer.parseInt(getAttr(tag, "y"));
            int w = Integer.parseInt(getAttr(tag, "width"));
            int h = Integer.parseInt(getAttr(tag, "height"));
            String color = getAttr(tag, "fill");
            shapes.add(new Rectangle(x, y, w, h, color));
        }

        Matcher circleMatcher = Pattern.compile("<circle[^>]+>").matcher(content);
        while (circleMatcher.find()) {
            String tag = circleMatcher.group();
            int cx = Integer.parseInt(getAttr(tag, "cx"));
            int cy = Integer.parseInt(getAttr(tag, "cy"));
            int r = Integer.parseInt(getAttr(tag, "r"));
            String color = getAttr(tag, "fill");
            shapes.add(new Circle(cx, cy, r, color));
        }

        Matcher lineMatcher = Pattern.compile("<line[^>]+>").matcher(content);
        while (lineMatcher.find()) {
            String tag = lineMatcher.group();
            int x1 = Integer.parseInt(getAttr(tag, "x1"));
            int y1 = Integer.parseInt(getAttr(tag, "y1"));
            int x2 = Integer.parseInt(getAttr(tag, "x2"));
            int y2 = Integer.parseInt(getAttr(tag, "y2"));
            String color = getAttr(tag, "stroke");
            shapes.add(new Line(x1, y1, x2, y2, color));
        }

        return shapes;
    }

    private static String getAttr(String tag, String attr) {
        Matcher m = Pattern.compile(attr + "=\"(.*?)\"").matcher(tag);
        return m.find() ? m.group(1) : "black";
    }
}

