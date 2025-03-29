package nhl.stenden;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

class BitmapItemTest {

    @Test
    void testBitmapItemInitializationWithValidImage() {
        BitmapItem bitmapItem = new BitmapItem(1, "src/nhl/stenden/test_image.png"); // Use a valid image path
        assertNotNull(bitmapItem);
        assertEquals("src/nhl/stenden/test_image.png", bitmapItem.getImageName());
    }

    @Test
    void testBitmapItemInitializationWithInvalidImage() {
        BitmapItem bitmapItem = new BitmapItem(1, "invalid_image_path.png");
        assertNotNull(bitmapItem);
        assertEquals("invalid_image_path.png", bitmapItem.getImageName());
    }

    @Test
    void testGetImageName() {
        BitmapItem bitmapItem = new BitmapItem(1, "image.png");
        assertEquals("image.png", bitmapItem.getImageName());
    }

    @Test
    void testGetBoundingBox() {
        BitmapItem bitmapItem = new BitmapItem(1, "src/nhl/stenden/test_image.png"); // Use a valid image path
        Graphics g = null; // Mock or create a Graphics object as needed
        ImageObserver observer = null; // Mock or create an ImageObserver as needed
        Style style = new Style(0, null, 0, 0); // Provide necessary arguments for Style
        Rectangle boundingBox = bitmapItem.getBoundingBox(g, observer, 1.0f, style);
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width > 0);
        assertTrue(boundingBox.height > 0);
    }

    @Test
    void testDraw() {
        BitmapItem bitmapItem = new BitmapItem(1, "src/nhl/stenden/test_image.png");
        Graphics g = null; // Mock or create a Graphics object as needed
        ImageObserver observer = null; // Mock or create an ImageObserver as needed
        Style style = new Style(0, null, 0, 0); // Provide necessary arguments for Style
        bitmapItem.draw(0, 0, 1.0f, g, style, observer);
        // Add assertions to verify drawing behavior if possible
    }
}
