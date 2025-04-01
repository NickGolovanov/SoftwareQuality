package nhl.stenden.factorymethod;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorCreatorTest {
    private XMLAccessorCreator creator;

    @BeforeEach
    void setUp() {
        creator = new XMLAccessorCreator();
    }

    @Test
    @DisplayName("Should create XMLAccessor instance")
    void testCreate() {
        Accessor accessor = creator.create();
        assertNotNull(accessor);
        assertTrue(accessor instanceof XMLAccessor);
    }

    @Test
    @DisplayName("Should create new instance each time")
    void testCreateNewInstance() {
        Accessor accessor1 = creator.create();
        Accessor accessor2 = creator.create();
        assertNotNull(accessor1);
        assertNotNull(accessor2);
        assertNotSame(accessor1, accessor2);
    }
} 