public class Rectangle {
    private int height;
    private int width;
    public Rectangle(int height, int width) {
        setHeight(height);
        setWidth(width);
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return this.height;
    }
    public int getWidth() {
        return this.width;
    }
    public int computeArea() {
        return getHeight() * getWidth();
    }
}
