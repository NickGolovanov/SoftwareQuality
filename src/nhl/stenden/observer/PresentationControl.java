package nhl.stenden.observer;

public interface PresentationControl
{
    void nextSlide();

    void prevSlide();

    void setSlideNumber(int number);

    int getSlideNumber();
}