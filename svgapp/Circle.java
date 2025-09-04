package svgapp;

public class Circle extends Shape {
    private int cx, cy, r;

    public Circle(int cx, int cy, int r, String color) {
        super(color);
        this.cx = cx; this.cy = cy; this.r = r;
    }

    @Override
    public void translate(int dx, int dy) {
        cx += dx; cy += dy;
    }

    @Override
    public String toSvg() {
        return String.format("<circle cx=\"%d\" cy=\"%d\" r=\"%d\" fill=\"%s\" />", cx, cy, r, color);
    }

    @Override
    public String toString() {
        return String.format("circle %d %d %d %s", cx, cy, r, color);
    }

    @Override
    public String getType() {
        return "circle";
    }
}
