package nhl.stenden.observer;

import nhl.stenden.Slide;
import nhl.stenden.command.KeyController;
import nhl.stenden.command.MenuController;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame implements Observer
{
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideViewerFrame(String title, SlideViewerComponent slideViewerComponent, Presentation presentation)
    {
        super(title);
        this.setupWindow(slideViewerComponent, presentation);
    }

    // Setup GUI
    public void setupWindow(SlideViewerComponent
                                    slideViewerComponent, Presentation presentation)
    {
        this.setTitle(JABTITLE);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        this.getContentPane().add(slideViewerComponent);
        this.addKeyListener(new KeyController(presentation)); // add a controller
        this.setMenuBar(new MenuController(presentation));    // add another controller
        this.setSize(new Dimension(WIDTH, HEIGHT)); // Same sizes as Slide has.
        this.setVisible(true);
    }

    @Override
    public void update(Slide slide)
    {
        this.repaint();

        if (slide == null)
        {
            return;
        }

        this.setTitle(slide.getTitle());
    }
}
