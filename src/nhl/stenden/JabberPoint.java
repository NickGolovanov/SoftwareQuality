package nhl.stenden;

import nhl.stenden.factorymethod.*;
import nhl.stenden.observer.Presentation;
import nhl.stenden.observer.SlideViewerComponent;
import nhl.stenden.observer.SlideViewerFrame;

import javax.swing.JOptionPane;

import java.io.IOException;

/**
 * JabberPoint Main Programma
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class JabberPoint
{
    private static final String JABVERSION = "Jabberpoint 1.6 - OU version";

    /**
     * Het Main Programma
     */
    public static void main(String argv[])
    {
        Style.createStyles();
        Presentation presentation = new Presentation();

        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation);
        presentation.subscribe(slideViewerComponent);

        SlideViewerFrame slideViewerFrame = new SlideViewerFrame(JABVERSION, slideViewerComponent, presentation);
        presentation.subscribe(slideViewerFrame);

        try
        {
            if (argv.length == 0)
            { // een demo presentatie
                AccessorCreator DemoPresentationAccessorCreator = new DemoPresentationAccessorCreator();
                Accessor DemoPresentation = DemoPresentationAccessorCreator.getAccessor();
                DemoPresentation.loadFile(presentation, "");
            } else
            {
                AccessorCreator XMLAccessorCreator = new XMLAccessorCreator();
                Accessor XMLAccessor = XMLAccessorCreator.getAccessor();
                XMLAccessor.loadFile(presentation, argv[0]);
            }
            presentation.setSlideNumber(0);
        } catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null,
                    ErrorMessage.IOERR.getErrorMessage() + ex, ErrorMessage.JABERR.getErrorMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
