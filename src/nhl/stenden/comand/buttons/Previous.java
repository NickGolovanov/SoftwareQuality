package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Comand;
import nhl.stenden.comand.Receiver;

public class Previous implements Comand
{
    private Receiver receiver;

    public Previous(Receiver receiver)
    {
        this.receiver = receiver;
    }


    /**
     *
     */
    @Override
    public void execute()
    {
        this.receiver.previousSlide();
    }
}
