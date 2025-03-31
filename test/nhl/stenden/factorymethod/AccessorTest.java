package nhl.stenden.factorymethod;

import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AccessorTest {
    private TestAccessor accessor;

    // Concrete test implementation of abstract Accessor class
    private static class TestAccessor extends Accessor {
        @Override
        public void loadFile(Presentation p, String fn) throws IOException {
            // Test implementation
        }

        @Override
        public void saveFile(Presentation p, String fn) throws IOException {
            // Test implementation
        }
    }

    @BeforeEach
    void setUp() {
        accessor = new TestAccessor();
    }

    @Test
    @DisplayName("Should have correct default extension")
    void testDefaultExtension() {
        assertEquals(".xml", Accessor.DEFAULT_EXTENSION);
    }

    @Test
    @DisplayName("Should have correct demo name")
    void testDemoName() {
        assertEquals("Demonstration presentation", Accessor.DEMO_NAME);
    }

    @Test
    @DisplayName("Should create instance without throwing exception")
    void testConstructor() {
        assertNotNull(accessor);
    }
} 