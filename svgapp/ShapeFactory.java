package svgapp;

public class ShapeFactory {
    public static Shape createShape(String[] tokens) throws IllegalArgumentException {
        String type = tokens[1].toLowerCase();
        switch (type) {
            case "rectangle":
                return new Rectangle(
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3]),
                        Integer.parseInt(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        tokens[6]);
            case "circle":
                return new Circle(
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3]),
                        Integer.parseInt(tokens[4]),
                        tokens[5]);
            case "line":
                return new Line(
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3]),
                        Integer.parseInt(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        tokens[6]);
            default:
                throw new IllegalArgumentException("Unsupported shape type");
        }
    }
}

