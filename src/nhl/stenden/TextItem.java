package nhl.stenden;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.ArrayList;

/**
 * TextItem class for handling text content in slides.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Handles only text-specific slide item functionality
 * - Liskov Substitution Principle (LSP): Properly implements all SlideItem abstract methods
 * - Open-Closed Principle (OCP): Can be extended for specialized text items without modification
 * - Interface Segregation Principle (ISP): Implements only the methods it needs from SlideItem
 */
public class TextItem extends SlideItem
{
    private String text;

    public TextItem(int level, String text)
    {
        super(level);
        this.text = text;
    }

    // An empty text-item
    public TextItem()
    {
        this(0, null);
    }

    public String getText()
    {
        return this.text == null ? "" : this.text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attributedString = new AttributedString(this.getText());
        attributedString.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());
        return attributedString;
    }

    private Graphics2D getGraphics2D(Graphics graphics)
    {
        return (graphics instanceof Graphics2D) ? (Graphics2D) graphics : null;
    }

    @Override
    public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, Style style)
    {
        Graphics2D g2d = getGraphics2D(graphics);
        if (g2d == null)
        {
            return new Rectangle();
        }

        List<TextLayout> layouts = this.getLayouts(g2d, style, scale);
        int maxWidth = 0;
        int totalHeight = (int) (style.getLeading() * scale);

        for (TextLayout layout : layouts)
        {
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > maxWidth)
            {
                maxWidth = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0)
            {
                totalHeight += (int) bounds.getHeight();
            }
            totalHeight += (int) (layout.getLeading() + layout.getDescent());
        }

        return new Rectangle((int) (style.getIndent() * scale), 0, maxWidth, totalHeight);
    }

    @Override
    public void draw(int x, int y, float scale, Graphics graphics, Style style, ImageObserver observer)
    {
        Graphics2D g2d = getGraphics2D(graphics);
        if (g2d == null)
        {
            return;
        }

        List<TextLayout> layouts = this.getLayouts(g2d, style, scale);
        if (layouts.isEmpty())
        {
            return;
        }

        Point penPosition = new Point(
                x + (int) (style.getIndent() * scale),
                y + (int) (style.getLeading() * scale)
        );

        g2d.setColor(style.getColor());

        for (TextLayout layout : layouts)
        {
            penPosition.y += (int) layout.getAscent();
            layout.draw(g2d, penPosition.x, penPosition.y);
            penPosition.y += (int) layout.getDescent();
        }
    }

    private List<TextLayout> getLayouts(Graphics2D graphics, Style style, float scale)
    {
        List<TextLayout> layouts = new ArrayList<>();

        if (graphics == null || style == null || this.getText().isEmpty())
        {
            return layouts;
        }

        try
        {
            AttributedString attributedString = this.getAttributedString(style, scale);
            FontRenderContext fontRenderContext = graphics.getFontRenderContext();

            if (fontRenderContext != null)
            {
                TextLayout layout = new TextLayout(attributedString.getIterator(), fontRenderContext);
                layouts.add(layout);
            }
        } catch (Exception e)
        {
            System.err.println("Error creating text layout: " + e.getMessage());
        }

        return layouts;
    }

    @Override
    public String toString()
    {
        return String.format("TextItem[%d,%s]", this.getLevel(), this.getText());
    }
}
