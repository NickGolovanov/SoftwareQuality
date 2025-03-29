import nhl.stenden.factorymethod.XMLAccessor;
import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {

    @Test
    void testLoadFile() {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        // Add logic to create a test XML file
        xmlAccessor.loadFile(presentation, "testPresentation.xml");
        // Add assertions to verify the presentation is loaded correctly
    }

    @Test
    void testSaveFile() {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        // Add logic to populate the presentation
        xmlAccessor.saveFile(presentation, "testSavePresentation.xml");
        // Add assertions to verify the presentation is saved correctly
    }
}
