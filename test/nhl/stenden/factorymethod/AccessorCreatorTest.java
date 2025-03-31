package nhl.stenden.factorymethod;

import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class AccessorCreatorTest {
    private TestAccessorCreator creator;

    // Concrete test implementation of abstract AccessorCreator class
    private static class TestAccessorCreator extends AccessorCreator {
        @Override
        public Accessor create() {
            return new TestAccessor();
        }
    }

    // Concrete test implementation of Accessor class
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
        creator = new TestAccessorCreator();
    }

    @Test
    @DisplayName("Should create accessor instance")
    void testGetAccessor() {
        Accessor accessor = creator.getAccessor();
        assertNotNull(accessor);
        assertTrue(accessor instanceof TestAccessor);
    }

    @Test
    @DisplayName("Should create new instance each time")
    void testGetAccessorCreatesNewInstance() {
        Accessor accessor1 = creator.getAccessor();
        Accessor accessor2 = creator.getAccessor();
        assertNotNull(accessor1);
        assertNotNull(accessor2);
        assertNotSame(accessor1, accessor2);
    }
} 