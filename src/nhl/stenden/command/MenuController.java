package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import nhl.stenden.command.buttons.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{
    private final Frame parent; // the frame, only used as parent for the Dialogs
    private final Presentation presentation; // Commands are given to the presentation
    private final Receiver receiver;

    private static final long serialVersionUID = 227L;

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    public MenuController(Frame parent, Presentation presentation)
    {
        this.parent = parent;
        this.presentation = presentation;
        this.receiver = new Receiver(presentation);

        MenuItem menuItem;

        Menu fileMenu = new Menu(GlobalVariable.FILE.getButtonName());
        fileMenu.add(menuItem = this.createMenuItem(GlobalVariable.OPEN.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Command openCommand = new Open(receiver, TESTFILE);
                openCommand.execute();
            }
        });
        fileMenu.add(menuItem = this.createMenuItem(GlobalVariable.NEW.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Save save = new Save(receiver);
                save.execute();
            }
        });
        fileMenu.add(menuItem = this.createMenuItem(GlobalVariable.SAVE.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Save save = new Save(receiver);
                save.execute();
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(menuItem = this.createMenuItem(GlobalVariable.EXIT.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Exit exit = new Exit(receiver);
                exit.execute();
            }
        });
        add(fileMenu);
        Menu viewMenu = new Menu(GlobalVariable.VIEW.getButtonName());
        viewMenu.add(menuItem = this.createMenuItem(GlobalVariable.NEXT.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Next nextCommand = new Next(receiver);
                nextCommand.execute();
            }
        });
        viewMenu.add(menuItem = this.createMenuItem(GlobalVariable.PREV.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Previous previous = new Previous(receiver);
                previous.execute();
            }
        });
        viewMenu.add(menuItem = this.createMenuItem(GlobalVariable.GOTO.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                GoTo goTo = new GoTo(receiver);
                goTo.execute();
            }
        });
        add(viewMenu);
        Menu helpMenu = new Menu(GlobalVariable.HELP.getButtonName());
        helpMenu.add(menuItem = this.createMenuItem(GlobalVariable.ABOUT.getButtonName()));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                Command aboutBox = new About();
                aboutBox.execute();
            }
        });
        setHelpMenu(helpMenu);        // needed for portability (Motif, etc.).
    }

    // create a menu item
    public MenuItem createMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
