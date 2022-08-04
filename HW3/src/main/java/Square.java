public class Square {
    private IRectangle iRectangle;
    public Square(int side) {
        this.iRectangle = new Rectangle(side, side);
    }
    public int computeArea() {
        return iRectangle.computeArea();
    }
}
