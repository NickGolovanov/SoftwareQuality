package nhl.stenden.command.buttons;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.JOptionPane;


class AboutTest {
    @Mock
    private JOptionPane optionPane;

    @Test
    void testExecute() {
        MockitoAnnotations.openMocks(this);
        About about = new About();
        about.execute();
        // Since this is a UI component that shows a dialog,
        // we can't easily verify its content in a unit test.
        // The test will pass if no exceptions are thrown.
    }
} 