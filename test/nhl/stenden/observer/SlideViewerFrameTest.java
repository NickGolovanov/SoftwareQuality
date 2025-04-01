package nhl.stenden.observer;

import nhl.stenden.Slide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerFrameTest {
    private SlideViewerFrame frame;
    private SlideViewerComponent component;
    private Presentation presentation;
    private Slide testSlide;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        component = new SlideViewerComponent(presentation);
        frame = new SlideViewerFrame("Test Frame", component, presentation);
        testSlide = new Slide();
        testSlide.setTitle("Test Slide");
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }

    @Test
    @DisplayName("Should initialize with correct properties")
    void testInitialization() {
        assertEquals("Jabberpoint 1.6 - OU", frame.getTitle());
        assertEquals(1200, frame.getWidth());
        assertEquals(800, frame.getHeight());
        assertTrue(frame.isVisible());
    }

    @Test
    @DisplayName("Should update title when slide changes")
    void testUpdate() {
        ((Observer)frame).update(testSlide);
        assertEquals(testSlide.getTitle(), frame.getTitle());
    }

    @Test
    @DisplayName("Should handle null slide update")
    void testUpdateWithNull() {
        String originalTitle = frame.getTitle();
        ((Observer)frame).update(null);
        assertEquals(originalTitle, frame.getTitle());
    }

    @Test
    @DisplayName("Should have correct menu bar")
    void testMenuBar() {
        assertNotNull(frame.getMenuBar());
    }

    @Test
    @DisplayName("Should have correct key listeners")
    void testKeyListeners() {
        assertTrue(frame.getKeyListeners().length > 0);
    }

    @Test
    @DisplayName("Should have correct content pane")
    void testContentPane() {
        assertTrue(frame.getContentPane().getComponents().length > 0);
        assertTrue(frame.getContentPane().getComponent(0) instanceof SlideViewerComponent);
    }
} 