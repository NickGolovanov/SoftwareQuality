package nhl.stenden.observer;

import nhl.stenden.Slide;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation
{
    private String showTitle; // title of the presentation
    private List<Slide> slides = new ArrayList<>(); // an ArrayList with Slides
    private int currentSlideNumber = 0; // the slidenummer of the current Slide
    private List<Observer> observers = new ArrayList<>();

    public Presentation()
    {
        clear();
    }

    public int getSize()
    {
        return this.slides.size();
    }

    public String getTitle()
    {
        return this.showTitle;
    }

    public void setTitle(String showTitle)
    {
        this.showTitle = showTitle;
    }

    public List<Observer> getObservers()
    {
        return observers;
    }

    public void setObservers(List<Observer> observers)
    {
        this.observers = observers;
    }

    // give the number of the current slide
    public int getSlideNumber()
    {
        return currentSlideNumber;
    }

    // change the current slide number and signal it to the window
    public void setSlideNumber(int number)
    {
        this.currentSlideNumber = number;

        this.notifyObservers();
    }

    // go to the previous slide unless your at the beginning of the presentation
    public void prevSlide()
    {
        if (this.currentSlideNumber > 0)
        {
            this.setSlideNumber(this.currentSlideNumber - 1);
        }
    }

    // go to the next slide unless your at the end of the presentation.
    public void nextSlide()
    {
        if (this.currentSlideNumber < (this.getSize() - 1))
        {
            this.setSlideNumber(currentSlideNumber + 1);
        }
    }

    // Delete the presentation to be ready for the next one.
    public void clear()
    {
        this.slides = new ArrayList<Slide>();
        setSlideNumber(-1);
    }

    // Add a slide to the presentation
    public void append(Slide slide)
    {
        this.slides.add(slide);
    }

    // Get a slide with a certain slidenumber
    public Slide getSlide(int number)
    {
        if (number < 0 || number >= this.getSize())
        {
            return null;
        }
        return this.slides.get(number);
    }

    // Give the current slide
    public Slide getCurrentSlide()
    {
        return this.getSlide(this.currentSlideNumber);
    }

    public void exit(int n)
    {
        System.exit(n);
    }

    public void subscribe(Observer observer)
    {
        this.observers.add(observer);
    }

    public void unsubscribe(Observer observer)
    {
        this.observers.remove(observer);
    }

    public void notifyObservers()
    {
        for (Observer observer : this.observers)
        {
            observer.update(this.getCurrentSlide());
        }
    }
}
