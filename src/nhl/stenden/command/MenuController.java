package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import nhl.stenden.command.buttons.*;
import nhl.stenden.factorymethod.XMLAccessorCreator;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * MenuController class that manages menu actions using Command pattern.
 * 
 * SOLID Principles Implementation:
 * - Single Responsibility Principle (SRP): Handles only menu control functionality
 * - Dependency Inversion Principle (DIP): Depends on Command interface rather than concrete commands
 * - Open-Closed Principle (OCP): New menu items can be added without modifying existing code
 * - Liskov Substitution Principle (LSP): Works with any command implementation
 */
public class MenuController extends MenuBar
{
    private final Receiver receiver;
    private final Map<GlobalVariable, ActionListener> actionListeners;

    private static final long serialVersionUID = 227L;

    private static final String TESTFILE = "test.xml";
    private static final String SAVEFILE = "dump.xml";

    public MenuController(Presentation presentation)
    {
        this.receiver = new Receiver(presentation, new XMLAccessorCreator());
        this.actionListeners = new HashMap<>();

        initializeActionListeners();

        createFileMenu();
        createViewMenu();
        createHelpMenu();
    }

    private void createFileMenu()
    {
        Menu fileMenu = new Menu(GlobalVariable.FILE.getButtonName());
        addMenuItems(fileMenu, new GlobalVariable[]{
                GlobalVariable.OPEN,
                GlobalVariable.NEW,
                GlobalVariable.SAVE,
                GlobalVariable.EXIT
        });
        add(fileMenu);
    }

    private void createViewMenu()
    {
        Menu viewMenu = new Menu(GlobalVariable.VIEW.getButtonName());
        addMenuItems(viewMenu, new GlobalVariable[]{
                GlobalVariable.NEXT,
                GlobalVariable.PREV,
                GlobalVariable.GOTO
        });
        add(viewMenu);
    }

    private void createHelpMenu()
    {
        Menu helpMenu = new Menu(GlobalVariable.HELP.getButtonName());
        addMenuItems(helpMenu, new GlobalVariable[]{GlobalVariable.ABOUT});
        setHelpMenu(helpMenu);
    }

    private void addMenuItems(Menu menu, GlobalVariable[] items)
    {
        for (GlobalVariable item : items)
        {
            MenuItem menuItem = createMenuItem(item.getButtonName());
            menuItem.addActionListener(this.createActionListener(item));
            menu.add(menuItem);
        }
    }

    private void initializeActionListeners()
    {
        actionListeners.put(GlobalVariable.OPEN, e -> new Open(receiver, TESTFILE).execute());
        actionListeners.put(GlobalVariable.NEW, e -> new Save(receiver, SAVEFILE).execute());
        actionListeners.put(GlobalVariable.SAVE, e -> new Save(receiver, SAVEFILE).execute());
        actionListeners.put(GlobalVariable.EXIT, e -> new Exit(receiver).execute());
        actionListeners.put(GlobalVariable.NEXT, e -> new Next(receiver).execute());
        actionListeners.put(GlobalVariable.PREV, e -> new Previous(receiver).execute());
        actionListeners.put(GlobalVariable.GOTO, e -> new GoTo(receiver).execute());
        actionListeners.put(GlobalVariable.ABOUT, e -> new About().execute());
    }

    public ActionListener createActionListener(GlobalVariable item)
    {
        return actionListeners.get(item);
    }

    // create a menu item
    private MenuItem createMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
