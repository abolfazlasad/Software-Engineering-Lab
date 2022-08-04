public class Rectangle {
    private int height;
    private int width;
    public Rectangle(int height, int width) {
        setHeight(height);
        setWidth(width);
    }
    public void setHeight(int height) {
        if (height <= 0)
            throw new NonPositiveArgException("Height must be positive");
        this.height = height;
    }
    public void setWidth(int width) {
        if (width <= 0)
            throw new NonPositiveArgException("Width must be positive");
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
