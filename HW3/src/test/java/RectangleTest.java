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
}
