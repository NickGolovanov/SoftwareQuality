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

public class Presentation implements SlideManagement, PresentationControl, Observable
{
    private String showTitle; // title of the presentation
    private List<Slide> slides = new ArrayList<>(); // an ArrayList with Slides
    private int currentSlideNumber = 0; // the index of the current Slide
    private final ObservableSupport observableSupport;
    private final ApplicationExit applicationExit;

    public Presentation()
    {
        this(new SystemApplicationExit());
        clear();
    }

    public Presentation(ApplicationExit applicationExit)
    {
        this.applicationExit = applicationExit;
        this.observableSupport = new ObservableSupport(this);
    }
    
    public void exit(int status)
    {
        this.applicationExit.exit(status);
    }

    @Override
    public int getSize()
    {
        return this.slides.size();
    }

    @Override
    public String getTitle()
    {
        return this.showTitle;
    }

    @Override
    public void setTitle(String showTitle)
    {
        this.showTitle = showTitle;
        this.notifyObservers();
    }

    @Override
    public int getSlideNumber()
    {
        return this.currentSlideNumber;
    }

    @Override
    public void setSlideNumber(int number)
    {
        this.currentSlideNumber = number;
        this.notifyObservers();
    }

    @Override
    public void prevSlide()
    {
        if (this.currentSlideNumber > 0)
        {
            this.setSlideNumber(this.currentSlideNumber - 1);
        }
    }

    @Override
    public void nextSlide()
    {
        if (this.currentSlideNumber < (this.getSize() - 1))
        {
            this.setSlideNumber(currentSlideNumber + 1);
        }
    }

    @Override
    public void clear()
    {
        this.slides = new ArrayList<Slide>();
        setSlideNumber(-1);
    }

    @Override
    public void append(Slide slide)
    {
        this.slides.add(slide);
        this.notifyObservers();
    }

    @Override
    public Slide getSlide(int number)
    {
        if (number < 0 || number >= this.getSize())
        {
            return null;
        }
        return this.slides.get(number);
    }

    @Override
    public Slide getCurrentSlide()
    {
        return this.getSlide(this.currentSlideNumber);
    }


    @Override
    public void subscribe(Observer observer)
    {
        this.observableSupport.subscribe(observer);
    }

    @Override
    public void unsubscribe(Observer observer)
    {
        this.observableSupport.unsubscribe(observer);
    }

    @Override
    public void notifyObservers()
    {
        this.observableSupport.notifyObservers();
    }

    @Override
    public List<Observer> getObservers()
    {
        return this.observableSupport.getObservers();
    }
}
