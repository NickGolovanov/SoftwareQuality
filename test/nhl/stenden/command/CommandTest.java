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
