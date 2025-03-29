<<<<<<< HEAD
import nhl.stenden.command.*;
import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    private Receiver receiver;
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        receiver = new Receiver(presentation);
    }

    @Test
    void testOpenCommand() {
        Command openCommand = new Open(receiver, "testPresentation.xml");
        openCommand.execute();
        // Add assertions to verify the presentation is opened correctly
    }

    @Test
    void testSaveCommand() {
        Command saveCommand = new Save(receiver);
        saveCommand.execute();
        // Add assertions to verify the presentation is saved correctly
    }

    @Test
    void testNextCommand() {
        Command nextCommand = new Next(receiver);
        nextCommand.execute();
        // Add assertions to verify the next slide is displayed
    }

    @Test
    void testPreviousCommand() {
        Command previousCommand = new Previous(receiver);
        previousCommand.execute();
        // Add assertions to verify the previous slide is displayed
    }
}
=======
package nhl.stenden.command;

import nhl.stenden.AboutBox;
import nhl.stenden.command.buttons.*;
import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest
{
    @Mock
    private Receiver receiver;

    private Presentation presentation;

    @BeforeEach
    protected void onSetUp()
    {
        presentation = new Presentation();
        receiver = Mockito.mock(Receiver.class);
        presentation.append(new nhl.stenden.Slide());
        presentation.append(new nhl.stenden.Slide());
    }

    @Test
    void nextCommandTest()
    {
        presentation.setSlideNumber(0);

        Command next = new Next(receiver);
        next.execute();

        Mockito.verify(receiver).nextSlide();
    }

    @Test
    void previousCommandTest()
    {
        presentation.setSlideNumber(1);

        Command previous = new Previous(receiver);
        previous.execute();

        Mockito.verify(receiver).previousSlide();
    }

    @Test
    void exitCommandTest() throws Exception
    {
        Command exit = new Exit(receiver);
        exit.execute();

        Mockito.verify(receiver).exit(0);
    }

    @Test
    void aboutCommandTest()
    {
        try (MockedStatic<AboutBox> mockedAbout = Mockito.mockStatic(AboutBox.class))
        {
            Command about = new About();

            assertDoesNotThrow(() -> about.execute());
            mockedAbout.verify(() -> AboutBox.show());
        }
    }

    @Test
    void goToCommandTest()
    {
        try (MockedStatic<JOptionPane> mockedPane = Mockito.mockStatic(JOptionPane.class))
        {
            mockedPane.when(() -> JOptionPane.showInputDialog("Page number?")).thenReturn("2");

            Command goTo = new GoTo(receiver);
            goTo.execute();

            Mockito.verify(receiver).setSlideNumber(1);
        }
    }

    @Test
    void newCommandTest()
    {
        Command newCommand = new New(receiver);
        newCommand.execute();
        Mockito.verify(receiver).save();
    }

    @Test
    void openCommandTest()
    {
        Command open = new Open(receiver, "test.xml");
        open.execute();
        Mockito.verify(receiver).openPresentation("test.xml");
    }

    @Test
    void saveCommandTest()
    {
        Command save = new Save(receiver);
        save.execute();
        Mockito.verify(receiver).save();
    }
}

>>>>>>> 63fd61d076042079fddf88016be0b622190785ee
