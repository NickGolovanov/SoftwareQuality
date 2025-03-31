package nhl.stenden.observer;

import nhl.stenden.Slide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest
{
    private TestObserver observer;
    private Slide testSlide;

    // Concrete implementation of Observer interface for testing
    private static class TestObserver implements Observer
    {
        private Slide lastUpdatedSlide;

        @Override
        public void update(Slide slide)
        {
            this.lastUpdatedSlide = slide;
        }

        public Slide getLastUpdatedSlide()
        {
            return lastUpdatedSlide;
        }
    }

    @BeforeEach
    void setUp()
    {
        observer = new TestObserver();
        testSlide = new Slide();
        testSlide.setTitle("Test Slide");
    }

    @Test
    @DisplayName("Should update observer with new slide")
    void testUpdate()
    {
        observer.update(testSlide);
        assertEquals(testSlide, observer.getLastUpdatedSlide());
    }

    @Test
    @DisplayName("Should handle null slide update")
    void testUpdateWithNull()
    {
        observer.update(null);
        assertNull(observer.getLastUpdatedSlide());
    }
}

