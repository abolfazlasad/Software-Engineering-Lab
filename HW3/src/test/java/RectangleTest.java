import org.junit.Test;

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
    @Test(expected = NonPositiveArgException.class)
    public void checkNonPositiveExceptionInHeight() {
        // Arrange
        Rectangle rectangle = new Rectangle(3, 3);
        // Act
        rectangle.setHeight(-1);
    }
    @Test(expected = NonPositiveArgException.class)
    public void checkNonPositiveExceptionInWeight() {
        // Arrange
        Rectangle rectangle = new Rectangle(7, -3);
    }
}
