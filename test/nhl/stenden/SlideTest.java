package nhl.stenden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.ImageObserver;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SlideTest {
    private Slide slide;
    private static final String TEST_TITLE = "Test Slide";

    @Mock
    private Graphics2D graphics2D;

    @Mock
    private ImageObserver observer;

    @Mock
    private TextItem textItem;

    @Mock
    private BitmapItem bitmapItem;

    @Mock
    private FontRenderContext fontRenderContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        slide = new Slide();
        slide.setTitle(TEST_TITLE);
        
        // Setup common mock behaviors
        when(textItem.getLevel()).thenReturn(1);
        when(textItem.getBoundingBox(any(), any(), anyFloat(), any())).thenReturn(new Rectangle(0, 0, 100, 20));
        when(bitmapItem.getLevel()).thenReturn(2);
        when(bitmapItem.getBoundingBox(any(), any(), anyFloat(), any())).thenReturn(new Rectangle(0, 0, 200, 100));
        
        // Setup Graphics2D mock
        when(graphics2D.getFontRenderContext()).thenReturn(fontRenderContext);
    }

    @Test
    void testConstructor() {
        Slide newSlide = new Slide();
        assertNotNull(newSlide);
        assertNull(newSlide.getTitle());
        assertEquals(0, newSlide.getSize());
    }

    @Test
    void testGetTitle() {
        assertEquals(TEST_TITLE, slide.getTitle());
    }

    @Test
    void testSetTitle() {
        String newTitle = "New Title";
        slide.setTitle(newTitle);
        assertEquals(newTitle, slide.getTitle());
    }

    @Test
    void testAppendWithLevelAndMessage() {
        slide.append(1, "Test Message");
        assertEquals(1, slide.getSize());
        assertTrue(slide.getSlide(0) instanceof TextItem);
        assertEquals(1, slide.getSlide(0).getLevel());
    }

    @Test
    void testAppendWithSlideItem() {
        slide.append(textItem);
        assertEquals(1, slide.getSize());
        assertEquals(textItem, slide.getSlide(0));
    }

    @Test
    void testGetSlide() {
        slide.append(textItem);
        assertEquals(textItem, slide.getSlide(0));
    }

    @Test
    void testGetSlides() {
        slide.append(textItem);
        slide.append(bitmapItem);
        
        Vector<SlideItem> slides = slide.getSlides();
        assertEquals(2, slides.size());
        assertEquals(textItem, slides.elementAt(0));
        assertEquals(bitmapItem, slides.elementAt(1));
    }

    @Test
    void testGetSize() {
        assertEquals(0, slide.getSize());
        slide.append(textItem);
        assertEquals(1, slide.getSize());
        slide.append(bitmapItem);
        assertEquals(2, slide.getSize());
    }

    @Test
    void testDraw() {
        slide.append(textItem);
        slide.append(bitmapItem);
        
        Rectangle area = new Rectangle(0, 0, 800, 600);
        slide.draw(graphics2D, area, observer);
        
        // Verify that draw was called for title and each slide item
        verify(textItem, times(1)).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
        verify(bitmapItem, times(1)).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
    }

    @Test
    void testDrawWithEmptySlide() {
        Rectangle area = new Rectangle(0, 0, 800, 600);
        slide.draw(graphics2D, area, observer);
        
        // Verify that draw was called only for the title
        verify(textItem, never()).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
        verify(bitmapItem, never()).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
    }

    @Test
    void testDrawWithDifferentAreas() {
        slide.append(textItem);
        slide.append(bitmapItem);
        
        Rectangle[] areas = {
            new Rectangle(0, 0, 800, 600),
            new Rectangle(0, 0, 1024, 768),
            new Rectangle(0, 0, 640, 480)
        };
        
        for (Rectangle area : areas) {
            slide.draw(graphics2D, area, observer);
            verify(textItem, atLeastOnce()).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
            verify(bitmapItem, atLeastOnce()).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
        }
    }

    @Test
    void testGetScale() {
        Rectangle[] areas = {
            new Rectangle(0, 0, 600, 400),  // Smaller than slide
            new Rectangle(0, 0, 1200, 800), // Equal to slide
            new Rectangle(0, 0, 2400, 1600) // Larger than slide
        };
        
        for (Rectangle area : areas) {
            slide.draw(graphics2D, area, observer);
            // Verify that draw was called with appropriate scaling
            verify(textItem, atLeastOnce()).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
            verify(bitmapItem, atLeastOnce()).draw(anyInt(), anyInt(), anyFloat(), any(), any(), any());
        }
    }
} 