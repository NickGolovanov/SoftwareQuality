package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class New implements Command
{
    private Receiver receiver;

    public New(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.save();
    }
}
