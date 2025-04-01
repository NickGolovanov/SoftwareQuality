package nhl.stenden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BitmapItemTest {
    private BitmapItem bitmapItem;
    private static final String TEST_IMAGE_NAME = "test.jpg";
    private static final int TEST_LEVEL = 2;

    @Mock
    private Graphics graphics;

    @Mock
    private ImageObserver observer;

    @Mock
    private Style style;

    @Mock
    private BufferedImage bufferedImage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Mock ImageIO.read to return our mock BufferedImage
        try (MockedStatic<ImageIO> mockedImageIO = mockStatic(ImageIO.class)) {
            mockedImageIO.when(() -> ImageIO.read(any(File.class))).thenReturn(bufferedImage);
            
            bitmapItem = new BitmapItem(TEST_LEVEL, TEST_IMAGE_NAME);
        }
        
        // Setup common mock behaviors
        when(style.getIndent()).thenReturn(10);
        when(style.getLeading()).thenReturn(5);
        when(bufferedImage.getWidth(observer)).thenReturn(100);
        when(bufferedImage.getHeight(observer)).thenReturn(50);
    }

    @Test
    void testConstructorWithLevelAndName() {
        assertEquals(TEST_LEVEL, bitmapItem.getLevel());
        assertEquals(TEST_IMAGE_NAME, bitmapItem.getImageName());
    }

    @Test
    void testGetImageName() {
        assertEquals(TEST_IMAGE_NAME, bitmapItem.getImageName());
    }

    @Test
    void testGetBoundingBox() {
        Rectangle bounds = bitmapItem.getBoundingBox(graphics, observer, 1.0f, style);
        assertNotNull(bounds);
        assertEquals(10, bounds.x); // style.getIndent() * scale
        assertEquals(0, bounds.y);
        assertEquals(100, bounds.width);
        assertEquals(55, bounds.height); // style.getLeading() + image height
    }

    @Test
    void testDraw() {
        bitmapItem.draw(0, 0, 1.0f, graphics, style, observer);
        
        verify(graphics).drawImage(
            any(BufferedImage.class),
            eq(10), // x + style.getIndent() * scale
            eq(5),  // y + style.getLeading() * scale
            eq(100), // width
            eq(50),  // height
            eq(observer)
        );
    }

    @Test
    void testToString() {
        String expected = String.format("BitmapItem[%d,%s]", TEST_LEVEL, TEST_IMAGE_NAME);
        assertEquals(expected, bitmapItem.toString());
    }

    @Test
    void testGetBoundingBoxWithDifferentScales() {
        float[] scales = {0.5f, 1.0f, 2.0f};
        for (float scale : scales) {
            Rectangle bounds = bitmapItem.getBoundingBox(graphics, observer, scale, style);
            assertNotNull(bounds);
            assertEquals((int)(10 * scale), bounds.x); // style.getIndent() * scale
            assertEquals(0, bounds.y);
            assertEquals((int)(100 * scale), bounds.width);
            assertEquals((int)(5 * scale) + (int)(50 * scale), bounds.height);
        }
    }

    @Test
    void testDrawWithDifferentPositions() {
        int[][] positions = {{0, 0}, {100, 100}, {-100, -100}};
        for (int[] pos : positions) {
            bitmapItem.draw(pos[0], pos[1], 1.0f, graphics, style, observer);
            verify(graphics).drawImage(
                any(BufferedImage.class),
                eq(pos[0] + 10), // x + style.getIndent() * scale
                eq(pos[1] + 5),  // y + style.getLeading() * scale
                eq(100), // width
                eq(50),  // height
                eq(observer)
            );
        }
    }

    @Test
    void testConstructorWithInvalidImage() {
        try (MockedStatic<ImageIO> mockedImageIO = mockStatic(ImageIO.class)) {
            mockedImageIO.when(() -> ImageIO.read(any(File.class))).thenThrow(new IOException());
            
            BitmapItem invalidItem = new BitmapItem(1, "nonexistent.jpg");
            assertNotNull(invalidItem);
            assertEquals(1, invalidItem.getLevel());
            assertEquals("nonexistent.jpg", invalidItem.getImageName());
        }
    }
}
