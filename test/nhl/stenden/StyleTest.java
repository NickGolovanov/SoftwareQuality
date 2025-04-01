package nhl.stenden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StyleTest {
    private Style style;
    private static final int TEST_INDENT = 20;
    private static final Color TEST_COLOR = Color.BLUE;
    private static final int TEST_POINTS = 40;
    private static final int TEST_LEADING = 10;

    @BeforeEach
    void setUp() {
        Style.createStyles();
        style = new Style(TEST_INDENT, TEST_COLOR, TEST_POINTS, TEST_LEADING);
    }

    @Test
    void testCreateStyles() {
        Style.createStyles();
        
        // Test each style level
        for (int i = 0; i < 5; i++) {
            Style s = Style.getStyle(i);
            assertNotNull(s);
            switch (i) {
                case 0:
                    assertEquals(0, s.getIndent());
                    assertEquals(Color.red, s.getColor());
                    assertEquals(48, s.getFont(1.0f).getSize());
                    assertEquals(20, s.getLeading());
                    break;
                case 1:
                    assertEquals(20, s.getIndent());
                    assertEquals(Color.blue, s.getColor());
                    assertEquals(40, s.getFont(1.0f).getSize());
                    assertEquals(10, s.getLeading());
                    break;
                case 2:
                    assertEquals(50, s.getIndent());
                    assertEquals(Color.black, s.getColor());
                    assertEquals(36, s.getFont(1.0f).getSize());
                    assertEquals(10, s.getLeading());
                    break;
                case 3:
                    assertEquals(70, s.getIndent());
                    assertEquals(Color.black, s.getColor());
                    assertEquals(30, s.getFont(1.0f).getSize());
                    assertEquals(10, s.getLeading());
                    break;
                case 4:
                    assertEquals(90, s.getIndent());
                    assertEquals(Color.black, s.getColor());
                    assertEquals(24, s.getFont(1.0f).getSize());
                    assertEquals(10, s.getLeading());
                    break;
            }
        }
    }

    @Test
    void testGetStyleWithValidLevel() {
        Style s = Style.getStyle(2);
        assertNotNull(s);
        assertEquals(50, s.getIndent());
        assertEquals(Color.black, s.getColor());
        assertEquals(36, s.getFont(1.0f).getSize());
        assertEquals(10, s.getLeading());
    }

    @Test
    void testGetStyleWithInvalidLevel() {
        Style s = Style.getStyle(10); // Level beyond array bounds
        assertNotNull(s);
        assertEquals(90, s.getIndent()); // Should return last style
        assertEquals(Color.black, s.getColor());
        assertEquals(24, s.getFont(1.0f).getSize());
        assertEquals(10, s.getLeading());
    }

    @Test
    void testGetStyleWithNegativeLevel() {
        Style s = Style.getStyle(-1);
        assertNotNull(s);
        assertEquals(0, s.getIndent()); // Should return first style
        assertEquals(Color.red, s.getColor());
        assertEquals(48, s.getFont(1.0f).getSize());
        assertEquals(20, s.getLeading());
    }

    @Test
    void testConstructor() {
        assertEquals(TEST_INDENT, style.getIndent());
        assertEquals(TEST_COLOR, style.getColor());
        assertEquals(TEST_POINTS, style.getFont(1.0f).getSize());
        assertEquals(TEST_LEADING, style.getLeading());
    }

    @Test
    void testGetFontWithDifferentScales() {
        float[] scales = {0.5f, 1.0f, 2.0f};
        for (float scale : scales) {
            Font font = style.getFont(scale);
            assertEquals(TEST_POINTS * scale, font.getSize());
            assertEquals("Helvetica", font.getName());
            assertEquals(Font.BOLD, font.getStyle());
        }
    }

    @Test
    void testGetIndent() {
        assertEquals(TEST_INDENT, style.getIndent());
    }

    @Test
    void testGetColor() {
        assertEquals(TEST_COLOR, style.getColor());
    }

    @Test
    void testGetLeading() {
        assertEquals(TEST_LEADING, style.getLeading());
    }

    @Test
    void testToString() {
        String expected = String.format("[%d,%s; %d on %d]", 
            TEST_INDENT, TEST_COLOR, TEST_POINTS, TEST_LEADING);
        assertEquals(expected, style.toString());
    }
} 