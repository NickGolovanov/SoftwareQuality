package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class Next implements Command
{
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
