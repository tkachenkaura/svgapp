package svgapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SvgEditor {
    private List<Shape> shapes = new ArrayList<>();
    private String filePath;

    public void processCommand(String commandLine) {
        String[] tokens = commandLine.trim().split("\\s+");
        String cmd = tokens[0].toLowerCase();

        try {
            switch (cmd) {
                case "open":
                    filePath = tokens[1];
                    shapes = SvgParser.parse(filePath);
                    System.out.println("Successfully opened " + filePath);
                    break;
                case "print":
                    int i = 1;
                    for (Shape shape : shapes)
                        System.out.println((i++) + ". " + shape);
                    break;
                case "create":
                    Shape shape = ShapeFactory.createShape(tokens);
                    shapes.add(shape);
                    System.out.println("Successfully created " + shape.getType() + " (" + shapes.size() + ")");
                    break;
                case "erase":
                    int index = Integer.parseInt(tokens[1]) - 1;
                    if (index < 0 || index >= shapes.size()) {
                        System.out.println("There is no figure number " + (index + 1) + "!");
                    } else {
                        Shape removed = shapes.remove(index);
                        System.out.println("Erased a " + removed.getType() + " (" + (index + 1) + ")");
                    }
                    break;
                case "translate":
                    int dx = 0, dy = 0;
                    for (int j = 1; j < tokens.length; j++) {
                        if (tokens[j].startsWith("horizontal="))
                            dx = Integer.parseInt(tokens[j].substring("horizontal=".length()));
                        else if (tokens[j].startsWith("vertical="))
                            dy = Integer.parseInt(tokens[j].substring("vertical=".length()));
                    }
                    for (Shape s : shapes)
                        s.translate(dx, dy);
                    System.out.println("Translated all figures");
                    break;
                case "save":
                    SvgWriter.write(filePath, shapes);
                    System.out.println("Successfully saved " + filePath);
                    break;
                case "saveas":
                    String newPath = tokens[1];
                    SvgWriter.write(newPath, shapes);
                    System.out.println("Successfully saved " + newPath);
                    break;
                case "close":
                    shapes.clear();
                    System.out.println("Successfully closed " + filePath);
                    filePath = null;
                    break;
                case "help":
                    System.out.println("The following commands are supported:\n" +
                            "open <file>\nclose\nsave\nsaveas <file>\nhelp\nexit\nprint\ncreate <shape>\nerase <n>\ntranslate [vertical=N] [horizontal=N]");
                    break;
                default:
                    System.out.println("Unknown command: " + cmd);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void printHelp() {
        System.out.println("""
    ========== SVG Editor Help ==========
    open <file>         – open an SVG file
    close               – close the current file
    save                – save to the current file
    saveas <file>       – save to a different file

    print               – list all shapes
    create rectangle x y width height color
    create circle cx cy radius color
    create line x1 y1 x2 y2 color
    erase <n>           – delete shape number <n>

    translate horizontal=N vertical=M
                        – move all shapes

    help                – show this help menu
    exit                – exit the program
    =====================================
    """);
    }

}
