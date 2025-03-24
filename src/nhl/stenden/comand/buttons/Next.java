package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Comand;
import nhl.stenden.comand.Receiver;

public class Next implements Comand
{
    /**
     *
     */

    private Receiver receiver;

    public Next(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.nextSlide();
    }
}
