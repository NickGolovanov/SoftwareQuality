package nhl.stenden;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * Abstract base class for items in a slide.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Class handles only slide item properties and behavior
 * - Open-Closed Principle (OCP): Base class that can be extended for new item types without modification
 * - Liskov Substitution Principle (LSP): Defines contract that all subclasses must follow
 */
public abstract class SlideItem
{
    private int level = 0;

    public SlideItem(int level)
    {
        this.level = level;
    }

    // Give the level
    public int getLevel()
    {
        return this.level;
    }

    // Give the bounding box
    public abstract Rectangle getBoundingBox(Graphics g,
                                             ImageObserver observer, float scale, Style style);

    // Draw the item
    public abstract void draw(int x, int y, float scale,
                              Graphics g, Style style, ImageObserver observer);
}
