public class Rectangle implements IRectangle {
    private int height;
    private int width;
    public Rectangle(int height, int width) {
        setHeight(height);
        setWidth(width);
    }
    @Override
    public void setHeight(int height) {
        if (height <= 0)
            throw new NonPositiveArgException("Height must be positive");
        this.height = height;
    }
    @Override
    public void setWidth(int width) {
        if (width <= 0)
            throw new NonPositiveArgException("Width must be positive");
        this.width = width;
    }
    @Override
    public int getHeight() {
        return this.height;
    }
    @Override
    public int getWidth() {
        return this.width;
    }
    @Override
    public int computeArea() {
        return getHeight() * getWidth();
    }
}
