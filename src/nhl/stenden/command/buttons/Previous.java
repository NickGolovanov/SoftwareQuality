package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class Previous implements Command
{
    private final Receiver receiver;

    public Previous(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.previousSlide();
    }
}
