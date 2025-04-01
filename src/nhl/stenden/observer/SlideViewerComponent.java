package nhl.stenden.observer;

import nhl.stenden.Slide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;


/**
 * <p>SlideViewerComponent is a graphical component that can show slides.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent implements Observer
{
    private Slide currentSlide;
    private Font labelFont;
    private Presentation presentation;

    private static final long serialVersionUID = 227L;

    private static final Color BG_COLOR = Color.white;
    private static final Color COLOR = Color.black;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_HEIGHT = 10;
    private static final int XPOS = 1100;
    private static final int YPOS = 20;

    public SlideViewerComponent(Presentation presentation)
    {
        this.setBackground(BG_COLOR);
        this.presentation = presentation;
        this.labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT);
    }

    public Slide getCurrentSlide()
    {
        return this.currentSlide;
    }

    public void setCurrentSlide(Slide currentSlide)
    {
        this.currentSlide = currentSlide;
    }

    public Font getLabelFont()
    {
        return this.labelFont;
    }

    public void setLabelFont(Font labelFont)
    {
        this.labelFont = labelFont;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    // draw the slide
    public void paintComponent(Graphics g)
    {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);

        if (this.presentation.getSlideNumber() < 0 || this.currentSlide == null)
        {
            return;
        }

        g.setFont(this.labelFont);
        g.setColor(COLOR);
        g.drawString("Slide " + (1 + this.presentation.getSlideNumber()) + " of " + this.presentation.getSize(), XPOS, YPOS);

        Rectangle area = new Rectangle(0, YPOS, this.getWidth(), (this.getHeight() - YPOS));

        this.currentSlide.draw(g, area, this);
    }


    @Override
    public void update(Slide slide)
    {
        if (slide == null)
        {
            this.repaint();
            return;
        }

        this.currentSlide = slide;
        this.repaint();
    }
}
