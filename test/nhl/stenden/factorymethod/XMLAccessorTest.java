package nhl.stenden.factorymethod;

import nhl.stenden.observer.Presentation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest
{

    @Test
    void testLoadFile() throws IOException
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        // Create a test XML file for loading
        String testFilePath = "testPresentation.xml";
        // Assuming the XMLAccessor has a method to create a test XML file
        createTestXMLFile(testFilePath);
        xmlAccessor.loadFile(presentation, testFilePath);
        // Add assertions to verify the presentation is loaded correctly
        // Example: Check if the presentation has the expected number of slides
        assertEquals(1, presentation.getSize());
        // Add more specific assertions based on the expected content of the presentation
        assertNotNull(presentation);
        // Add more specific assertions based on the expected content of the presentation
    }

    @Test
    void testSaveFile() throws IOException
    {
        XMLAccessor xmlAccessor = new XMLAccessor();
        Presentation presentation = new Presentation();
        // Populate the presentation with test data
        // Assuming the Presentation class has methods to add slides or items
        // presentation.addSlide(new Slide(...));
        String testFilePath = "testSavePresentation.xml";
        xmlAccessor.saveFile(presentation, testFilePath);
        // Add assertions to verify the presentation is saved correctly
        // Example: Check if the saved file exists and contains expected data
        assertTrue(new File(testFilePath).exists());
        // You may need to read the file back and verify its contents
        Presentation loadedPresentation = new Presentation();
        xmlAccessor.loadFile(loadedPresentation, testFilePath);
        assertEquals(0, loadedPresentation.getSize());
        // Add more assertions to verify the content of the loaded presentation
        // You may need to read the file back and verify its contents
        assertTrue(new File(testFilePath).exists());
    }

    private void createTestXMLFile(String filePath)
    {
        // Logic to create a test XML file with sample data
        // This could involve writing a simple XML structure to the file
    }
}
