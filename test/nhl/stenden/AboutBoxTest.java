package nhl.stenden;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.swing.JOptionPane;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

class AboutBoxTest {
    @Test
    void testShow() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            AboutBox.show();
            
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                any(),
                any(String.class),
                eq("About JabberPoint"),
                eq(JOptionPane.INFORMATION_MESSAGE)
            ));
        }
    }

    @Test
    void testShowMessageContent() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            AboutBox.show();
            
            mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(
                any(),
                argThat(message -> {
                    String msg = (String) message;
                    return msg.contains("JabberPoint is a primitive slide-show program") &&
                           msg.contains("Copyright (c) 1995-1997 by Ian F. Darwin") &&
                           msg.contains("Adapted by Gert Florijn") &&
                           msg.contains("Sylvia Stuurman");
                }),
                eq("About JabberPoint"),
                eq(JOptionPane.INFORMATION_MESSAGE)
            ));
        }
    }
} 