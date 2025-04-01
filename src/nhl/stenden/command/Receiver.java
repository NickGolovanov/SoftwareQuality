package nhl.stenden.command;

import nhl.stenden.ErrorMessage;
import nhl.stenden.observer.Presentation;
import nhl.stenden.factorymethod.Accessor;
import nhl.stenden.factorymethod.AccessorCreator;

import javax.swing.*;
import java.io.IOException;

public class Receiver
{
    private Presentation presentation;
    private final AccessorCreator accessorCreator;

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    public Receiver(Presentation presentation, AccessorCreator accessorCreator)
    {
        this.presentation = presentation;
        this.accessorCreator = accessorCreator;
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

        try
        {
            Accessor accessor = this.accessorCreator.getAccessor();
            accessor.loadFile(presentation, fileName);
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog(null, ErrorMessage.IOEX.getErrorMessage() + exc,
                    ErrorMessage.LOADERR.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
        }
        presentation.setSlideNumber(0);
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

    public void save(String fileName)
    {
        try
        {
            Accessor accessor = this.accessorCreator.getAccessor();
            accessor.saveFile(presentation, fileName);
        } catch (IOException exc)
        {
            JOptionPane.showMessageDialog(null, ErrorMessage.IOEX.getErrorMessage() + exc,
                    ErrorMessage.SAVEERR.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit(int i)
    {
        this.presentation.exit(i);
    }
}
