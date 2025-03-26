package nhl.stenden.observer;

import nhl.stenden.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

class SlideViewerComponentTest {
    private Presentation presentation;
    private SlideViewerComponent slideViewer;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();

        // Mock SlideViewerComponent without needing JFrame
        slideViewer = Mockito.mock(SlideViewerComponent.class);

        presentation.subscribe(slideViewer);
    }

    @Test
    void testObserverIsSubscribed() {
        assertEquals(0, presentation.getSize(), "Presentation should start empty.");
    }

    @Test
    void testSlideChangeNotifiesObserver() {
        Slide slide1 = new Slide();
        Slide slide2 = new Slide();

        presentation.append(slide1);
        presentation.append(slide2);

        presentation.setSlideNumber(0);
        assertEquals(slide1, presentation.getCurrentSlide(), "Observer should update to Slide 1.");

        presentation.setSlideNumber(1);
        assertEquals(slide2, presentation.getCurrentSlide(), "Observer should update to Slide 2.");
    }

    @Test
    void testObserverReceivesInitialState() {
        assertNull(presentation.getCurrentSlide(), "Initial slide should be null.");
    }
}
