package nhl.stenden.observer;


import nhl.stenden.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObserverTest
{

    private Presentation presentation;
    private TestObserver observer;

    @BeforeEach
    void setUp()
    {
        presentation = new Presentation();
        observer = new TestObserver();
        presentation.subscribe(observer);
    }

    @Test
    void observerSubscriptionTest()
    {
        assertNotNull(observer);
        assertEquals(1, presentation.getObservers().size(), "Observer should be subscribed.");
    }

    @Test
    void observerNotificationTest()
    {
        Slide slide = new Slide();
        slide.setTitle("Test Slide");
        presentation.append(slide);

        presentation.setSlideNumber(0);

        assertEquals("Test Slide", observer.updatedSlide.getTitle(), "Observer did not receive correct slide update.");
    }

    @Test
    void observerUnsubscribeTest()
    {
        presentation.unsubscribe(observer);
        assertEquals(0, presentation.getObservers().size(), "Observer should be unsubscribed.");
    }

    private static class TestObserver implements Observer
    {
        Slide updatedSlide;

        @Override
        public void update(Slide slide)
        {
            updatedSlide = slide;
        }
    }
}

