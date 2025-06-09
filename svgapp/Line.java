package svgapp;

public class Line extends Shape {
    private int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2, String color) {
        super(color);
        this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
    }

    @Override
    public void translate(int dx, int dy) {
        x1 += dx; y1 += dy; x2 += dx; y2 += dy;
    }

    @Override
    public String toSvg() {
        return String.format("<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"%s\" />", x1, y1, x2, y2, color);
    }

    @Override
    public String toString() {
        return String.format("line %d %d %d %d %s", x1, y1, x2, y2, color);
    }

    @Override
    public String getType() {
        return "line";
    }
}

