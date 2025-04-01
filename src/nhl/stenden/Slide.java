package nhl.stenden;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>A slide. This class has a drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide
{
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title; // title is saved separately
    protected List<SlideItem> slides; // slide items are saved in a Vector

    public Slide()
    {
        this.title = "";
        this.slides = new ArrayList<>();
    }

    // give the title of the slide
    public String getTitle()
    {
        return this.title;
    }

    // change the title of the slide
    public void setTitle(String title)
    {
        this.title = title;
    }

    // Create TextItem of String, and add the TextItem
    public void append(int level, String message)
    {
        if (message == null) {
            return;
        }

        this.append(new TextItem(level, message));
    }

    // Add a slide item
    public void append(SlideItem slide)
    {
        if (slide == null) {
            return;
        }

        this.slides.add(slide);
    }

    // give the  SlideItem
    public SlideItem getSlide(int number)
    {
        if (number < 0 || number >= this.getSize())
        {
            return null;
        }

        return this.slides.get(number);
    }

    // give all SlideItems in a Vector
    public List<SlideItem> getSlides()
    {
        return this.slides;
    }

    // give the size of the Slide
    public int getSize()
    {
        return this.slides.size();
    }

    // draw the slide
    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        if (g == null || area == null)
        {
            return;
        }

        float scale = this.getScale(area);
        int currentY = area.y;

        // Draw the title
        SlideItem titleItem = new TextItem(0, this.getTitle());
        Style titleStyle = Style.getStyle(titleItem.getLevel());
        titleItem.draw(area.x, currentY, scale, g, titleStyle, view);
        currentY += titleItem.getBoundingBox(g, view, scale, titleStyle).height;

        // Draw all slide items
        for (SlideItem slide : this.slides)
        {
            Style itemStyle = Style.getStyle(slide.getLevel());
            slide.draw(area.x, currentY, scale, g, itemStyle, view);
            currentY += slide.getBoundingBox(g, view, scale, itemStyle).height;
        }
    }

    // Give the scale for drawing
    private float getScale(Rectangle area)
    {
        if (area == null) {
            return 1.0f;
        }
        return Math.min(((float) area.width) / WIDTH, ((float) area.height) / HEIGHT);
    }
}
