public class Square {
    private IRectangle iRectangle;
    public Square(int side) {
        this.iRectangle = new Rectangle(side, side);
    }
    public int computeArea() {
        return iRectangle.computeArea();
    }
    public void setSide(int side) {
        iRectangle.setHeight(side);
        iRectangle.setWidth(side);
    }
    public int getSide() {
        return iRectangle.getHeight();
    }
}
