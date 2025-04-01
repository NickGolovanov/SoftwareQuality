package nhl.stenden.command.buttons;

import nhl.stenden.command.Receiver;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SaveTest
{
    @Mock
    private Receiver receiver;

    @Test
    void testExecute()
    {
        MockitoAnnotations.openMocks(this);
        Save save = new Save(receiver, "test.xml");
        save.execute();
        verify(receiver).save("test.xml");
    }
} 