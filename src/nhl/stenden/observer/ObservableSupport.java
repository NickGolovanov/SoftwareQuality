package nhl.stenden.observer;

import nhl.stenden.Slide;

import java.util.ArrayList;
import java.util.List;

public class ObservableSupport implements Observable
{
    private final List<Observer> observers = new ArrayList<>();
    private final Presentation presentation;

    public ObservableSupport(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void subscribe(Observer observer)
    {
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        Slide currentSlide = presentation.getCurrentSlide();
        for (Observer observer : observers)
        {
            observer.update(currentSlide);
        }
    }

    @Override
    public List<Observer> getObservers()
    {
        return new ArrayList<>(observers);
    }
} 