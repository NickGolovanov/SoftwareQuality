package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.mockito.Mockito.*;

class MenuControllerTest
{
    @Mock
    private Presentation presentation;

    @Mock
    private Receiver receiver;

    private MenuController menuController;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        menuController = new MenuController(presentation);
    }

    @Test
    void testFileMenuItems()
    {
        // Test Open menu item
        MenuItem openItem = findMenuItem(menuController, GlobalVariable.OPEN.getButtonName());
        ActionEvent openEvent = new ActionEvent(openItem, ActionEvent.ACTION_PERFORMED, "Open");
        for (ActionListener listener : openItem.getActionListeners())
        {
            listener.actionPerformed(openEvent);
        }
        verify(presentation).clear();
        verify(presentation).setSlideNumber(0);
        verify(presentation).notifyObservers();

        // Test New menu item
        MenuItem newItem = findMenuItem(menuController, GlobalVariable.NEW.getButtonName());
        ActionEvent newEvent = new ActionEvent(newItem, ActionEvent.ACTION_PERFORMED, "New");
        for (ActionListener listener : newItem.getActionListeners())
        {
            listener.actionPerformed(newEvent);
        }
        verify(presentation).clear();
        verify(presentation).setSlideNumber(0);
        verify(presentation).notifyObservers();

        // Test Save menu item
        MenuItem saveItem = findMenuItem(menuController, GlobalVariable.SAVE.getButtonName());
        ActionEvent saveEvent = new ActionEvent(saveItem, ActionEvent.ACTION_PERFORMED, "Save");
        for (ActionListener listener : saveItem.getActionListeners())
        {
            listener.actionPerformed(saveEvent);
        }
        verify(presentation).notifyObservers();

        // Test Exit menu item
        MenuItem exitItem = findMenuItem(menuController, GlobalVariable.EXIT.getButtonName());
        ActionEvent exitEvent = new ActionEvent(exitItem, ActionEvent.ACTION_PERFORMED, "Exit");
        for (ActionListener listener : exitItem.getActionListeners())
        {
            listener.actionPerformed(exitEvent);
        }
        verify(presentation).exit(0);
    }

    @Test
    void testViewMenuItems()
    {
        // Test Next menu item
        MenuItem nextItem = findMenuItem(menuController, GlobalVariable.NEXT.getButtonName());
        ActionEvent nextEvent = new ActionEvent(nextItem, ActionEvent.ACTION_PERFORMED, "Next");
        for (ActionListener listener : nextItem.getActionListeners())
        {
            listener.actionPerformed(nextEvent);
        }
        verify(presentation).nextSlide();

        // Test Previous menu item
        MenuItem prevItem = findMenuItem(menuController, GlobalVariable.PREV.getButtonName());
        ActionEvent prevEvent = new ActionEvent(prevItem, ActionEvent.ACTION_PERFORMED, "Previous");
        for (ActionListener listener : prevItem.getActionListeners())
        {
            listener.actionPerformed(prevEvent);
        }
        verify(presentation).prevSlide();

        // Test GoTo menu item
        MenuItem gotoItem = findMenuItem(menuController, GlobalVariable.GOTO.getButtonName());
        ActionEvent gotoEvent = new ActionEvent(gotoItem, ActionEvent.ACTION_PERFORMED, "GoTo");
        for (ActionListener listener : gotoItem.getActionListeners())
        {
            listener.actionPerformed(gotoEvent);
        }
        // The GoTo command will show a dialog, which we can't easily verify in a unit test
    }

    @Test
    void testHelpMenuItems()
    {
        // Test About menu item
        MenuItem aboutItem = findMenuItem(menuController, GlobalVariable.ABOUT.getButtonName());
        ActionEvent aboutEvent = new ActionEvent(aboutItem, ActionEvent.ACTION_PERFORMED, "About");
        for (ActionListener listener : aboutItem.getActionListeners())
        {
            listener.actionPerformed(aboutEvent);
        }
        // The About command will show a dialog, which we can't easily verify in a unit test
    }

    private MenuItem findMenuItem(MenuController controller, String label)
    {
        for (Menu menu : new Menu[]{controller.getMenu(0), controller.getMenu(1), controller.getMenu(2)})
        {
            for (int i = 0; i < menu.getItemCount(); i++)
            {
                MenuItem item = menu.getItem(i);
                if (item != null && item.getLabel().equals(label))
                {
                    return item;
                }
            }
        }
        return null;
    }
} 