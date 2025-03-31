package nhl.stenden.command.buttons;

import nhl.stenden.command.Receiver;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class PreviousTest {
    @Mock
    private Receiver receiver;

    @Test
    void testExecute() {
        MockitoAnnotations.openMocks(this);
        Previous previous = new Previous(receiver);
        previous.execute();
        verify(receiver).previousSlide();
    }
} 