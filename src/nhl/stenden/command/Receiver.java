package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import nhl.stenden.factorymethod.Accessor;
import nhl.stenden.factorymethod.AccessorCreator;
import nhl.stenden.factorymethod.XMLAccessorCreator;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Receiver
{
    private Presentation presentation;

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public Receiver(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public Presentation getPresentation()
    {
        return this.presentation;
    }

    public void setPresentation(Presentation presentation)
    {
        this.presentation = presentation;
    }

    public void openPresentation(String fileName)
    {
        presentation.clear();
        AccessorCreator xmlAccessorCreator = new XMLAccessorCreator();
        Accessor xmlAccessor = xmlAccessorCreator.getAccessor();
        try
        {
            xmlAccessor.loadFile(presentation, TESTFILE);
            presentation.setSlideNumber(0);
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog(null, IOEX + exc,
                    LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        presentation.notifyObservers();
    }

    public void nextSlide()
    {
        this.presentation.nextSlide();
    }

    public void previousSlide()
    {
        this.presentation.prevSlide();
    }

    public void setSlideNumber(int slideNumber)
    {
        this.presentation.setSlideNumber(slideNumber);
    }

    public void save()
    {
        AccessorCreator xmlAccessorCreator = new XMLAccessorCreator();
        Accessor xmlAccessor = xmlAccessorCreator.getAccessor();
        try
        {
            xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog(null, IOEX + exc,
                    SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit(int i)
    {
        this.presentation.exit(i);
    }
}
