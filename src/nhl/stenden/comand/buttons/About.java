package nhl.stenden.comand.buttons;

import nhl.stenden.AboutBox;
import nhl.stenden.comand.Command;
import nhl.stenden.comand.Receiver;

public class About implements Command
{
    Receiver receiver;

    public About(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        AboutBox.show(receiver.getParent());
    }
}
