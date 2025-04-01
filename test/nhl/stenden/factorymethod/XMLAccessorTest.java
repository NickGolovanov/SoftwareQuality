package nhl.stenden.factorymethod;

import nhl.stenden.observer.Presentation;
import nhl.stenden.BitmapItem;
import nhl.stenden.TextItem;
import nhl.stenden.Slide;
import nhl.stenden.SlideItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class XMLAccessorTest {
    private XMLAccessor accessor;
    private Presentation presentation;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        accessor = new XMLAccessor();
        presentation = new Presentation();
    }

    @Test
    @DisplayName("Should load XML file with text items")
    void testLoadFileWithTextItems() throws IOException {
        // Create test XML file
        String xmlContent = "<?xml version=\"1.0\"?>" +
                "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">" +
                "<presentation>" +
                "<showtitle>Test Presentation</showtitle>" +
                "<slide>" +
                "<title>Test Slide</title>" +
                "<item kind=\"text\" level=\"1\">Test Text Item</item>" +
                "</slide>" +
                "</presentation>";

        // Create DTD file
        String dtdContent = "<!ELEMENT presentation (showtitle?, slide*)>\n" +
                "<!ELEMENT showtitle (#PCDATA)>\n" +
                "<!ELEMENT slide (title, item*)>\n" +
                "<!ELEMENT title (#PCDATA)>\n" +
                "<!ELEMENT item (#PCDATA)>\n" +
                "<!ATTLIST item\n" +
                "    kind (text|image) #REQUIRED\n" +
                "    level (0|1|2|3|4) #REQUIRED>";

        File dtdFile = tempDir.resolve("jabberpoint.dtd").toFile();
        java.nio.file.Files.write(dtdFile.toPath(), dtdContent.getBytes());

        File testFile = tempDir.resolve("test.xml").toFile();
        java.nio.file.Files.write(testFile.toPath(), xmlContent.getBytes());

        // Load the file
        accessor.loadFile(presentation, testFile.getAbsolutePath());

        // Verify the loaded content
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(1, presentation.getSize());
        Slide slide = presentation.getSlide(0);
        assertEquals("Test Slide", slide.getTitle());
        assertEquals(1, slide.getSlides().size());
        SlideItem item = slide.getSlide(0);
        assertTrue(item instanceof TextItem);
        assertEquals(1, item.getLevel());
        assertEquals("Test Text Item", ((TextItem) item).getText());
    }

    @Test
    @DisplayName("Should load XML file with image items")
    void testLoadFileWithImageItems() throws IOException {
        // Create test XML file
        String xmlContent = "<?xml version=\"1.0\"?>" +
                "<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">" +
                "<presentation>" +
                "<showtitle>Test Presentation</showtitle>" +
                "<slide>" +
                "<title>Test Slide</title>" +
                "<item kind=\"image\" level=\"1\">test.jpg</item>" +
                "</slide>" +
                "</presentation>";

        // Create DTD file
        String dtdContent = "<!ELEMENT presentation (showtitle?, slide*)>\n" +
                "<!ELEMENT showtitle (#PCDATA)>\n" +
                "<!ELEMENT slide (title, item*)>\n" +
                "<!ELEMENT title (#PCDATA)>\n" +
                "<!ELEMENT item (#PCDATA)>\n" +
                "<!ATTLIST item\n" +
                "    kind (text|image) #REQUIRED\n" +
                "    level (0|1|2|3|4) #REQUIRED>";

        File dtdFile = tempDir.resolve("jabberpoint.dtd").toFile();
        java.nio.file.Files.write(dtdFile.toPath(), dtdContent.getBytes());

        File testFile = tempDir.resolve("test.xml").toFile();
        java.nio.file.Files.write(testFile.toPath(), xmlContent.getBytes());

        // Load the file
        accessor.loadFile(presentation, testFile.getAbsolutePath());

        // Verify the loaded content
        assertEquals("Test Presentation", presentation.getTitle());
        assertEquals(1, presentation.getSize());
        Slide slide = presentation.getSlide(0);
        assertEquals("Test Slide", slide.getTitle());
        assertEquals(1, slide.getSlides().size());
        SlideItem item = slide.getSlide(0);
        assertTrue(item instanceof BitmapItem);
        assertEquals(1, item.getLevel());
        assertEquals("test.jpg", ((BitmapItem) item).getImageName());
    }

    @Test
    @DisplayName("Should save presentation to XML file")
    void testSaveFile() throws IOException {
        // Create test presentation
        presentation.setTitle("Test Presentation");
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        slide.append(new TextItem(1, "Test Text Item"));
        presentation.append(slide);

        // Save the presentation
        File outputFile = tempDir.resolve("output.xml").toFile();
        accessor.saveFile(presentation, outputFile.getAbsolutePath());

        // Verify the saved content
        String savedContent = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
        assertTrue(savedContent.contains("Test Presentation"));
        assertTrue(savedContent.contains("Test Slide"));
        assertTrue(savedContent.contains("Test Text Item"));
        assertTrue(savedContent.contains("kind=\"text\""));
        assertTrue(savedContent.contains("level=\"1\""));
    }

    @Test
    @DisplayName("Should handle empty presentation")
    void testEmptyPresentation() throws IOException {
        // Save empty presentation
        File outputFile = tempDir.resolve("empty.xml").toFile();
        accessor.saveFile(presentation, outputFile.getAbsolutePath());

        // Verify the saved content
        String savedContent = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
        assertTrue(savedContent.contains("<presentation>"));
        assertTrue(savedContent.contains("</presentation>"));
        assertFalse(savedContent.contains("<slide>"));
    }

    @Test
    @DisplayName("Should handle non-existent file")
    void testNonExistentFile() {
        assertThrows(IOException.class, () -> {
            accessor.loadFile(presentation, "nonexistent.xml");
        });
    }
}
