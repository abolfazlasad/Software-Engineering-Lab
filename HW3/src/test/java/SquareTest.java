import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareTest {
    @Test
    public void checkComputeArea() {
        // Arrange
        Square square = new Square(3);
        // Act
        int area = square.computeArea();
        // Assert
        assertEquals(9, area);
    }
    @Test
    public void checkGetterAndSetterForSide() {
        // Arrange
        Square square = new Square(1);
        // Act
        square.setSide(3);
        // Assert
        assertEquals(3, square.getSide());
    }
    @Test
    public void checkSquareImplementsISquare() {
        // Arrange
        ISquare iSquare = new Square(3);
        // Act
        iSquare.setSide(6);
        // Assert
        assertEquals(6, iSquare.getSide());
        assertEquals(36, iSquare.computeArea());
    }
}
