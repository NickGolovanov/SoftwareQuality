package nhl.stenden.observer;

import nhl.stenden.observer.Presentation;
import nhl.stenden.observer.Observer;
import nhl.stenden.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PresentationTest {
    private Presentation presentation;

    @BeforeEach
    void setUp() {
        presentation = new Presentation();
    }

    @Test
    void testSubscribeObserver() {
        Observer observer = new Observer() {
            @Override
            public void update(Slide slide) {
                // Observer logic for testing
            }
        };
        presentation.subscribe(observer);
        // Add assertions to verify the observer is added
    }

    @Test
    void testUnsubscribeObserver() {
        Observer observer = new Observer() {
            @Override
            public void update(Slide slide) {
                // Observer logic for testing
            }
        };
        presentation.subscribe(observer);
        presentation.unsubscribe(observer);
        // Add assertions to verify the observer is removed
    }

    @Test
    void testNotifyObservers() {
        Observer observer = new Observer() {
            @Override
            public void update(Slide slide) {
                // Observer logic for testing
            }
        };
        presentation.subscribe(observer);
        presentation.notifyObservers();
        // Add assertions to verify observers are notified
    }
}
