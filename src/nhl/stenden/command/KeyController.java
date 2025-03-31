package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import nhl.stenden.command.buttons.Exit;
import nhl.stenden.command.buttons.Next;
import nhl.stenden.command.buttons.Previous;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * <p>This is the KeyController (KeyListener)</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class KeyController extends KeyAdapter
{
    private final Presentation presentation; // Commands are given to the presentation
    private final Receiver receiver;

    public KeyController(Presentation presentation)
    {
        this.presentation = presentation;
        this.receiver = new Receiver(presentation);
    }

    // Constructor for testing purposes
    public KeyController(Presentation presentation, Receiver receiver)
    {
        this.presentation = presentation;
        this.receiver = receiver;
    }

    public void keyPressed(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                Next next = new Next(receiver);
                next.execute();
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                Previous previous = new Previous(receiver);
                previous.execute();
                break;
            case 'q':
            case 'Q':
                Exit exit = new Exit(receiver);
                exit.execute();
                break; // Probably never reached!!
            default:
                break;
        }
    }
}
