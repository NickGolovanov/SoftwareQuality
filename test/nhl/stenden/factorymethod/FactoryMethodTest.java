package nhl.stenden.factorymethod;


import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FactoryMethodTest
{

    @Test
    void xmlAccessorCreationTest()
    {
        AccessorCreator creator = new XMLAccessorCreator();
        Accessor accessor = creator.getAccessor();

        assertNotNull(accessor);
        assertTrue(accessor instanceof XMLAccessor);
    }

    @Test
    void demoPresentationCreationTest()
    {
        AccessorCreator creator = new DemoPresentationAccessorCreator();
        Accessor accessor = creator.getAccessor();

        assertNotNull(accessor);
        assertTrue(accessor instanceof DemoPresentation);
    }

    @Test
    void demoPresentationLoadTest()
    {
        Presentation presentation = new Presentation();

        AccessorCreator creator = new DemoPresentationAccessorCreator();
        Accessor accessor = creator.getAccessor();

        try
        {
            accessor.loadFile(presentation, "");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        assertEquals("Demo Presentation", presentation.getTitle());
        assertEquals(3, presentation.getSize());
    }

    @Test
    void xmlAccessorLoadInvalidFileTest()
    {
        Presentation presentation = new Presentation();

        AccessorCreator creator = new XMLAccessorCreator();
        Accessor accessor = creator.getAccessor();

        assertThrows(IOException.class, () -> accessor.loadFile(presentation, "invalid.xml"));
    }
}
