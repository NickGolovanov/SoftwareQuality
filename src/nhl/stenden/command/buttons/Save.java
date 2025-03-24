package nhl.stenden.command.buttons;

import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class Save implements Command
{
    private Receiver receiver;

    public Save(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.save();
    }
}
