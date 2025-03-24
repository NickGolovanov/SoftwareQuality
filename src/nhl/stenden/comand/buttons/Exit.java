package nhl.stenden.comand.buttons;

import nhl.stenden.comand.Command;
import nhl.stenden.comand.Receiver;

public class Exit implements Command
{

    /**
     *
     */

    private Receiver receiver;
    private static final int ZERO = 0;

    public Exit(Receiver receiver)
    {
        this.receiver = receiver;
    }

    @Override
    public void execute()
    {
        this.receiver.exit(this.ZERO);
    }
}
