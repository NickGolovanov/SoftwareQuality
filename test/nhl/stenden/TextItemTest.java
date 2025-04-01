package nhl.stenden;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TextItemTest
{
    private TextItem textItem;
    private static final String TEST_TEXT = "Test Text";

    @Mock
    private Graphics2D graphics2D;

    @Mock
    private Style style;

    @Mock
    private Font font;

    @Mock
    private FontRenderContext fontRenderContext;

    @Mock
    private LineMetrics lineMetrics;

    @Mock
    private ImageObserver observer;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        textItem = new TextItem(1, TEST_TEXT);

        // Setup common mock behaviors
        when(style.getFont(anyFloat())).thenReturn(font);
        when(style.getIndent()).thenReturn(20);
        when(style.getLeading()).thenReturn(10);
        when(style.getColor()).thenReturn(Color.BLACK);

        // Setup FontRenderContext mock
        when(graphics2D.getFontRenderContext()).thenReturn(fontRenderContext);
    }

    @Test
    void testConstructorWithText()
    {
        assertEquals(TEST_TEXT, textItem.getText());
        assertEquals(1, textItem.getLevel());
    }

    @Test
    void testGetText()
    {
        assertEquals(TEST_TEXT, textItem.getText());
    }

    @Test
    void testGetTextWithNull()
    {
        TextItem nullItem = new TextItem(1, null);
        assertEquals("", nullItem.getText());
    }

    @Test
    void testGetAttributedString()
    {
        AttributedString attributedString = textItem.getAttributedString(style, 1.0f);
        assertNotNull(attributedString);

        // Get the iterator and verify its content
        AttributedCharacterIterator iterator = attributedString.getIterator();
        StringBuilder text = new StringBuilder();
        for (char c = iterator.first(); c != AttributedCharacterIterator.DONE; c = iterator.next())
        {
            text.append(c);
        }
        assertEquals(TEST_TEXT, text.toString());
    }

    @Test
    void testGetBoundingBox()
    {
        Rectangle boundingBox = textItem.getBoundingBox(graphics2D, observer, 1.0f, style);
        assertNotNull(boundingBox);
        assertTrue(boundingBox.width >= 0);
        assertTrue(boundingBox.height >= 0);
    }

    @Test
    void testGetBoundingBoxWithDifferentScales()
    {
        float[] scales = {0.5f, 1.0f, 2.0f};
        for (float scale : scales)
        {
            Rectangle boundingBox = textItem.getBoundingBox(graphics2D, observer, scale, style);
            assertNotNull(boundingBox);
            assertTrue(boundingBox.width >= 0);
            assertTrue(boundingBox.height >= 0);
        }
    }

    @Test
    void testDraw()
    {
        // Create a real TextLayout with mocked dependencies
        TextLayout textLayout = new TextLayout(TEST_TEXT, font, fontRenderContext);
        
        // Draw the text item
        textItem.draw(0, 0, 1.0f, graphics2D, style, observer);
        
        // Verify the color was set and text was drawn
        verify(graphics2D).setColor(eq(style.getColor()));
    }

    @Test
    void testDrawWithDifferentPositions()
    {
        // Create a spy of the textItem to intercept getAttributedString
        TextItem spyTextItem = spy(textItem);
        AttributedString attributedString = new AttributedString(TEST_TEXT);
        when(spyTextItem.getAttributedString(any(), anyFloat())).thenReturn(attributedString);
        
        int[][] positions = {{0, 0}, {100, 100}, {200, 200}};
        for (int[] pos : positions)
        {
            spyTextItem.draw(pos[0], pos[1], 1.0f, graphics2D, style, observer);
            verify(graphics2D).setColor(eq(style.getColor()));
            clearInvocations(graphics2D);
        }
    }

    @Test
    void testDrawWithNullText()
    {
        TextItem nullItem = new TextItem(1, null);
        nullItem.draw(0, 0, 1.0f, graphics2D, style, observer);
        verify(graphics2D, never()).setColor(any(Color.class));
    }

    @Test
    void testToString()
    {
        String expected = "TextItem[1," + TEST_TEXT + "]";
        assertEquals(expected, textItem.toString());
    }
} 