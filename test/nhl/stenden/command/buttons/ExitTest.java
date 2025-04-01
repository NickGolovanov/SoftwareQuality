package nhl.stenden.command.buttons;

import nhl.stenden.command.Receiver;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ExitTest {
    @Mock
    private Receiver receiver;

    @Test
    void testExecute() {
        MockitoAnnotations.openMocks(this);
        Exit exit = new Exit(receiver);
        exit.execute();
        verify(receiver).exit(0);
    }
} 