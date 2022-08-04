public class Square implements ISquare {
    private IRectangle iRectangle;
    public Square(int side) {
        this.iRectangle = new Rectangle(side, side);
    }
    @Override
    public int computeArea() {
        return iRectangle.computeArea();
    }
    @Override
    public void setSide(int side) {
        iRectangle.setHeight(side);
        iRectangle.setWidth(side);
    }
    @Override
    public int getSide() {
        return iRectangle.getHeight();
    }
}
