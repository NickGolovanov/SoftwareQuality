package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

class KeyControllerTest
{
    @Mock
    private Presentation presentation;

    @Mock
    private Receiver receiver;

    private KeyController keyController;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        keyController = new KeyController(presentation, receiver);
    }

    @Test
    void testNextSlideKeys()
    {
        // Test all keys that should trigger next slide
        int[] nextKeys = {
                KeyEvent.VK_PAGE_DOWN,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_ENTER,
                '+'
        };

        for (int keyCode : nextKeys)
        {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(keyCode);

            keyController.keyPressed(event);
            verify(receiver).nextSlide();
            reset(receiver); // Reset the mock after each verification
        }
    }

    @Test
    void testPreviousSlideKeys()
    {
        // Test all keys that should trigger previous slide
        int[] prevKeys = {
                KeyEvent.VK_PAGE_UP,
                KeyEvent.VK_UP,
                '-'
        };

        for (int keyCode : prevKeys)
        {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(keyCode);

            keyController.keyPressed(event);
            verify(receiver).previousSlide();
            reset(receiver); // Reset the mock after each verification
        }
    }

    @Test
    void testExitKeys()
    {
        // Test exit keys (q and Q)
        int[] exitKeys = {
                'q',
                'Q'
        };

        for (int keyCode : exitKeys)
        {
            KeyEvent event = mock(KeyEvent.class);
            when(event.getKeyCode()).thenReturn(keyCode);

            keyController.keyPressed(event);
            verify(receiver).exit(0);
            reset(receiver); // Reset the mock after each verification
        }
    }

    @Test
    void testUnknownKey()
    {
        KeyEvent event = mock(KeyEvent.class);
        when(event.getKeyCode()).thenReturn(KeyEvent.VK_A); // Some random key

        keyController.keyPressed(event);
        verify(receiver, never()).nextSlide();
        verify(receiver, never()).previousSlide();
        verify(receiver, never()).exit(anyInt());
    }
} 