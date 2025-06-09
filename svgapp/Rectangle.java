package svgapp;

public class Rectangle extends Shape {
    private int x, y, width, height;

    public Rectangle(int x, int y, int width, int height, String color) {
        super(color);
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    @Override
    public void translate(int dx, int dy) {
        x += dx; y += dy;
    }

    @Override
    public String toSvg() {
        return String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" fill=\"%s\" />", x, y, width, height, color);
    }

    @Override
    public String toString() {
        return String.format("rectangle %d %d %d %d %s", x, y, width, height, color);
    }

    @Override
    public String getType() {
        return "rectangle";
    }
}

