package nhl.stenden.command.buttons;

import nhl.stenden.command.Receiver;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.JOptionPane;

import static org.mockito.Mockito.*;

class GoToTest {
    @Mock
    private Receiver receiver;

    @Test
    void testExecuteWithValidInput() {
        MockitoAnnotations.openMocks(this);
        GoTo goTo = new GoTo(receiver);
        
        try (MockedStatic<JOptionPane> mockedPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedPane.when(() -> JOptionPane.showInputDialog("Page number?")).thenReturn("2");
            
            goTo.execute();
            verify(receiver).setSlideNumber(1); // Note: GoTo command subtracts 1 from input
        }
    }

    @Test
    void testExecuteWithInvalidInput() {
        MockitoAnnotations.openMocks(this);
        GoTo goTo = new GoTo(receiver);
        
        try (MockedStatic<JOptionPane> mockedPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedPane.when(() -> JOptionPane.showInputDialog("Page number?")).thenReturn("invalid");
            
            goTo.execute();
            verify(receiver, never()).setSlideNumber(anyInt());
        }
    }

    @Test
    void testExecuteWithEmptyInput() {
        MockitoAnnotations.openMocks(this);
        GoTo goTo = new GoTo(receiver);
        
        try (MockedStatic<JOptionPane> mockedPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedPane.when(() -> JOptionPane.showInputDialog("Page number?")).thenReturn("");
            
            goTo.execute();
            verify(receiver, never()).setSlideNumber(anyInt());
        }
    }
} 