package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Command;
import nhl.stenden.comand.Receiver;

public class Save implements Command
{
    /**
     *
     */

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
