package nhl.stenden.observer;

import nhl.stenden.Slide;

public interface SlideManagement
{
    void append(Slide slide);

    Slide getSlide(int number);

    Slide getCurrentSlide();

    int getSize();

    void clear();

    void setTitle(String title);

    String getTitle();
}