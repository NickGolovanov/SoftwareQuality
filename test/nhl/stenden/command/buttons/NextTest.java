package nhl.stenden.command.buttons;

import nhl.stenden.command.Receiver;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class NextTest {
    @Mock
    private Receiver receiver;

    @Test
    void testExecute() {
        MockitoAnnotations.openMocks(this);
        Next next = new Next(receiver);
        next.execute();
        verify(receiver).nextSlide();
    }
} 