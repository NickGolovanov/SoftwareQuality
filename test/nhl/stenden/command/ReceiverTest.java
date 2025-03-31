package nhl.stenden.command;

import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiverTest {
    @Mock
    private Presentation presentation;

    private Receiver receiver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        receiver = new Receiver(presentation);
    }

    @Test
    void testGetPresentation() {
        assertEquals(presentation, receiver.getPresentation());
    }

    @Test
    void testSetPresentation() {
        Presentation newPresentation = mock(Presentation.class);
        receiver.setPresentation(newPresentation);
        assertEquals(newPresentation, receiver.getPresentation());
    }

    @Test
    void testOpenPresentation() {
        receiver.openPresentation("test.xml");
        verify(presentation).clear();
        verify(presentation).setSlideNumber(0);
        verify(presentation).notifyObservers();
    }

    @Test
    void testNextSlide() {
        receiver.nextSlide();
        verify(presentation).nextSlide();
    }

    @Test
    void testPreviousSlide() {
        receiver.previousSlide();
        verify(presentation).prevSlide();
    }

    @Test
    void testSetSlideNumber() {
        int slideNumber = 5;
        receiver.setSlideNumber(slideNumber);
        verify(presentation).setSlideNumber(slideNumber);
    }

    @Test
    void testSave() {
        receiver.save();
        verify(presentation, never()).exit(anyInt());
    }

    @Test
    void testExit() {
        int exitCode = 1;
        receiver.exit(exitCode);
        verify(presentation).exit(exitCode);
    }
} 