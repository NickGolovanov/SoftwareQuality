package nhl.stenden.command.buttons;

import nhl.stenden.AboutBox;
import nhl.stenden.command.Command;
import nhl.stenden.command.Receiver;

public class About implements Command
{
    private Receiver receiver;

    public About(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        AboutBox.show(this.receiver.getParent());
    }
}
