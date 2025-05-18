package svgapp;

import java.io.*;
import java.util.List;

public class SvgWriter {
    public static void write(String filePath, List<Shape> shapes) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write("<?xml version=\"1.0\" standalone=\"no\"?>\n");
        writer.write("<svg>\n");
        for (Shape shape : shapes)
            writer.write("  " + shape.toSvg() + "\n");
        writer.write("</svg>");
        writer.close();
    }
}
