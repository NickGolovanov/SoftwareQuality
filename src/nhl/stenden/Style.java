package nhl.stenden;

import java.awt.*;

/**
 * <p>Style is for Indent, Color, Font and Leading.</p>
 * <p>Direct relation between style-number and item-level:
 * in Slide style if fetched for an item
 * with style-number as item-level.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style
{
    private static Style[] styles; // de styles

    private static final String FONT_NAME = "Helvetica";
    private int indent;
    private Color color;
    private Font font;
    private int fontSize;
    private int leading;

    public static void createStyles()
    {
        styles = new Style[5];
        // The styles are fixed.
        styles[0] = new Style(0, Color.red, 48, 20);    // style for item-level 0
        styles[1] = new Style(20, Color.blue, 40, 10);    // style for item-level 1
        styles[2] = new Style(50, Color.black, 36, 10);    // style for item-level 2
        styles[3] = new Style(70, Color.black, 30, 10);    // style for item-level 3
        styles[4] = new Style(90, Color.black, 24, 10);    // style for item-level 4
    }

    public static Style getStyle(int level)
    {
        if (level >= styles.length)
        {
            level = styles.length - 1;
        }
        return styles[level];
    }

    public Style(int indent, Color color, int points, int leading)
    {
        this.indent = indent;
        this.color = color;
        this.font = new Font(FONT_NAME, Font.BOLD, this.fontSize = points);
        this.leading = leading;
    }

    public String toString()
    {
        return "[" + this.indent + "," + this.color + "; " + this.fontSize + " on " + this.leading + "]";
    }

    public Font getFont(float scale)
    {
        return this.font.deriveFont(fontSize * scale);
    }

    public int getIndent()
    {
        return this.indent;
    }

    public Color getColor()
    {
        return this.color;
    }

    public int getLeading()
    {
        return this.leading;
    }
}
