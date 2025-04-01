package nhl.stenden;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a text item within a slide.
 * TextItem provides functionality for drawing and measuring text with specific styles and formatting.
 */
public class TextItem extends SlideItem {
	private static final String EMPTY_TEXT = "No Text Given";
	
	private String text;

	/**
	 * Creates a new TextItem with specified level and text content.
	 *
	 * @param level The indentation level of the text
	 * @param text The content of the text item
	 */
	public TextItem(int level, String text) {
		super(level);
		this.text = text;
	}

	/**
	 * Creates an empty TextItem with default level and text.
	 */
	public TextItem() {
		this(0, EMPTY_TEXT);
	}

	/**
	 * Returns the text content of this item.
	 *
	 * @return The text content, or empty string if text is null
	 */
	public String getText() {
		return this.text == null ? "" : this.text;
	}

	/**
	 * Creates an AttributedString with the specified style and scale.
	 *
	 * @param style The style to apply to the text
	 * @param scale The scale factor for the text
	 * @return An AttributedString with the applied style
	 */
	public AttributedString getAttributedString(Style style, float scale) {
		AttributedString attributedString = new AttributedString(this.getText());
		attributedString.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());
		return attributedString;
	}

	/**
	 * Calculates the bounding box for this text item.
	 *
	 * @param graphics The graphics context
	 * @param observer The image observer
	 * @param scale The scale factor
	 * @param style The style to apply
	 * @return A Rectangle representing the bounding box
	 */
	@Override
	public Rectangle getBoundingBox(Graphics graphics, ImageObserver observer, float scale, Style style) {
		List<TextLayout> layouts = this.getLayouts(graphics, style, scale);
		int maxWidth = 0;
		int totalHeight = (int) (style.getLeading() * scale);
		
		for (TextLayout layout : layouts) {
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > maxWidth) {
				maxWidth = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				totalHeight += bounds.getHeight();
			}
			totalHeight += layout.getLeading() + layout.getDescent();
		}
		
		return new Rectangle((int) (style.getIndent() * scale), 0, maxWidth, totalHeight);
	}

	/**
	 * Draws the text item on the specified graphics context.
	 *
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param scale The scale factor
	 * @param graphics The graphics context
	 * @param style The style to apply
	 * @param observer The image observer
	 */
	@Override
	public void draw(int x, int y, float scale, Graphics graphics, Style style, ImageObserver observer) {
		if (this.text == null || this.text.isEmpty()) {
			return;
		}
		
		List<TextLayout> layouts = this.getLayouts(graphics, style, scale);
		Point penPosition = new Point(
			x + (int)(style.getIndent() * scale),
			y + (int)(style.getLeading() * scale)
		);
		
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setColor(style.getColor());
		
		for (TextLayout layout : layouts) {
			penPosition.y += layout.getAscent();
			layout.draw(graphics2D, penPosition.x, penPosition.y);
			penPosition.y += layout.getDescent();
		}
	}

	/**
	 * Creates a list of TextLayout objects for the text content.
	 *
	 * @param graphics The graphics context
	 * @param style The style to apply
	 * @param scale The scale factor
	 * @return A list of TextLayout objects
	 */
	private List<TextLayout> getLayouts(Graphics graphics, Style style, float scale) {
		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attributedString = this.getAttributedString(style, scale);
		Graphics2D graphics2D = (Graphics2D) graphics;
		FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
		
		// Create a single TextLayout for the entire text
		TextLayout layout = new TextLayout(attributedString.getIterator(), fontRenderContext);
		layouts.add(layout);
		
		return layouts;
	}

	@Override
	public String toString() {
		return String.format("TextItem[%d,%s]", this.getLevel(), this.getText());
	}
}
