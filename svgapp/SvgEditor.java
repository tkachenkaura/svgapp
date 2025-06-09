package svgapp;

import java.util.*;
import java.io.*;

public class SvgEditor {
    private List<Shape> shapes = new ArrayList<>();
    private String filePath = null;

    private final Map<String, Runnable> commandMap = new HashMap<>();
    private String[] currentArgs;

    public SvgEditor() {

        commandMap.put("print", this::printShapes);
        commandMap.put("help", this::printHelp);
        commandMap.put("save", this::save);
        commandMap.put("close", this::close);
        commandMap.put("exit", () -> {
            System.out.println("Exiting the program...");
            System.exit(0);
        });
    }

    public void processCommand(String commandLine) {
        currentArgs = commandLine.trim().split("\\s+");
        if (currentArgs.length == 0 || currentArgs[0].isEmpty()) {
            System.out.println("No command entered.");
            return;
        }

        String cmd = currentArgs[0].toLowerCase();

        Runnable action = commandMap.get(cmd);
        if (action != null) {
            action.run();
            return;
        }

        try {
            switch (cmd) {
                case "open" -> openFile();
                case "create" -> createShape();
                case "erase" -> eraseShape();
                case "translate" -> translateShapes();
                case "saveas" -> saveAs();
                default -> System.out.println("Unknown command: " + cmd);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void openFile() throws IOException {
        if (currentArgs.length < 2) {
            System.out.println("Usage: open <filename>");
            return;
        }
        filePath = currentArgs[1];
        shapes = SvgParser.parse(filePath);
        System.out.println("Successfully opened " + filePath);
    }

    private void createShape() {
        try {
            Shape shape = ShapeFactory.createShape(currentArgs);
            shapes.add(shape);
            System.out.println("Successfully created " + shape.getType() + " (" + shapes.size() + ")");
        } catch (Exception e) {
            System.out.println("Failed to create shape: " + e.getMessage());
        }
    }

    private void eraseShape() {
        if (currentArgs.length < 2) {
            System.out.println("Usage: erase <index>");
            return;
        }
        int index = Integer.parseInt(currentArgs[1]) - 1;
        if (index < 0 || index >= shapes.size()) {
            System.out.println("There is no figure number " + (index + 1) + "!");
        } else {
            Shape removed = shapes.remove(index);
            System.out.println("Erased a " + removed.getType() + " (" + (index + 1) + ")");
        }
    }

    private void translateShapes() {
        int dx = 0, dy = 0;
        for (String arg : currentArgs) {
            if (arg.startsWith("horizontal="))
                dx = Integer.parseInt(arg.substring("horizontal=".length()));
            if (arg.startsWith("vertical="))
                dy = Integer.parseInt(arg.substring("vertical=".length()));
        }
        for (Shape shape : shapes) {
            shape.translate(dx, dy);
        }
        System.out.println("Translated all figures");
    }

    private void save() {
        if (filePath == null) {
            System.out.println("No file currently opened.");
            return;
        }
        try {
            SvgWriter.write(filePath, shapes);
            System.out.println("Successfully saved " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }

    private void saveAs() {
        if (currentArgs.length < 2) {
            System.out.println("Usage: saveas <filename>");
            return;
        }
        String newPath = currentArgs[1];
        try {
            SvgWriter.write(newPath, shapes);
            System.out.println("Successfully saved " + newPath);
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }

    private void close() {
        shapes.clear();
        System.out.println("Successfully closed " + filePath);
        filePath = null;
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

    private void printShapes() {
        if (shapes.isEmpty()) {
            System.out.println("No shapes loaded.");
            return;
        }
        int i = 1;
        for (Shape shape : shapes) {
            System.out.println((i++) + ". " + shape.toString());
        }
    }
}

