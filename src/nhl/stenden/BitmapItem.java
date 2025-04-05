package nhl.stenden;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/**
 * BitmapItem class for handling image content in slides.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Handles only image-specific slide item functionality
 * - Liskov Substitution Principle (LSP): Properly implements all SlideItem abstract methods
 * - Open-Closed Principle (OCP): Can be extended for specialized image items without modification
 * - Interface Segregation Principle (ISP): Implements only the methods it needs from SlideItem
 */
public class BitmapItem extends SlideItem
{
    private BufferedImage bufferedImage;
    private final String imageName;

    // level is equal to item-level; name is the name of the file with the Image
    public BitmapItem(int level, String name)
    {
        super(level);
        this.imageName = name;
        if (name != null)
        {
            try
            {
                File imageFile = new File(name);
                if (!imageFile.exists())
                {
                    System.err.println(ErrorMessage.FILE_ERROR.getErrorMessage() + name + ErrorMessage.NOT_FOUND.getErrorMessage());
                    return;
                }
                this.bufferedImage = ImageIO.read(imageFile);
            } catch (IOException e)
            {
                System.err.println(ErrorMessage.FILE_ERROR.getErrorMessage() + name + ": " + e.getMessage());
            }
        }
    }

    // An empty bitmap-item
    public BitmapItem()
    {
        this(0, null);
    }

    // give the filename of the image
    public String getImageName()
    {
        return this.imageName;
    }

    // give the  bounding box of the image
    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle)
    {
        if (this.bufferedImage == null || myStyle == null)
        {
            return new Rectangle();
        }

        return new Rectangle(
                (int) (myStyle.getIndent() * scale),
                0,
                (int) (this.bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.getLeading() * scale)) + (int) (this.bufferedImage.getHeight(observer) * scale)
        );
    }

    // draw the image
    @Override
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer)
    {
        if (g == null || this.bufferedImage == null || myStyle == null)
        {
            return;
        }

        int width = x + (int) (myStyle.getIndent() * scale);
        int height = y + (int) (myStyle.getLeading() * scale);

        g.drawImage(
                this.bufferedImage,
                width,
                height,
                (int) (this.bufferedImage.getWidth(observer) * scale),
                (int) (this.bufferedImage.getHeight(observer) * scale),
                observer
        );
    }

    @Override
    public String toString()
    {
        return "BitmapItem[" + this.getLevel() + "," + this.imageName + "]";
    }
}
