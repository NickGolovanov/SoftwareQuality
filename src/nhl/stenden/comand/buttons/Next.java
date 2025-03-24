package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Command;
import nhl.stenden.comand.Receiver;

public class Next implements Command
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
