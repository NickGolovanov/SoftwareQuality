package nhl.stenden.observer;

import nhl.stenden.Slide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest {
    private Presentation presentation;
    private TestObserver observer;
    private Slide testSlide;

    private static class TestObserver implements Observer {
        private Slide lastUpdatedSlide;

        @Override
        public void update(Slide slide) {
            this.lastUpdatedSlide = slide;
        }

        public Slide getLastUpdatedSlide() {
            return lastUpdatedSlide;
        }
    }

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
        observer = new TestObserver();
        testSlide = new Slide();
        testSlide.setTitle("Test Slide");
    }

    @Test
    @DisplayName("Should initialize with empty state")
    void testInitialization() {
        assertEquals(0, presentation.getSize());
        assertNull(presentation.getTitle());
        assertEquals(-1, presentation.getSlideNumber());
        assertNull(presentation.getCurrentSlide());
    }

    @Test
    @DisplayName("Should set and get title")
    void testTitle() {
        String title = "Test Presentation";
        presentation.setTitle(title);
        assertEquals(title, presentation.getTitle());
    }

    @Test
    @DisplayName("Should append slides")
    void testAppend() {
        presentation.append(testSlide);
        assertEquals(1, presentation.getSize());
        assertEquals(testSlide, presentation.getSlide(0));
    }

    @Test
    @DisplayName("Should get slide by index")
    void testGetSlide() {
        presentation.append(testSlide);
        assertEquals(testSlide, presentation.getSlide(0));
        assertNull(presentation.getSlide(-1));
        assertNull(presentation.getSlide(1));
    }

    @Test
    @DisplayName("Should handle slide navigation")
    void testSlideNavigation() {
        Slide slide1 = new Slide();
        slide1.setTitle("Slide 1");
        Slide slide2 = new Slide();
        slide2.setTitle("Slide 2");
        
        presentation.append(slide1);
        presentation.append(slide2);
        
        presentation.setSlideNumber(0);
        assertEquals(0, presentation.getSlideNumber());
        assertEquals(slide1, presentation.getCurrentSlide());
        
        presentation.nextSlide();
        assertEquals(1, presentation.getSlideNumber());
        assertEquals(slide2, presentation.getCurrentSlide());
        
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
        assertEquals(slide1, presentation.getCurrentSlide());
    }

    @Test
    @DisplayName("Should handle slide navigation boundaries")
    void testSlideNavigationBoundaries() {
        presentation.append(testSlide);
        
        presentation.setSlideNumber(0);
        presentation.nextSlide();
        assertEquals(0, presentation.getSlideNumber());
        
        presentation.prevSlide();
        assertEquals(0, presentation.getSlideNumber());
    }

    @Test
    @DisplayName("Should clear presentation")
    void testClear() {
        presentation.append(testSlide);
        presentation.setTitle("Test Title");
        presentation.setSlideNumber(0);
        
        presentation.clear();
        
        assertEquals(0, presentation.getSize());
        assertEquals(-1, presentation.getSlideNumber());
        assertNull(presentation.getCurrentSlide());
    }

    @Test
    @DisplayName("Should handle observer subscription")
    void testObserverSubscription() {
        presentation.subscribe(observer);
        assertTrue(presentation.getObservers().contains(observer));
        
        presentation.unsubscribe(observer);
        assertFalse(presentation.getObservers().contains(observer));
    }

    @Test
    @DisplayName("Should notify observers of slide changes")
    void testObserverNotification() {
        presentation.subscribe(observer);
        presentation.append(testSlide);
        presentation.setSlideNumber(0);
        
        assertEquals(testSlide, observer.getLastUpdatedSlide());
    }

    @Test
    @DisplayName("Should handle multiple observers")
    void testMultipleObservers() {
        TestObserver observer2 = new TestObserver();
        presentation.subscribe(observer);
        presentation.subscribe(observer2);
        
        presentation.append(testSlide);
        presentation.setSlideNumber(0);
        
        assertEquals(testSlide, observer.getLastUpdatedSlide());
        assertEquals(testSlide, observer2.getLastUpdatedSlide());
    }

    @Test
    @DisplayName("Should handle null slide notification")
    void testNullSlideNotification() {
        presentation.subscribe(observer);
        presentation.setSlideNumber(-1);
        
        assertNull(observer.getLastUpdatedSlide());
    }
}
