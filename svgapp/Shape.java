package svgapp;

public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract void translate(int dx, int dy);
    public abstract String toSvg();
    public abstract String toString();
    public abstract String getType();
}
