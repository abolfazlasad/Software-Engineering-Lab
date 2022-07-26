import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class RectangleTest {
    @Test
    public void checkComputeArea() {
        // Arrange
        Rectangle rectangle = new Rectangle(3, 2);
        // Act
        int area = rectangle.computeArea();
        // Assert
        assertEquals(6, area);
    }
    @Test
    public void checkSetterGetterForHeight() {
        // Arrange
        Rectangle rectangle = new Rectangle(4, 3);
        // Act
        rectangle.setHeight(10);
        // Assert
        assertEquals(10, rectangle.getHeight());
    }
    @Test
    public void checkSetterGetterForWidth() {
        // Arrange
        Rectangle rectangle = new Rectangle(3, 3);
        // Act
        rectangle.setWidth(2);
        // Assert
        assertEquals(2, rectangle.getWidth());
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void checkNonPositiveExceptionInHeight() {
        // Arrange
        Rectangle rectangle = new Rectangle(3, 3);
        thrown.expect(NonPositiveArgException.class);
        thrown.expectMessage("Height must be positive");
        // Act
        rectangle.setHeight(-1);
    }
    @Test
    public void checkNonPositiveExceptionInWidth() {
        // Arrange
        thrown.expect(NonPositiveArgException.class);
        thrown.expectMessage("Width must be positive");
        // Act
        Rectangle rectangle = new Rectangle(7, -3);
    }
    @Test
    public void checkRectangleImplementsIRectangle() {
        // Arrange
        IRectangle iRectangle =  new Rectangle(2, 1);
        // Act
        iRectangle.setHeight(9);
        iRectangle.setWidth(3);
        // Assert
        assertEquals(9, iRectangle.getHeight());
        assertEquals(3, iRectangle.getWidth());
        assertEquals(27, iRectangle.computeArea());
    }
}
