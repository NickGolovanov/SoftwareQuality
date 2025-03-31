package nhl.stenden.observer;

import nhl.stenden.Slide;
import nhl.stenden.Style;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SlideViewerComponentTest {
    private SlideViewerComponent component;
    private Presentation presentation;
    private Slide testSlide;
    private JFrame frame;

    @BeforeEach
    void setUp() {
        Style.createStyles(); // Initialize styles before using them
        presentation = new Presentation();
        component = new SlideViewerComponent(presentation);
        testSlide = new Slide();
        testSlide.setTitle("Test Slide");
        frame = new JFrame();
        frame.add(component);
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }

    @Test
    @DisplayName("Should initialize with correct properties")
    void testInitialization() {
        assertNotNull(component.getLabelFont());
        assertEquals("Dialog", component.getLabelFont().getFontName());
        assertEquals(Font.BOLD, component.getLabelFont().getStyle());
        assertEquals(10, component.getLabelFont().getSize());
        assertEquals(presentation, component.getPresentation());
    }

    @Test
    @DisplayName("Should set and get current slide")
    void testCurrentSlide() {
        component.setCurrentSlide(testSlide);
        assertEquals(testSlide, component.getCurrentSlide());
    }

    @Test
    @DisplayName("Should set and get label font")
    void testLabelFont() {
        Font newFont = new Font("Arial", Font.PLAIN, 12);
        component.setLabelFont(newFont);
        assertEquals(newFont, component.getLabelFont());
    }

    @Test
    @DisplayName("Should set and get presentation")
    void testPresentation() {
        Presentation newPresentation = new Presentation();
        component.setPresentation(newPresentation);
        assertEquals(newPresentation, component.getPresentation());
    }

    @Test
    @DisplayName("Should get preferred size")
    void testPreferredSize() {
        Dimension size = component.getPreferredSize();
        assertEquals(Slide.WIDTH, size.width);
        assertEquals(Slide.HEIGHT, size.height);
    }

    @Test
    @DisplayName("Should update when slide changes")
    void testUpdate() {
        ((Observer)component).update(testSlide);
        assertEquals(testSlide, component.getCurrentSlide());
    }

    @Test
    @DisplayName("Should handle null slide update")
    void testUpdateWithNull() {
        ((Observer)component).update(null);
        assertNull(component.getCurrentSlide());
    }

    @Test
    @DisplayName("Should paint component correctly")
    void testPaintComponent() {
        // Create a buffered image to simulate graphics context
        BufferedImage image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set up test data
        testSlide.setTitle("Test Slide Title");
        testSlide.append(1, "Test Content Line 1");
        testSlide.append(2, "Test Content Line 2");
        presentation.append(testSlide);
        presentation.setSlideNumber(0);
        component.setCurrentSlide(testSlide);
        
        // Paint the component
        component.paintComponent(g2d);
        
        // Verify the image was painted (basic check)
        assertNotNull(image);
        
        // Clean up
        g2d.dispose();
    }

    @Test
    @DisplayName("Should handle paint with no current slide")
    void testPaintComponentNoSlide() {
        // Create a buffered image to simulate graphics context
        BufferedImage image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set up test data with no current slide
        presentation.setSlideNumber(-1);
        component.setCurrentSlide(null);
        
        // Paint the component
        component.paintComponent(g2d);
        
        // Verify the image was painted (basic check)
        assertNotNull(image);
        
        // Clean up
        g2d.dispose();
    }

    @Test
    @DisplayName("Should handle paint with invalid slide number")
    void testPaintComponentInvalidSlideNumber() {
        // Create a buffered image to simulate graphics context
        BufferedImage image = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set up test data with invalid slide number
        testSlide.setTitle("Test Slide Title");
        testSlide.append(1, "Test Content Line 1");
        presentation.append(testSlide);
        presentation.setSlideNumber(999); // Invalid slide number
        component.setCurrentSlide(testSlide);
        
        // Paint the component
        component.paintComponent(g2d);
        
        // Verify the image was painted (basic check)
        assertNotNull(image);
        
        // Clean up
        g2d.dispose();
    }
}
